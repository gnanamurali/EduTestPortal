package com.EduTestPortal.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import com.EduTestPortal.model.Admin;
import com.EduTestPortal.model.Student;

public class AdminDAO 
{
	public boolean addAdmin(Admin admin)
	{
		String insertQuery="INSERT INTO ADMINS (NAME,EMAIL,PASSWORD) VALUES (?,?,?);";
		try(Connection con=DBConnection.getConnection();
			PreparedStatement ps=con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);)
		{
			ps.setString(1,admin.getName());
			ps.setString(2,admin.getEmail());
			ps.setString(3,admin.getPassword());
			
			int success=ps.executeUpdate();
			if(success>0)
			{
				ResultSet rs=ps.getGeneratedKeys();
				if (success > 0 && rs.next()) {
				    System.out.println("[AdminDAO] Admin added successfully");
				    System.out.println("[AdminDAO] Generated AdminId = A" + rs.getInt(1));
				    return true;
				}

				rs.close();
				
			}
		}
		catch (SQLIntegrityConstraintViolationException e) 
		{
			e.printStackTrace();
			System.out.println("[AdminDAO] Insertion failed :"+e.getMessage());
		}
		catch(SQLException e)
		{
			System.out.println("[AdminDAO] Database error:"+e.getMessage());
			e.printStackTrace();
		}
			
		catch (Exception e) 
		{
			
			System.out.println("[AdminDAO] Some error occured :"+e.getMessage());
			e.printStackTrace();
		}
		return false;
		
	}
	
	public Admin getAdminByEmail(String email)
	{
		String selectQuery="SELECT * FROM ADMINS WHERE EMAIL = ?;";
		try(Connection con=DBConnection.getConnection();
				PreparedStatement ps=con.prepareStatement(selectQuery);)
			{
				ps.setString(1, email);
				ResultSet rs=ps.executeQuery();
				
				if(rs.next())
				{
					Admin admin=new Admin();
					admin.setAid(rs.getInt("aid"));
					admin.setName(rs.getString("name"));
					admin.setEmail(rs.getString("email"));
					admin.setPassword(rs.getString("password"));
					admin.setRegisteredAt(rs.getTimestamp("registered_at"));
					rs.close();
					return admin ;	
				}
			} 
		catch(SQLException e)
		{
			System.out.println("[AdminDAO] Database error:"+e.getMessage());
			e.printStackTrace();
		}
			
		catch (Exception e) 
		{
			
			System.out.println("[AdminDAO] Some error occured :"+e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

}
