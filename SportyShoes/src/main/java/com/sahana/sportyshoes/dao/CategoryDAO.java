package com.sahana.sportyshoes.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sahana.sportyshoes.model.Category;

@Repository
public class CategoryDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	public Category getCategoryById(int categoryId) {
		String sql = "select * from category where id='" + categoryId + "'";
	    List<Category> category = jdbcTemplate.query(sql, new RowMapper<Category>() {
		
	    	@Override
			public Category mapRow(ResultSet rs, int arg1) throws SQLException {
	    		Category category = new Category();
				category.setId(rs.getInt("id"));
				category.setName(rs.getString("name"));
				return category;
			}
	    });
	    return category.size() > 0 ? category.get(0) : null;
	}


	public List<Category> getCategories() {
		String sql = "select * from category";
	    List<Category> category = jdbcTemplate.query(sql, new RowMapper<Category>() {
		
	    	@Override
			public Category mapRow(ResultSet rs, int arg1) throws SQLException {
	    		Category category = new Category();
				category.setId(rs.getInt("id"));
				category.setName(rs.getString("name"));
				return category;
			}
	    });
	    return category;
	}

}
