package com.EduTestPortal.db;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.sql.*;

import com.EduTestPortal.model.Quiz;

public class QuizDAO 
{
	
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
	
	public boolean assignQuizToBatch(int qid,String[] batch) 
	{
		String insertQuery="INSERT INTO QUIZ_BATCHES(QID,BATCH) VALUES (?,?)";
		try(Connection con=DBConnection.getConnection();
			PreparedStatement ps=con.prepareStatement(insertQuery);) 
		{
			for(String b : batch)
			{
				ps.setInt(1,qid);
				ps.setString(2,b);
				ps.executeUpdate();
			}
			return true;
			
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
		return false;	
	}

	public  List<Quiz> getQuizzesByTeacherId(int tid) 
	{
		String selectQuery="SELECT QID,TITLE,SUBJECT,CREATED_AT FROM QUIZZES WHERE TID=?";
		ArrayList<Quiz> quizzes=new ArrayList<>();
		try(Connection con=DBConnection.getConnection();
			PreparedStatement ps=con.prepareStatement(selectQuery))
		{
			ps.setInt(1, tid);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				Quiz q=new Quiz();
				q.setQid(rs.getInt("QID"));
				q.setTitle(rs.getString("TITLE"));
				q.setSubject(rs.getString("SUBJECT"));
				q.setCreatedAt(rs.getTimestamp("CREATED_AT"));
				q.setTid(tid);
				quizzes.add(q);
				
			}
			System.out.println("[QuizDAO] Quiz details fetched for TID:"+tid);
			return quizzes;
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
		return Collections.emptyList();
		
	}
	
	public boolean deleteQuiz(int qid)
	{
		String deleteQuery="DELETE FROM QUIZZES WHERE QID=?";
		try(Connection con=DBConnection.getConnection();
			PreparedStatement ps=con.prepareStatement(deleteQuery);)
		{
			ps.setInt(1, qid);
			int success=ps.executeUpdate();
			if(success>0)
			{
				return true;
			}
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
		return false;
		
	}
	
	public boolean updateQuiz(Quiz quiz)
	{
		String updateQuery="UPDATE QUIZZES SET TITLE =?, SUBJECT=? WHERE QID=?";
		try(Connection con=DBConnection.getConnection();
			PreparedStatement ps=con.prepareStatement(updateQuery);)
		{
			ps.setString(1,quiz.getTitle());
			ps.setString(2,quiz.getSubject());
			ps.setInt(3, quiz.getQid());
			int success=ps.executeUpdate();
			if(success>0)
			{
				System.out.println("[QuizDAO] Quiz "+quiz.getQid()+" updated successfully");
				return true;
			}
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
		return false;	
		
	}
	
	
	public Quiz getQuizById(int qid)
	{
		String selectQuery="SELECT * FROM QUIZZES WHERE QID=?";
		
		try(Connection con=DBConnection.getConnection();
				PreparedStatement ps=con.prepareStatement(selectQuery);)
			{
				ps.setInt(1,qid);
				
				ResultSet rs=ps.executeQuery();
				if(rs.next())
				{
					Quiz quiz = new Quiz();
					quiz.setQid(rs.getInt("QID"));
					quiz.setTitle(rs.getString("TITLE"));
					quiz.setSubject(rs.getString("SUBJECT"));
					quiz.setTid(rs.getInt("TID"));
					quiz.setCreatedAt(rs.getTimestamp("CREATED_AT"));
					System.out.println("[QuizDAO] Quiz details fetched for QID:"+qid);
					return quiz;
					
				}
				
				
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
			return null;
		
	}
	
}
