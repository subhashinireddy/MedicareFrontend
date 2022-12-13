package com.example.demo.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Cart;
import com.example.demo.Entity.Products;
import com.example.demo.Entity.User;
import com.example.demo.Repo.CartRepository;
import com.example.demo.Repo.ProductsRepository;
import com.example.demo.Repo.UserRepository;



@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartRepository crepo;
	
	@Autowired
	private UserRepository urepo;
	
	@Autowired
private ProductsRepository prepo;
	
	
	@GetMapping("getall/{userId}")
	public List<Cart> getAllByUserCart(Long id) {
		return crepo.findAllByUserId(id);
		//return null;
	}

	@GetMapping("/add/{pid}/{userId}")
	public Cart addToCart(@PathVariable Long pid,@PathVariable Long userId) {
		System.out.println("adding"+pid);  
		System.out.println("adding"+userId);
		
					
		Products product = prepo.findById(pid).get();
		System.out.println("Product"+product);
		User user = urepo.findById(userId).get();
		System.out.println("User"+user);
		
		Cart cart = new Cart(user, product);
		cart.setUnit(1);
		cart.setTotalPrice(cart.getUnit()*product.getPrice());
		//Cart addCart = crepo.addToCart(cart);
			//crepo.save(cart);
		Cart addCart =crepo.save(cart);
		return addCart; 
		
		
	}

	@DeleteMapping("/delete/{cartId}")
	public void deleteCart(Long id) {
		crepo.deleteById(id);
	}
	
	@PutMapping("/updatecart/{cid}")
	public void updateCart(Cart cart, int cid) {
		Cart updated=crepo.findById((long) cid).get();
		updated=cart;
		crepo.save(updated);
		
	}
}
