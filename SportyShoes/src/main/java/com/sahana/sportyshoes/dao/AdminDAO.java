package com.sahana.sportyshoes.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sahana.sportyshoes.model.Admin;

@Repository
public class AdminDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public Admin authenticate(String emailId, String pwd) {	
		String sql = "select * from admin where adminEmail='" + emailId + "' and adminPwd='" + pwd + "'";
	    List<Admin> admin = jdbcTemplate.query(sql, new RowMapper<Admin>() {

	    	@Override
	    	public Admin mapRow(ResultSet rs, int arg1) throws SQLException {
	    		Admin admin = new Admin();
	    		admin.setId(Integer.parseInt(rs.getString("id")));
	    		admin.setAdminEmail(rs.getString("adminEmail"));
				admin.setAdminPwd(rs.getString("adminPwd"));
				return admin;
	    	}
	    });
	    return admin.size() > 0 ? admin.get(0) : null;
	}

	public Admin getAdminById(int id) {
		String sql = "select * from admin where id='" + id + "'";
	    List<Admin> admin = jdbcTemplate.query(sql, new RowMapper<Admin>() {
	    	@Override
			public Admin mapRow(ResultSet rs, int arg1) throws SQLException {
	    		Admin admin = new Admin();
	    		admin.setAdminEmail(rs.getString("adminEmail"));
				admin.setAdminPwd(rs.getString("adminPwd"));
				return admin;
			}    	
	    });
	    return admin.size() > 0 ? admin.get(0) : null;
	}
	
	public void updatePwd(Admin admin) {
		String query="update admin set adminPwd='" +admin.getAdminPwd() + "' where id='"+admin.getId()+"'";  
		jdbcTemplate.update(query);  
	}  
}
