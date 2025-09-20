package com.EduTestPortal.db;
import java.sql.*;

import com.EduTestPortal.model.Teacher;

public class TeacherDAO {
	
	public boolean addTeacher(Teacher teacher){
		
		String insertQuery="INSERT INTO teachers(NAME, EMAIL, PHONE, PASSWORD, SUBJECT) VALUES (?, ?, ?, ?, ?)";    
		try(Connection con=DBConnection.getConnection();
			PreparedStatement ps=con.prepareStatement(insertQuery);) 
		{
			ps.setString(1,teacher.getName());
			ps.setString(2,teacher.getEmail());
			ps.setString(3,teacher.getPhone());
			ps.setString(4,teacher.getPassword());
			ps.setString(5,teacher.getSubject());
			int count=ps.executeUpdate();
			if (count>0)
			{
				return true;
			}
		} 
		
		catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("[TeacherDAO] Registration failed :Duplicate email or phone"+e.getMessage());
			e.printStackTrace();
		}
		
		catch(SQLException e)
		{
			System.out.println("[TeacherDAO] Database error:"+e.getMessage());
			e.printStackTrace();
		}
		
		catch (Exception e) {
			System.out.println("[TeacherDAO] Some error occured :"+e.getMessage());
			e.printStackTrace();
			
		}
		return false;
		
		
	}
	
	//method to extract teacher details
	private Teacher extractTeacher(ResultSet rs) throws SQLException {
	    Teacher t = new Teacher();
	    t.setTid(rs.getInt("TID"));
	    t.setName(rs.getString("NAME"));
	    t.setEmail(rs.getString("EMAIL"));
	    t.setPhone(rs.getString("PHONE"));
	    t.setPassword(rs.getString("PASSWORD"));
	    t.setSubject(rs.getString("SUBJECT"));
	    t.setRegisteredAt(rs.getTimestamp("REGISTERED_AT"));
	    return t;
	}
	//method to find teacher by email
	
	public Teacher getTeacherByEmail(String email)
	{
		String selectQuery="SELECT * FROM TEACHERS WHERE EMAIL=?";
		try(Connection con=DBConnection.getConnection();
			PreparedStatement ps=con.prepareStatement(selectQuery);) {
			
			ps.setString(1,email);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				Teacher teacher=extractTeacher(rs);
				return teacher;
				
			}
			rs.close();
		} 
		
		catch(SQLException e)
		{
			System.out.println("[TeacherDAO] Database error:"+e.getMessage());
			e.printStackTrace();
		}
		catch (Exception e) 
		{
			System.out.println("[TeacherDAO] Some error occured :"+e.getMessage());
			e.printStackTrace();
		}
		return null;
		
	}

}
