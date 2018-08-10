package com.skilldistillery.jobtracker.services;

import com.skilldistillery.jobtracker.entites.Company;

public interface CompanyService {
	Company findCompanyById(int id);
	Company create(Company company);
	Company update(Company company, int cid);
	Boolean delete(int cid);
}
