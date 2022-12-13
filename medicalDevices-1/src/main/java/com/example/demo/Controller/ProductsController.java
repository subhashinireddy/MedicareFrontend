package com.example.demo.Controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Entity.Products;
import com.example.demo.Repo.ProductsRepository;
import com.example.demo.helper.Helper;


@RestController
@RequestMapping("/products")
@CrossOrigin("http://localhost:4200/")
public class ProductsController {
	
	@Autowired
	private ProductsRepository prepo;
	
	@PostMapping("/addproduct")
	public Products addproduct(@RequestBody Products p) {
		return prepo.save(p);
		
	}
	
	@GetMapping("/getAll")
	public List<Products> getAllProducts(){
		return prepo.findAll();
	}
	
	@PutMapping("/updateProduct")
	public Products updateProduct(@RequestBody Products u) {
		return prepo.save(u);
	}
	
	@DeleteMapping("/deleteProduct/{id}")
	public String deleteProducts(@PathVariable Long id) {
		
		System.out.println("Id in DeleteProducts:::"+id);
				prepo.deleteById(id);
		return "Product is Deleted";
	} 
	
	@GetMapping("getproduct/{id}")
	public Products getProductById(Long id) {
		return prepo.findById(id).get();

	}
	@PostMapping("/add-bulk-products")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file){
		if(Helper.checkExelFormat(file))
		{
			List<Products> product;
			try {
				product = Helper.convertExcelToListOfProducts(file.getInputStream());
				this.prepo.saveAll(product);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
			return ResponseEntity.ok(Map.of("message","file is uploaded"));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("please upload excel file only");
		
	}
	
	@GetMapping("/getsorted-product")
	public List<Products> getSortedProduct(){
		return prepo.findAll(Sort.by("catagory")); 
	}
}


