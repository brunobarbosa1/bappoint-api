package com.wesleysilva.bappoint.Company;

import com.wesleysilva.bappoint.Company.dto.CompanyDetailsResponseDTO;
import com.wesleysilva.bappoint.Company.dto.CompanyResponseDTO;
import com.wesleysilva.bappoint.Company.dto.CreateCompanyDTO;
import com.wesleysilva.bappoint.Company.dto.UpdateCompanyDTO;
import com.wesleysilva.bappoint.Settings.SettingsModel;
import com.wesleysilva.bappoint.exceptions.CompanyDeleteException;
import com.wesleysilva.bappoint.exceptions.CompanyNotFoundException;
import com.wesleysilva.bappoint.exceptions.EmailAlreadyExistsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    public CompanyService(CompanyRepository companyRepository, CompanyMapper companyMapper) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
    }


    @Transactional
    public CreateCompanyDTO createCompany(CreateCompanyDTO dto) {
        if (companyRepository.existsByEmail(dto.getEmail())) {
            throw new EmailAlreadyExistsException();
        }

        CompanyModel company = new CompanyModel();
        company.setName(dto.getName());
        company.setEmail(dto.getEmail());
        company.setPhone(dto.getPhone());
        company.setAddress(dto.getAddress());

        if (dto.getSettings() != null) {
            SettingsModel settings = new SettingsModel();
            settings.setAppointment_interval(dto.getSettings().getAppointment_interval());
            settings.setMax_cancellation_interval(dto.getSettings().getMax_cancellation_interval());
            company.setSettings(settings);
        }

        CompanyModel saved = companyRepository.save(company);

        return companyMapper.toCreate(saved);
    }

    @Transactional(readOnly = true)
    public List<CompanyResponseDTO> listCompanies() {
       List<CompanyModel> companyModel = companyRepository.findAll();
        return companyModel.stream()
                .map(companyMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CompanyDetailsResponseDTO getCompanyById(UUID id) {
        CompanyModel companyModel = companyRepository.findById(id).orElseThrow(() -> new CompanyNotFoundException());
        if (companyModel.getAppointments() != null) {
            companyModel.getAppointments().size();
        }
        return companyMapper.toDetailsResponseDTO(companyModel);
    }

    @Transactional
    void deleteCompany(UUID companyId) {
        CompanyModel companyModel = companyRepository.findById(companyId).orElseThrow(() -> new CompanyNotFoundException());

        try{
            companyRepository.delete(companyModel);
        } catch (Exception exception){
            throw new CompanyDeleteException();
        }
    }

    @Transactional
    public CompanyResponseDTO updateCompany(UUID companyId, UpdateCompanyDTO updateDTO) {
        CompanyModel existingCompany = companyRepository.findById(companyId).orElseThrow(() -> new CompanyNotFoundException());
        CompanyModel updatedCompany = companyMapper.toUpdateCompany(updateDTO, existingCompany);
        updatedCompany = companyRepository.save(updatedCompany);
        return companyMapper.toResponseDTO(updatedCompany);
    }

}
