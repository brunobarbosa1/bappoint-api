package com.wesleysilva.bappoint.Services;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    public ServicesController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @PostMapping("/create")
    @Operation(
            summary = "",
            description = ""
    )
    public ResponseEntity<String> createService(@PathVariable UUID companyId, @RequestBody ServiceDTO service){
        service.setCompany_id(companyId);
        ServiceDTO newService = serviceService.createService(service);
        return ResponseEntity.status(HttpStatus.CREATED).body(newService.toString());

    }

    @GetMapping("/list")
    @Operation(
            summary = "",
            description = ""
    )
    public ResponseEntity<List<ServiceDTO>> listServices() {
        List<ServiceDTO> serviceDTOS = serviceService.listServices();
        return ResponseEntity.ok(serviceDTOS);
    }

    @GetMapping("/list/{serviceId}")
    @Operation(
            summary = "",
            description = ""
    )
    public ResponseEntity<ServiceDTO> listServicesById(@PathVariable UUID serviceId) {
        ServiceDTO service = serviceService.getServiceById(serviceId);
        if (service != null) {
            return ResponseEntity.status(HttpStatus.OK).body(service);
        } else  {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("delete/{serviceId}")
    @Operation(
            summary = "",
            description = ""
    )
    public ResponseEntity<String> deleteService(@PathVariable UUID serviceId){
        if(serviceService.getServiceById(serviceId) != null) {
            serviceService.deleteService(serviceId);
            return ResponseEntity.ok("Service id: " + serviceId + " delete successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/update/{serviceId}")
    @Operation(
            summary = "",
            description = ""
    )
    public ResponseEntity<ServiceDTO> updateService(@PathVariable UUID serviceId, @RequestBody ServiceDTO service){
        ServiceDTO company = serviceService.updateService(serviceId, service);
        if (company != null) {
            return ResponseEntity.status(HttpStatus.OK).body(company);
        } else  {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
