package com.skilldistillery.jobtracker.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.jobtracker.entites.Address;
import com.skilldistillery.jobtracker.entites.Company;
import com.skilldistillery.jobtracker.repositories.AddressRepository;
import com.skilldistillery.jobtracker.repositories.CompanyRepository;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepo;
	

	@Autowired
	private AddressRepository addressRepo;
	
	@Override
	public Company findCompanyById(int id) {
		Optional<Company> optCompany = companyRepo.findById(id);
		
		if(optCompany.isPresent()) {
			Company company = optCompany.get();
			return company;
		}
		return null;
	}

	@Override
	public Company create(Company company) {
		return companyRepo.saveAndFlush(company);
	}

	@Override
	public Company update(Company company, int cid) {
	Optional<Company> optCompany = companyRepo.findById(cid);
		
		if(optCompany.isPresent()) {
			Company managed = optCompany.get();
			managed.setName(company.getName());
			managed.setUrl(company.getUrl());
			if(managed.getAddress() == null) {
				Address managedAdd = addressRepo.saveAndFlush(company.getAddress());
				managed.setAddress(managedAdd);
			}
			else {
				managed.setAddress(company.getAddress());
			}
			
			return companyRepo.saveAndFlush(managed);
		}
		
		return null;
	}

	@Override
	public Boolean delete(int cid) {
	Optional<Company> optCompany = companyRepo.findById(cid);
		
		if(optCompany.isPresent()) {
			Company company = optCompany.get();
			companyRepo.delete(company);
			return true;
		}
		return false;
	}

}
