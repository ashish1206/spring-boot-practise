package com.project.application.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.internal.ExceptionConverterImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.application.entity.AddressEntity;
import com.project.application.entity.UserEntity;
import com.project.application.model.Address;
import com.project.application.model.Seller;
import com.project.application.model.User;

@Repository(value="userDAO")
public class UserDAOIml implements UserDAO {

	@Autowired
	EntityManager em;
	
	@Override
	public User checkLogin(String username, String password, String email) {
		// TODO Auto-generated method stub
		String sql;
		if(username != null) {
			sql = "select u from UserEntity u where u.username = '" + username + "' and u.password = '" + password+"'";
		}
		else {
			sql = "select u from UserEntity u where u.email = '" + email + "' and u.password = '" + password+"'";
		}
		UserEntity userEntity = em.createQuery(sql, UserEntity.class).getSingleResult();
		if(userEntity != null) {
			User user = new User();
			user.setUsername(userEntity.getUsername());
			user.setEmail(userEntity.getEmail());
			user.setPhoneNumber(userEntity.getPhoneNumber());
			List<AddressEntity> addressEntity = userEntity.getAddresses();
			List<Address> addresses = new ArrayList<Address>();
			for(AddressEntity ae : addressEntity) {
				Address address = new Address();
				address.setAddressLine1(ae.getAddressLine1());
				address.setAddressLine2(ae.getAddressLine2());
				address.setCity(ae.getCity());
				address.setPin(ae.getPin());
				address.setState(ae.getState());
				addresses.add(address);
			}
			user.setAddresses(addresses);
			return user;
		}
		else {
			return null;
		}
	}

	@Override
	public String addUser(User user) {
		// TODO Auto-generated method stub
		String username = null;
		UserEntity userEntity = new UserEntity();
		userEntity.setUsername(user.getUsername());
		userEntity.setPassword(user.getPassword());
		userEntity.setEmail(user.getEmail());
		userEntity.setPhoneNumber(user.getPhoneNumber());
//		List<AddressEntity> addressEntity = new ArrayList<AddressEntity>();
//		List<Address> addresses = user.getAddresses();
//		for(Address ad : addresses) {
//			AddressEntity ae = new AddressEntity();
//			ae.setAddressLine1(ad.getAddressLine1());
//			ae.setAddressLine2(ad.getAddressLine2());
//			ae.setCity(ad.getCity());
//			ae.setPin(ad.getPin());
//			ae.setState(ad.getState());
//			addressEntity.add(ae);
//		}
//		userEntity.setAddresses(addressEntity);
		em.persist(userEntity);
		username = userEntity.getUsername();
		return username;
	}

	@Override
	public void addUserAddress(Address address, String email) {
		// TODO Auto-generated method stub
		UserEntity ue = em.find(UserEntity.class, email);
		AddressEntity ae = new AddressEntity();
		ae.setAddressLine1(address.getAddressLine1());
		ae.setAddressLine2(address.getAddressLine2());
		ae.setCity(address.getCity());
		ae.setPin(address.getPin());
		ae.setState(address.getState());
		List<AddressEntity> aeList = ue.getAddresses();
		aeList.add(ae);
		ue.setAddresses(aeList);
//		em.persist(ue);
	}
	
}
