package com.wesleysilva.bappoint.Services;

import com.wesleysilva.bappoint.Company.CompanyModel;
import com.wesleysilva.bappoint.Company.CompanyRepository;
import com.wesleysilva.bappoint.Services.dto.CreateServiceDTO;
import com.wesleysilva.bappoint.Services.dto.ServiceAllDetailsDTO;
import com.wesleysilva.bappoint.Services.dto.ServiceResponseDTO;
import com.wesleysilva.bappoint.Services.dto.UpdateServiceDTO;
import com.wesleysilva.bappoint.Settings.SettingsModel;
import com.wesleysilva.bappoint.Settings.SettingsRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("company/{companyId}/services")
@Tag(name = "dev/Services", description = "Manage company services")
public class ServicesController {

    private final ServiceService serviceService;
    private final CompanyRepository companyRepository;

    public ServicesController(ServiceService serviceService,
                              CompanyRepository companyRepository,
                              SettingsRepository settingsRepository) {
        this.serviceService = serviceService;
        this.companyRepository = companyRepository;
    }

    @PostMapping("/create")
    @Operation(summary = "Create service for company", description = "")
    public ResponseEntity<CreateServiceDTO> createService(
            @PathVariable UUID companyId,
           @Valid @RequestBody CreateServiceDTO service) {

        CreateServiceDTO newService = serviceService.createService(service, companyId);
        return ResponseEntity.status(HttpStatus.CREATED).body(newService);
    }

    @GetMapping("/list")
    @Operation(summary = "List services for company", description = "")
    public ResponseEntity<List<ServiceResponseDTO>> listServices() {
        List<ServiceResponseDTO> serviceDTOS = serviceService.listAllServices();
        return ResponseEntity.ok(serviceDTOS);
    }

    @GetMapping("/{serviceId}")
    @Operation(summary = "Get service by ID", description = "")
    public ResponseEntity<ServiceAllDetailsDTO> getServiceById(@PathVariable UUID serviceId) {
        ServiceAllDetailsDTO service = serviceService.getServiceById(serviceId);
            return ResponseEntity.status(HttpStatus.OK).body(service);
    }

    @DeleteMapping("/delete/{serviceId}")
    @Operation(summary = "Delete service", description = "")
    public ResponseEntity<String> deleteService(@PathVariable UUID serviceId) {
            serviceService.deleteService(serviceId);
            return ResponseEntity.status(HttpStatus.OK).body("Service deleted");
    }

    @PutMapping("/update/{serviceId}")
    @Operation(summary = "Update service", description = "")
    public ResponseEntity<UpdateServiceDTO> updateService(
            @PathVariable UUID serviceId,
            @Valid @RequestBody CreateServiceDTO service) {

        UpdateServiceDTO updatedService = serviceService.updateService(serviceId, service);
            return ResponseEntity.status(HttpStatus.OK).body(updatedService);
    }
}
