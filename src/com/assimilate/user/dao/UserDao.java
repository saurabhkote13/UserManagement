package com.assimilate.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.assimilate.user.models.User;

//will responsible for performing user related database operation
public class UserDao {

	public int addUser(User u) {

		Connection con = DbConnection.getConnection();
		// create query
		// create prepared statement
		// executeupdate
		//
		int status = -1; //no records updated
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("insert into userdetails(name,password,email,sex,country) values(?,?,?,?,?)");
			ps.setString(1, u.getName());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getEmail());
			ps.setString(4, u.getSex());
			ps.setString(5, u.getCountry());
			status = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return status;
	}

	public List<User> getAllUsers() {
		List<User> list = new ArrayList<User>();
		try {
			Connection con = DbConnection.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from userdetails");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User u = new User();
				u.setId(rs.getInt("id"));
				u.setName(rs.getString("name"));
				u.setPassword(rs.getString("password"));
				u.setEmail(rs.getString("email"));
				u.setSex(rs.getString("sex"));
				u.setCountry(rs.getString("country"));
				list.add(u);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}

	public int deleteUser(int id) {
		int status = -1; //no records updated
		Connection con = DbConnection.getConnection();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("delete from userdetails where id=?");
			ps.setInt(1, id);
			status = ps.executeUpdate();
			System.out.println("User deleted successfully..");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return status;
	}

	public User getUserById(String id) {
		User userFromDb = null;
		try {
			Connection con = DbConnection.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from userdetails where id=?");
			ps.setInt(1, Integer.parseInt(id));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				userFromDb = new User();
				userFromDb.setId(rs.getInt("id"));
				userFromDb.setName(rs.getString("name"));
				userFromDb.setPassword(rs.getString("password"));
				userFromDb.setEmail(rs.getString("email"));
				userFromDb.setSex(rs.getString("sex"));
				userFromDb.setCountry(rs.getString("country"));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return userFromDb;
	}

	public int updateUser(User u) {
		Connection con = DbConnection.getConnection();
		// create query
		// create prepared statement
		// executeupdate
		//
		int status = -1; //no records updated
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("update userdetails set name =?, password=?, email=?, sex=?, country =? where id=? ");
			ps.setString(1, u.getName());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getEmail());
			ps.setString(4, u.getSex());
			ps.setString(5, u.getCountry());
			ps.setInt(6, u.getId());
			status = ps.executeUpdate();
			System.out.println("User updated successfuly..");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return status;
	}

}
