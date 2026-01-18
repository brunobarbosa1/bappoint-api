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
        CompanyModel companyModel = companyMapper.map(companyDTO);
        companyModel = companyRepository.save(companyModel);

        return companyMapper.map(companyModel);
    }

    public List<CompanyDTO> listCompanies() {
       List<CompanyModel> companyModel = companyRepository.findAll();
        return companyModel.stream()
                .map(companyMapper::map)
                .collect(Collectors.toList());
    }

    public CompanyDTO getCompanyById(UUID id) {
        CompanyModel companyModel = companyRepository.findById(id).orElse(null);
        assert companyModel != null;
        return companyMapper.map(companyModel);
    }

    void deleteCompany(UUID id) {
        companyRepository.deleteById(id);
    }

    public CompanyDTO updateCompany(UUID id, CompanyDTO companyDTO) {
        Optional<CompanyModel> companyModel = companyRepository.findById(id);
        if (companyModel.isPresent()) {
            CompanyModel companyUpdated = companyMapper.map(companyDTO);
            companyUpdated.setId(id);
            CompanyModel updatedCompanyModel = companyRepository.save(companyUpdated);
            return companyMapper.map(updatedCompanyModel);
        }
        return null;
    }

}
