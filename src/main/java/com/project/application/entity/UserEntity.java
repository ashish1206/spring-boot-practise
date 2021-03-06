package com.project.application.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="user", schema = "public")
public class UserEntity {

	@Id
	private String email;
	private String username;
	private String password;
	private String phoneNumber;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="email")
	private List<AddressEntity> addresses;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="email")
	private List<UserCartEntity> cart;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="user_email")
	private List<OrderUserMappingEntity> orders;
	
	
	public List<OrderUserMappingEntity> getOrders() {
		return orders;
	}
	public void setOrders(List<OrderUserMappingEntity> orders) {
		this.orders = orders;
	}
	public List<UserCartEntity> getCart() {
		return cart;
	}
	public void setCart(List<UserCartEntity> cart) {
		this.cart = cart;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public List<AddressEntity> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<AddressEntity> addresses) {
		this.addresses = addresses;
	}
	
}
