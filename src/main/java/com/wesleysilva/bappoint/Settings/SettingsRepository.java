package com.wesleysilva.bappoint.Settings;

import org.springframework.context.aot.AbstractAotProcessor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SettingsRepository extends JpaRepository<SettingsModel, UUID> {
}
