package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Admin;
import com.example.demo.Repo.AdminRepository;



@RestController
@RequestMapping("/admin")
@CrossOrigin("http://localhost:4200/")
public class AdminController { 
	
	@Autowired 
	private	AdminRepository arepo;
	
	@PostMapping("/add-admin")
	public Admin addAdmin(@RequestBody Admin s) {
		return arepo.save(s);
	}
	
	@GetMapping("/getAll")
		public List<Admin> getAllAdmins(){
		return arepo.findAll();   
	}
	

}
