package com.wesleysilva.bappoint.Settings;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/settings")
@Tag(name = "dev/settings", description = "Manage company settings")
public class SettingsController {

    private final SettingsService settingsService;

    public SettingsController(SettingsService settingsService) {
        this.settingsService = settingsService;
    }

    @PostMapping("/create")
    @Operation(
            summary = "",
            description = ""
    )
    public ResponseEntity<String> createSettings(@RequestBody SettingsDTO settings) {
        SettingsDTO newSettings = settingsService.createSettings(settings);
        return ResponseEntity.status(HttpStatus.CREATED).body(newSettings.toString());
    }

    @GetMapping("/list")
    @Operation(
            summary = "",
            description = ""
    )
    public ResponseEntity<List<SettingsDTO>> listSettings(){
        List<SettingsDTO> settings = settingsService.listSettings();

        return ResponseEntity.status(HttpStatus.OK).body(settings);
    }

    @GetMapping("/list/{id}")
    @Operation(
            summary = "",
            description = ""
    )
    public ResponseEntity<String> listSettingsById(@PathVariable UUID id) {
        SettingsDTO settings = settingsService.listSettingsById(id);
        if (settings != null) {
            return ResponseEntity.status(HttpStatus.OK).body(settings.toString());
        } else  {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Settings not found");
        }
    }

    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = "",
            description = ""
    )
    public ResponseEntity<String> deleteSettings(@PathVariable UUID id) {
       if (settingsService.listSettingsById(id) != null){
           settingsService.deleteSettingsById(id);
           return ResponseEntity.status(HttpStatus.OK).body("Settings deleted");
        } else   {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Settings not found");
        }
    }

    @PutMapping("/edit/{id}")
    @Operation(
            summary = "",
            description = ""
    )
    public ResponseEntity<?> updateSettings(@PathVariable UUID id, @RequestBody SettingsDTO settings) {
        SettingsDTO updatedSettings = settingsService.updateSettings(id, settings);
        if (updatedSettings != null) {
            return ResponseEntity.status(HttpStatus.OK).body(updatedSettings.toString());
        } else   {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Settings not found");
        }
    }
}
