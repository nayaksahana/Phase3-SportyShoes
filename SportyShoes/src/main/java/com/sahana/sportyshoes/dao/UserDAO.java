package com.sahana.sportyshoes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.sahana.sportyshoes.model.Users;

@Repository
public class UserDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public Users authenticate(String emailId, String pwd) {
		String sql = "select * from users where email='" + emailId + "' and pwd='" + pwd + "'";
	    List<Users> users = jdbcTemplate.query(sql, new RowMapper<Users>() {
		
	    	@Override
			public Users mapRow(ResultSet rs, int arg1) throws SQLException {
	    		Users user = new Users();
				user.setUserId(rs.getInt("userId"));
				user.setFname(rs.getString("fname"));
				user.setLname(rs.getString("lname"));
				user.setAddress(rs.getString("address"));
				user.setEmail(emailId);
				user.setPwd(pwd);
				return user;
			}
	    });
	    return users.size() > 0 ? users.get(0) : null;
	}
	
	public List<Users> getAllUsers() {
		String sql = "select * from users";
	    List<Users> users = jdbcTemplate.query(sql, new RowMapper<Users>() {
	    	
	    	@Override
			public Users mapRow(ResultSet rs, int arg1) throws SQLException {
	    		Users user = new Users();
				user.setUserId(rs.getInt("userId"));
				user.setFname(rs.getString("fname"));
				user.setLname(rs.getString("lname"));
				user.setAddress(rs.getString("address"));
				user.setEmail(rs.getString("email"));
				return user;
	    	}
	    });
	    return users;
	}

	public int insertUser(Users user) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql="insert into users (fname, lname, address, email, pwd) values(?, ?, ?, ?, ?)";
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement ps = conn.prepareStatement(sql, new String[] { "user_id" });
				 ps.setString(1,user.getFname());
		          ps.setString(2, user.getLname());
		          ps.setString(3, user.getAddress());
		          ps.setString(4, user.getEmail());
		          ps.setString(5, user.getPwd());
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}

	public List<Users> getUser(String username) {
		String sql = "select * from users where fname like '%"+username+"%' or lname like '%"+username+"%'";
	    List<Users> users = jdbcTemplate.query(sql, new RowMapper<Users>() {
	    	
	    	@Override
			public Users mapRow(ResultSet rs, int arg1) throws SQLException {
	    		Users user = new Users();
				user.setUserId(rs.getInt("userId"));
				user.setFname(rs.getString("fname"));
				user.setLname(rs.getString("lname"));
				user.setAddress(rs.getString("address"));
				user.setEmail(rs.getString("email"));
				return user;
	    	}
	    });
	    return users;
	}

}
