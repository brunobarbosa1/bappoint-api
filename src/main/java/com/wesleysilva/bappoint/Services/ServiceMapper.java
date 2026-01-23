package com.wesleysilva.bappoint.Services;

import com.wesleysilva.bappoint.Company.CompanyModel;
import com.wesleysilva.bappoint.Company.CompanyRepository;
import org.springframework.stereotype.Component;

@Component
public class ServiceMapper {

    private final CompanyRepository companyRepository;

    public ServiceMapper(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public ServiceModel map(ServiceDTO serviceDTO) {
        ServiceModel serviceModel = new ServiceModel();

        serviceModel.setId(serviceDTO.getId());
        serviceModel.setName(serviceDTO.getName());
        serviceModel.setPrice(serviceDTO.getPrice());
        serviceModel.setDuration_minutes(serviceDTO.getDuration_minutes());
        serviceModel.setIs_active(serviceDTO.getIs_active());

        if (serviceDTO.getCompany_id() != null) {
            CompanyModel company = companyRepository
                    .findById(serviceDTO.getCompany_id())
                    .orElseThrow(() -> new RuntimeException("Company not found"));
            serviceModel.setCompany(company);
        }

        return serviceModel;
    }

    public ServiceDTO map(ServiceModel serviceModel) {
        ServiceDTO serviceDTO = new ServiceDTO();

        serviceDTO.setId(serviceModel.getId());
        serviceDTO.setName(serviceModel.getName());
        serviceDTO.setPrice(serviceModel.getPrice());
        serviceDTO.setDuration_minutes(serviceModel.getDuration_minutes());
        serviceDTO.setIs_active(serviceModel.getIs_active());

        if (serviceModel.getCompany() != null) {
            serviceDTO.setCompany_id(serviceModel.getCompany().getId());
        }

        return serviceDTO;
    }
}
