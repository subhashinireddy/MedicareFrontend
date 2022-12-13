package com.example.demo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Cart {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="cart_id")
private int id;

@OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
@JoinColumn(nullable = false, name = "user_id")
private User user;

@ManyToOne()
@JoinColumn(name = "product_id")
private Products product;

public Cart(User user, Products product) {
	this.user = user;
	this.product = product;
}

private  Integer unit;

private double totalPrice;


}
