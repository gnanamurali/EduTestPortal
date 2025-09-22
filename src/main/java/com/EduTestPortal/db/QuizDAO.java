package com.EduTestPortal.db;
import java.sql.*;

import com.EduTestPortal.model.Quiz;

public class QuizDAO {
	
	public int addQuiz(Quiz quiz)
	{
		String insertQuery="INSERT INTO QUIZZES (TITLE,SUBJECT,TID) VALUES (?,?,?)"; 
		try(Connection con=DBConnection.getConnection();
			PreparedStatement ps=con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS))
		{
			ps.setString(1, quiz.getTitle());
			ps.setString(2, quiz.getSubject());
			ps.setInt(3, quiz.getTid());
			int success=ps.executeUpdate();
			ResultSet rs=ps.getGeneratedKeys();
			
			if(success>0)
			{
				if(rs.next())
				{
					return rs.getInt(1);
				}
			}	
			rs.close();
		}
		
		catch (SQLIntegrityConstraintViolationException e) 
		{
			e.printStackTrace();
			System.out.println("[QuizDAO] Insertion failed :"+e.getMessage());
		}
		catch(SQLException e)
		{
			System.out.println("[QuizDAO] Database error:"+e.getMessage());
			e.printStackTrace();
		}
			
		catch (Exception e) 
		{
			
			System.out.println("[QuizDAO] Some error occured :"+e.getMessage());
			e.printStackTrace();
		}
		return -1;
	}
	

}
