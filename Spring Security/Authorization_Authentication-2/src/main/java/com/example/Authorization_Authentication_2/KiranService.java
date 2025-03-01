package com.example.Authorization_Authentication_2;

import java.util.List;

public interface KiranService{
	

	public Kiran saveDetails(Kiran k);
	
	public List<Kiran> getAllDetails();
	
	public Kiran getDetailsbyId(Integer id);
	
	
}
