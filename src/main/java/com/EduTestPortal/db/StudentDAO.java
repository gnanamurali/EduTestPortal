package com.EduTestPortal.db;


import java.sql.*;

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
	
	
	
}
