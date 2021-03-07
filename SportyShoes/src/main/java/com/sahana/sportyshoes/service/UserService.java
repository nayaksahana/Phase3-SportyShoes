package com.sahana.sportyshoes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sahana.sportyshoes.dao.UserDAO;
import com.sahana.sportyshoes.model.Users;

@Component("userService")

@Service
public class UserService {
	
	@Autowired
	 public UserDAO userDAO;
	 
	 
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}


	@Transactional
		public Users authenticate(String userId, String pwd) {
			return userDAO.authenticate(userId, pwd);
	}


	public List<Users> getAllUsers() {
		
		return userDAO.getAllUsers();
	}


	public int insertUser(Users user) {
		return userDAO.insertUser(user);
	}


	public List<Users> getUser(String username) {
		return userDAO.getUser(username);
	}


	/*public int insertUser(Users user) {
		return userDAO.insertUser(user);
	}*/
}
