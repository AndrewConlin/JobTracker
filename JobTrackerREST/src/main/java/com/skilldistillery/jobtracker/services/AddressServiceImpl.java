package com.skilldistillery.jobtracker.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.jobtracker.entites.Address;
import com.skilldistillery.jobtracker.repositories.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService{
	
	@Autowired
	private AddressRepository addressRepo;

	@Override
	public Address findById(int aid) {
		Optional<Address> optAdd = addressRepo.findById(aid);
		if(optAdd.isPresent()) {
			return optAdd.get();
		}
		return null;
	}

	@Override
	public Address create(Address address) {
		return addressRepo.saveAndFlush(address);
	}

	@Override
	public Address update(Address address, int aid) {
		address.setId(aid);
		return addressRepo.saveAndFlush(address);
	}

	@Override
	public Boolean delete(int aid) {
		Optional<Address> optAdd = addressRepo.findById(aid);
		if(optAdd.isPresent()) {
			addressRepo.delete(optAdd.get());
			return true;
		}
		return false;
	}

}
