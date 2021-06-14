package com.mvc.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jdbc.web.entity.content.notice;
import com.jdbc.web.entity.user.Login;
import com.jdbc.web.entity.user.Register;
import com.jdbc.web.entity.user.User;
import com.mvc.web.connection.ConnectionProvider;
import com.mvc.web.connection.jdbcUtil;

public class UserDAO {
	
	
	private static UserDAO instance = new UserDAO();

	public static UserDAO getInstance() {
		return instance;
	}



	public User logincheck(Login lg) {
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		User ur=new User();
		
		String sql = "select userID, userPass, userName, userEmail, userRank "
				+ " from user"
				+ " where FLAG='Y' "
				+ " and userID=? "
				+ " and userPass =SHA2(?,256) ";

		int result = 0;
		try {
			System.out.println("id :"+lg.getId());
			System.out.println("pass :"+lg.getPass());
			
			
			con =ConnectionProvider.getConnection();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, lg.getId());
			psmt.setString(2, lg.getPass());
			System.out.println("psmt : " +psmt);
			rs = psmt.executeQuery();

			if (rs.next()) {

				ur.setId(lg.getId());
				ur.setName(rs.getString("userName"));
				ur.setRank(rs.getString("userRank"));
				ur.setEmail(rs.getString("userEmail"));
				ur.setNumber(1);
				
				} else {
					ur.setNumber(0);
		
			} 

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbcUtil.close(con);
			jdbcUtil.close(psmt);
			jdbcUtil.close(rs);
		}
		return ur;
	}

	

	public int idCheck(String pid) {
		
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int result=0;
		
		String sql="select userID from user where userID=?";
		
		try {
			con =ConnectionProvider.getConnection();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, pid);

			rs = psmt.executeQuery();
			
			if(rs.next()) {
				 result=1;
			}else {
				result=0;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	//사용자 추가
	public int signUp(Register rt) {
		Connection con = null;
		PreparedStatement psmt = null;
	
		
		int result=0;
		String sql="insert into user (userID,userPass,userName,userEmail) "
				+ "values (?,SHA2(?,256),?,?)";
		
		
		
		try {
			con =ConnectionProvider.getConnection();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, rt.getId());
			psmt.setString(2, rt.getPass());
			psmt.setString(3, rt.getName());
			psmt.setString(4, rt.getEmail());
			System.out.println("psmt aaaaaaa:" +psmt);

			result = psmt.executeUpdate();
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}


	//접속자 정보 입력
	public void setLoginInfo(String userID, String userIP) {
		
		Connection con = null;
		PreparedStatement psmt = null;
	
		
		int result=0;
		String sql=" insert into user_swap(userID,userIP,outTime,division) "
				+ " values(?,?,?,?) ";
		
		
		
		try {
			con =ConnectionProvider.getConnection();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, userID);
			psmt.setString(2, userIP);
			psmt.setString(3, null);
			psmt.setString(4, "I");
		

			result = psmt.executeUpdate();
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbcUtil.close(con);
			jdbcUtil.close(psmt);
			
		}
		
	}
}

