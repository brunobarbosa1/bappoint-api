package com.wesleysilva.bappoint.Settings;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class SettingsService {
    private final SettingsRepository settingsRepository;
    private final SettingsMapper settingsMapper;

    public SettingsService(SettingsMapper serviceMapper, SettingsRepository settingsRepository) {
        this.settingsMapper = serviceMapper;
        this.settingsRepository = settingsRepository;
    }

    public SettingsDTO createSettings(SettingsDTO settingsDTO) {
        SettingsModel settingsModel = settingsMapper.map(settingsDTO);
        settingsModel = settingsRepository.save(settingsModel);

        return settingsMapper.map(settingsModel);
    }

    public List<SettingsDTO> listSettings() {
        List<SettingsModel> settingsModels = settingsRepository.findAll();
        return settingsModels.stream()
                .map(settingsMapper::map)
                .collect(Collectors.toList());
    }

    public SettingsDTO listSettingsById(UUID id) {
        SettingsModel settingsModel = settingsRepository.findById(id).orElse(null);
        assert settingsModel != null;
        return settingsMapper.map(settingsModel);
    }

    void deleteSettingsById(UUID id) {
        settingsRepository.deleteById(id);
    }

    public SettingsDTO updateSettings(UUID id, SettingsDTO settingsDTO) {
        Optional<SettingsModel> settingsModel = settingsRepository.findById(id);
        if (settingsModel.isPresent()) {
            SettingsModel settingsUpdated = settingsMapper.map(settingsDTO);
            settingsUpdated.setId(id);
            SettingsModel settingsModelUpdated = settingsRepository.save(settingsUpdated);
            return settingsMapper.map(settingsModelUpdated);
        } else  {
            return null;
        }

    }

}
