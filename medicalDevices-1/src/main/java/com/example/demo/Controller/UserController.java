package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.User;
import com.example.demo.Repo.UserRepository;


@RestController
@RequestMapping("/user")
@CrossOrigin("http://localhost:4200/")
public class UserController {
	
	@Autowired
	private UserRepository urepo;
	
	@PostMapping("/adduser")
	public User user(@RequestBody User u) {
		return urepo.save(u);
		
	}
	
	@GetMapping("/getAll")
	public List<User> getAlluser(){
		return urepo.findAll();
	}
	
	@PutMapping("/updateUser")
	public User updateUser(@RequestBody User s) {
		return urepo.save(s); 
	}
	
	@DeleteMapping("/deleteUser/{id}")
	public void deleteUser (@PathVariable Long id) {
		 urepo.deleteById(id); 
	
	}
	
	@GetMapping("/getuser/{id}")
	public User usergetbyid(@PathVariable Long id) {
		return urepo.findById(id).get();
	}

}