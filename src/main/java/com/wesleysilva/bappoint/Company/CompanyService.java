package com.wesleysilva.bappoint.Company;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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


    public CompanyDTO createCompany(CompanyDTO companyDTO) {
        CompanyModel companyModel = companyMapper.toEntity(companyDTO);
        companyModel = companyRepository.save(companyModel);

        return companyMapper.toDto(companyModel);
    }

    public List<CompanyDTO> listCompanies() {
       List<CompanyModel> companyModel = companyRepository.findAll();
        return companyModel.stream()
                .map(companyMapper::toDto)
                .collect(Collectors.toList());
    }

    public CompanyDTO getCompanyById(UUID id) {
        CompanyModel companyModel = companyRepository.findById(id).orElse(null);
        assert companyModel != null;
        return companyMapper.toDto(companyModel);
    }

    void deleteCompany(UUID id) {
        companyRepository.deleteById(id);
    }

    public CompanyDTO updateCompany(UUID id, CompanyDTO companyDTO) {
        Optional<CompanyModel> companyModel = companyRepository.findById(id);
        if (companyModel.isPresent()) {
            CompanyModel companyUpdated = companyMapper.toEntity(companyDTO);
            companyUpdated.setId(id);
            CompanyModel updatedCompanyModel = companyRepository.save(companyUpdated);
            return companyMapper.toDto(updatedCompanyModel);
        }
        return null;
    }

}
