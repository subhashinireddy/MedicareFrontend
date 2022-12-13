package com.example.demo.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
	
		

		List<Cart> findAllByUserId(Long id);

	

	


}

