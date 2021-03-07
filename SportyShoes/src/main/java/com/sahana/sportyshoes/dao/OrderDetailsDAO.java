package com.sahana.sportyshoes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.sahana.sportyshoes.model.OrderDetails;
import com.sahana.sportyshoes.model.Users;

@Repository
public class OrderDetailsDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void updateItem(OrderDetails item) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql="insert into orderDetails (productId, userId, rate, price, qty) values(?, ?, ?, ?, ?)";
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement ps = conn.prepareStatement(sql, new String[] { "orderId" });
				 ps.setInt(1,item.getProductId());
		          ps.setInt(2, item.getUserId());
		          ps.setFloat(3, item.getRate());
		          ps.setFloat(4, item.getPrice());
		          ps.setInt(5, item.getQty());
				return ps;
			}
		}, keyHolder);
		int i = keyHolder.getKey().intValue();
		
		/* KeyHolder keyHolder = new GeneratedKeyHolder();

		    jdbcTemplate.update(connection -> {
		        PreparedStatement ps = connection
		          .prepareStatement(query);
		          ps.setInt(1,item.getProductId());
		          ps.setInt(2, item.getUserId());
		          ps.setFloat(3, item.getRate());
		          ps.setFloat(4, item.getPrice());
		          ps.setInt(5, item.getQty());
		          return ps;
		        }, keyHolder);
		    return keyHolder.getKey().longValue();*/
		    }
		/*int purchaseId = Statement.RETUR
		 * N_GENERATED_KEYS;
		String query="insert into orderDetails values("+purchaseId + ","+item.getProductId()+","+item.getUserId()
				+","+item.getRate()+","+item.getQty()+","+item.getPrice()+")";
		jdbcTemplate.update(query);  */

	public List<OrderDetails> getItemsByPurchaseId(int id) {
		String sql = "select * from orderdetails where orderId='" + id + "'";
	    List<OrderDetails> orders = jdbcTemplate.query(sql, new RowMapper<OrderDetails>() {
		
	    	@Override
			public OrderDetails mapRow(ResultSet rs, int arg1) throws SQLException {
	    		OrderDetails orderDetails = new OrderDetails();
	    		orderDetails.setOrderId(rs.getInt("orderId"));
	    		orderDetails.setProductId(rs.getInt("productId"));
	    		orderDetails.setUserId(rs.getInt("userId"));
	    		orderDetails.setRate(rs.getFloat("rate"));
	    		orderDetails.setPrice(rs.getFloat("price"));
	    		orderDetails.setQty(rs.getInt("qty"));
				return orderDetails;
			}
	    });
	    return orders;
	}

	public List<OrderDetails> getOrderDetails(int userId) {
		String sql = "select * from orderdetails where userId='" + userId + "'";
	    List<OrderDetails> orders = jdbcTemplate.query(sql, new RowMapper<OrderDetails>() {
		
	    	@Override
			public OrderDetails mapRow(ResultSet rs, int arg1) throws SQLException {
	    		OrderDetails orderDetails = new OrderDetails();
	    		orderDetails.setOrderId(rs.getInt("orderId"));
	    		orderDetails.setProductId(rs.getInt("productId"));
	    		orderDetails.setUserId(rs.getInt("userId"));
	    		orderDetails.setRate(rs.getFloat("rate"));
	    		orderDetails.setPrice(rs.getFloat("price"));
	    		orderDetails.setQty(rs.getInt("qty"));
				return orderDetails;
			}
	    });
	    return orders;
	}

	public List<OrderDetails> getOrderDetails() {
		String sql = "select * from orderdetails";
		 List<OrderDetails> orders = jdbcTemplate.query(sql, new RowMapper<OrderDetails>() {
		    	
		    	@Override
				public OrderDetails mapRow(ResultSet rs, int arg1) throws SQLException {
		    		OrderDetails order = new OrderDetails();
					order.setUserId(rs.getInt("userId"));
					order.setOrderId(rs.getInt("orderId"));
					order.setProductId(rs.getInt("productId"));
					order.setRate(rs.getFloat("rate"));
					order.setPrice(rs.getFloat("price"));
					order.setQty(rs.getInt("qty"));
		    		
					return order;
		    	}
		    });
		    return orders;
	}

}
