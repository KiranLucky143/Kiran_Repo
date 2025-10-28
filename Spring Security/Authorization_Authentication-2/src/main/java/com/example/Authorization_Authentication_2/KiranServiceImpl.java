package com.example.Authorization_Authentication_2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KiranServiceImpl implements KiranService{

	@Autowired
	KiranRepository kiranRepository;
	
	@Override
	public Kiran saveDetails(Kiran user) {
		
		return kiranRepository.save(user);
	}

	@Override
	public List<Kiran> getAllDetails() {
	
		
		return kiranRepository.findAll();
	}

	@Override
	public Kiran getDetailsbyId(Integer id) {
		
		
		return kiranRepository.findById(id).get();
	}

	
}
