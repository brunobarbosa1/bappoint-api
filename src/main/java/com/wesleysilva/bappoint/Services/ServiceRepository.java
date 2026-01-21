package com.wesleysilva.bappoint.Services;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServiceRepository extends JpaRepository<ServiceModel, UUID> {
}
