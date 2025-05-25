package org.healthcarecontent.services;

// Example using WebClient in your Spring Boot RecommendationService.java
import org.healthcarecontent.dba.HealthContentRepository.HealthcareContentRepository;
import org.healthcarecontent.model.HealthcareContent;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.List;
import java.util.Map; // For the request body map

@Service
public class RecommendationService {

    private final WebClient webClient;
    private final HealthcareContentRepository contentRepository; // Assuming you have this

    // Constructor injection for WebClient and Repository
    public RecommendationService(WebClient.Builder webClientBuilder, HealthcareContentRepository contentRepository) {
        // Configure WebClient to point to your FastAPI service
        this.webClient = webClientBuilder.baseUrl("http://127.0.0.1:5000").build();
        this.contentRepository = contentRepository;
    }

    public Mono<List<HealthcareContent>> getRecommendations(Long contentId, int numRecommendations) {
        // Create the request body for FastAPI
        Map<String, Object> requestBody = Map.of(
                "content_id", contentId,
                "num_recommendations", numRecommendations
        );

        // Make the POST request to FastAPI and retrieve the response
        // Mono<List<Map>> means we expect a list of maps (JSON objects)
        return webClient.post()
                .uri("/recommend")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(List.class)
                .flatMap(rawRecommendationsObj -> {
                    List<Map<String, Object>> rawRecommendations = (List<Map<String, Object>>) rawRecommendationsObj;
                    if (CollectionUtils.isEmpty(rawRecommendations)) {
                        return Mono.just(List.of());
                    }
                    List<Long> recommendedContentIds = rawRecommendations.stream()
                            .map(rec -> ((Map<String, Object>) rec).get("id"))
                            .map(id -> Long.parseLong(id.toString()))
                            .toList();
                    // Wrap blocking call in Mono.fromCallable and schedule on boundedElastic
                    return Mono.fromCallable(() -> contentRepository.findAllById(recommendedContentIds))
                            .subscribeOn(reactor.core.scheduler.Schedulers.boundedElastic());
                });
    }
}
