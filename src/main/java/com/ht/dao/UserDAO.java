package com.ht.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ht.beans.User;
import com.ht.utility.GlobalResources;

public class UserDAO {

	public User getByUsername(String username){
		User user=null;
		try(
				Connection connection = GlobalResources.getDatasource().getConnection();
				PreparedStatement ps = connection.prepareStatement("select * from users where username=?");
				){
			ps.setString(1,username);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				user=new User(rs.getString("username").trim(),rs.getString("password").trim(),rs.getString("name").trim());
			}
		}catch(SQLException sqlException){
			System.out.println("Exception in class UserDAO : method : getByUsername(String) : "+sqlException);
		}
		return user;
	}
	public boolean insert(User user){
		boolean added= false;
		try(
				Connection connection = GlobalResources.getDatasource().getConnection();
				PreparedStatement ps = connection.prepareStatement("insert into users (username,password,name) values(?,?,?)");
				) {
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.executeUpdate();
			added = true;
		} catch (SQLException e) {
			added = false;
			System.out.println("Exception in class UserDAO : method : insert(User) : "+e);
			e.printStackTrace();
		}
		return added;
	}
}
