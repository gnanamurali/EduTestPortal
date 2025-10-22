package com.EduTestPortal.db;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.EduTestPortal.model.Student;

public class StudentDAO {
	
	
	//Method for adding student(Registration)
	public boolean addStudent(Student student)
	{
		
		String insertQuery="INSERT INTO students(name, email, phone, password, department, year_of_study, batch) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try(Connection con=DBConnection.getConnection();
			PreparedStatement ps=con.prepareStatement(insertQuery);) 
		{
			ps.setString(1,student.getName());
			ps.setString(2,student.getEmail());
			ps.setString(3,student.getPhone());
			ps.setString(4,student.getPassword());
			ps.setString(5,student.getDepartment());
			ps.setInt(6, student.getYearOfStudy());
			ps.setString(7,student.getBatch());
			
			int count  =ps.executeUpdate();
			if (count>0) {
				return true; 
			}
		}
			
		catch(SQLIntegrityConstraintViolationException e)
		{
				System.out.println("[StudentDAO] Registration failed :"+e.getMessage());
				e.printStackTrace();
		}
		
		catch(SQLException e)
		{
			System.out.println("[StudentDAO] Database error:"+e.getMessage());
			e.printStackTrace();
		}
			
		catch (Exception e) 
		{
			
			System.out.println("[StudentDAO] Some error occured :"+e.getMessage());
			e.printStackTrace();
		}
		return false;

	}
	
	//Method to get student with email id
	
	public Student getStudentByEmail(String email)
	{
		String selectQuery="SELECT * FROM STUDENTS WHERE EMAIL=?";
		try(Connection con=DBConnection.getConnection();
			PreparedStatement ps=con.prepareStatement(selectQuery);)
		{
			ps.setString(1, email);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next())
			{
				Student student=new Student();
				student.setSid(rs.getInt("sid"));
				student.setName(rs.getString("name"));
				student.setEmail(rs.getString("email"));
				student.setPhone(rs.getString("phone"));
				student.setPassword(rs.getString("password"));
				student.setDepartment(rs.getString("department"));
				student.setYearOfStudy(rs.getInt("year_of_study"));
				student.setBatch(rs.getString("batch"));
				student.setRegisteredAt(rs.getTimestamp("registered_at"));
				return student;	
			}
			rs.close();
		} 
		catch(SQLException e)
		{
			System.out.println("[StudentDAO] Database error:"+e.getMessage());
			e.printStackTrace();
		}
			
		catch (Exception e) 
		{
			
			System.out.println("[StudentDAO] Some error occured :"+e.getMessage());
			e.printStackTrace();
		}
		return null;

	}
	
	//Fetch all students (for admin dashboard view)
	public List<Student> getAllStudents() {
	    List<Student> list = new ArrayList<>();
	    String query = "SELECT * FROM STUDENTS ORDER BY registered_at DESC";
	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(query);
	         ResultSet rs = ps.executeQuery()) {
	        while (rs.next()) {
	            Student s = new Student();
	            s.setSid(rs.getInt("sid"));
	            s.setName(rs.getString("name"));
	            s.setEmail(rs.getString("email"));
	            s.setPhone(rs.getString("phone"));
	            s.setDepartment(rs.getString("department"));
	            s.setYearOfStudy(rs.getInt("year_of_study"));
	            s.setBatch(rs.getString("batch"));
	            s.setRegisteredAt(rs.getTimestamp("registered_at"));
	            list.add(s);
	        }
	    } catch (SQLException e) {
	        System.out.println("[StudentDAO] Error fetching all students: " + e.getMessage());
	        e.printStackTrace();
	    }
	    return list;
	}
	
	
	//Fetch single student by ID (for edit page)
	public Student getStudentById(int sid) {
	    Student s = null;
	    String query = "SELECT * FROM STUDENTS WHERE SID = ?";
	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(query)) {
	        ps.setInt(1, sid);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            s = new Student();
	            s.setSid(rs.getInt("sid"));
	            s.setName(rs.getString("name"));
	            s.setEmail(rs.getString("email"));
	            s.setPhone(rs.getString("phone"));
	            s.setDepartment(rs.getString("department"));
	            s.setYearOfStudy(rs.getInt("year_of_study"));
	            s.setBatch(rs.getString("batch"));
	            s.setRegisteredAt(rs.getTimestamp("registered_at"));
	        }
	        rs.close();
	    } catch (SQLException e) {
	        System.out.println("[StudentDAO] Error fetching student by ID: " + e.getMessage());
	        e.printStackTrace();
	    }
	    return s;
	}
	
	//Update student details (non-password fields)
	public boolean updateStudent(Student s) {
	    String query = "UPDATE STUDENTS SET NAME=?, EMAIL=?, PHONE=?, DEPARTMENT=?, YEAR_OF_STUDY=?, BATCH=? WHERE SID=?";
	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(query)) {
	        ps.setString(1, s.getName());
	        ps.setString(2, s.getEmail());
	        ps.setString(3, s.getPhone());
	        ps.setString(4, s.getDepartment());
	        ps.setInt(5, s.getYearOfStudy());
	        ps.setString(6, s.getBatch());
	        ps.setInt(7, s.getSid());
	        return ps.executeUpdate() > 0;
	    } catch (SQLException e) {
	        System.out.println("[StudentDAO] Error updating student: " + e.getMessage());
	        e.printStackTrace();
	    }
	    return false;
	}
	
	//Delete student by ID
	public boolean deleteStudent(int sid) {
	    String query = "DELETE FROM STUDENTS WHERE SID = ?";
	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(query)) {
	        ps.setInt(1, sid);
	        return ps.executeUpdate() > 0;
	    } catch (SQLException e) {
	        System.out.println("[StudentDAO] Error deleting student: " + e.getMessage());
	        e.printStackTrace();
	    }
	    return false;
	}
	
	//Update student profile including password (for Student Dashboard Edit Profile)
	public boolean updateStudentWithPassword(Student s) {
	    String query = "UPDATE STUDENTS SET NAME=?, EMAIL=?, PHONE=?, PASSWORD=?, DEPARTMENT=?, YEAR_OF_STUDY=?, BATCH=? WHERE SID=?";
	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(query)) {
	        ps.setString(1, s.getName());
	        ps.setString(2, s.getEmail());
	        ps.setString(3, s.getPhone());
	        ps.setString(4, s.getPassword());  // <-- bcrypt-hashed before calling this method
	        ps.setString(5, s.getDepartment());
	        ps.setInt(6, s.getYearOfStudy());
	        ps.setString(7, s.getBatch());
	        ps.setInt(8, s.getSid());
	        return ps.executeUpdate() > 0;
	    } catch (SQLException e) {
	        System.out.println("[StudentDAO] Error updating student with password: " + e.getMessage());
	        e.printStackTrace();
	    }
	    return false;
	}
	
	
	//Fetch students by batch (for filtering)
	public List<Student> getStudentsByBatch(String batch) {
	    List<Student> list = new ArrayList<>();
	    String query = "SELECT * FROM STUDENTS WHERE BATCH = ? ORDER BY registered_at DESC";
	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(query)) {
	        ps.setString(1, batch);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            Student s = new Student();
	            s.setSid(rs.getInt("sid"));
	            s.setName(rs.getString("name"));
	            s.setEmail(rs.getString("email"));
	            s.setPhone(rs.getString("phone"));
	            s.setDepartment(rs.getString("department"));
	            s.setYearOfStudy(rs.getInt("year_of_study"));
	            s.setBatch(rs.getString("batch"));
	            s.setRegisteredAt(rs.getTimestamp("registered_at"));
	            list.add(s);
	        }
	        rs.close();
	    } catch (SQLException e) {
	        System.out.println("[StudentDAO] Error fetching students by batch: " + e.getMessage());
	        e.printStackTrace();
	    }
	    return list;
	}
	
	
	public int getStudentCount() {
	    String query = "SELECT COUNT(*) FROM STUDENTS";
	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(query);
	         ResultSet rs = ps.executeQuery()) {
	        if (rs.next()) return rs.getInt(1);
	    } catch (SQLException e) {
	        System.out.println("[StudentDAO] Error fetching student count: " + e.getMessage());
	    }
	    return 0;
	}






	
	
}
