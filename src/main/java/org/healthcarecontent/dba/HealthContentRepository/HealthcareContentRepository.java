package org.healthcarecontent.dba.HealthContentRepository;

import org.healthcarecontent.model.HealthcareContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthcareContentRepository extends JpaRepository<HealthcareContent, Long> {
}