package com.sahana.sportyshoes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sahana.sportyshoes.dao.AdminDAO;
import com.sahana.sportyshoes.model.Admin;

@Component
public class AdminService {

	@Autowired
	public AdminDAO adminDAO;
	
	public void setAdminDAO(AdminDAO adminDAO) {
		this.adminDAO = adminDAO;
	}

	@Transactional
		public Admin authenticate(String userId, String pwd) {
			return adminDAO.authenticate(userId, pwd);
	}

	@Transactional
	public Admin getAdminById(int id) {
		return adminDAO.getAdminById(id);
	}

	@Transactional
	public void updatePwd(Admin admin) {
		adminDAO.updatePwd(admin);
		
	}
}
