package com.EduTestPortal.db;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
	
	//Fetch all teachers (for admin dashboard view)
	public List<Teacher> getAllTeachers() {
	    List<Teacher> list = new ArrayList<>();
	    String query = "SELECT * FROM TEACHERS ORDER BY REGISTERED_AT DESC";
	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(query);
	         ResultSet rs = ps.executeQuery()) {
	        while (rs.next()) {
	            Teacher t = extractTeacher(rs);
	            list.add(t);
	        }
	    } catch (SQLException e) {
	        System.out.println("[TeacherDAO] Error fetching all teachers: " + e.getMessage());
	        e.printStackTrace();
	    }
	    return list;
	}

	//Fetch single teacher by ID (for edit page)
	public Teacher getTeacherById(int tid) {
	    Teacher t = null;
	    String query = "SELECT * FROM TEACHERS WHERE TID = ?";
	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(query)) {
	        ps.setInt(1, tid);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            t = extractTeacher(rs);
	        }
	        rs.close();
	    } catch (SQLException e) {
	        System.out.println("[TeacherDAO] Error fetching teacher by ID: " + e.getMessage());
	        e.printStackTrace();
	    }
	    return t;
	}

	
	//Update teacher details (non-password) for admin
	public boolean updateTeacher(Teacher t) {
	    String query = "UPDATE TEACHERS SET NAME=?, EMAIL=?, PHONE=?, SUBJECT=? WHERE TID=?";
	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(query)) {
	        ps.setString(1, t.getName());
	        ps.setString(2, t.getEmail());
	        ps.setString(3, t.getPhone());
	        ps.setString(4, t.getSubject());
	        ps.setInt(5, t.getTid());
	        return ps.executeUpdate() > 0;
	    } catch (SQLException e) {
	        System.out.println("[TeacherDAO] Error updating teacher: " + e.getMessage());
	        e.printStackTrace();
	    }
	    return false;
	}

	
	//Delete teacher by ID
	public boolean deleteTeacher(int tid) {
	    String query = "DELETE FROM TEACHERS WHERE TID = ?";
	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(query)) {
	        ps.setInt(1, tid);
	        return ps.executeUpdate() > 0;
	    } catch (SQLException e) {
	        System.out.println("[TeacherDAO] Error deleting teacher: " + e.getMessage());
	        e.printStackTrace();
	    }
	    return false;
	}

	
	//Update teacher profile including password (for teacher dashboard edit profile)
	public boolean updateTeacherWithPassword(Teacher t) {
	    String query = "UPDATE TEACHERS SET NAME=?, EMAIL=?, PHONE=?, PASSWORD=?, SUBJECT=? WHERE TID=?";
	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(query)) {
	        ps.setString(1, t.getName());
	        ps.setString(2, t.getEmail());
	        ps.setString(3, t.getPhone());
	        ps.setString(4, t.getPassword()); // bcrypt-hashed before calling this
	        ps.setString(5, t.getSubject());
	        ps.setInt(6, t.getTid());
	        return ps.executeUpdate() > 0;
	    } catch (SQLException e) {
	        System.out.println("[TeacherDAO] Error updating teacher with password: " + e.getMessage());
	        e.printStackTrace();
	    }
	    return false;
	}

	
	public int getTeacherCount() {
	    String query = "SELECT COUNT(*) FROM TEACHERS";
	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(query);
	         ResultSet rs = ps.executeQuery()) {
	        if (rs.next()) return rs.getInt(1);
	    } catch (SQLException e) {
	        System.out.println("[TeacherDAO] Error fetching teacher count: " + e.getMessage());
	    }
	    return 0;
	}


}
