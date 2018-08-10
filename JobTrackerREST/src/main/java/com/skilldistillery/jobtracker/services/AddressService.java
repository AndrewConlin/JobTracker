package com.skilldistillery.jobtracker.services;

import com.skilldistillery.jobtracker.entites.Address;

public interface AddressService {
	Address findById(int id);
	Address create(Address address);
	Address update(Address address, int aid);
	Boolean delete(int id);
}
