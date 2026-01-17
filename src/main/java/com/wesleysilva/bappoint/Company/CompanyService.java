package com.wesleysilva.bappoint.Company;

import org.springframework.stereotype.Service;

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
}
