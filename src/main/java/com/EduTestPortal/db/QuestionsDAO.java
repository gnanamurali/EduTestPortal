package com.EduTestPortal.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.EduTestPortal.model.Question;

public class QuestionsDAO 
{
	
	public int addQuestion(Question q)
	{
		String insertQuery="INSERT INTO QUESTIONS (QID, QUE_TEXT, OPT_A, OPT_B, OPT_C, OPT_D, ANSWER) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try(Connection con=DBConnection.getConnection();
			PreparedStatement ps=con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);)
			{
				ps.setInt(1,q.getQid());
				ps.setString(2,q.getQuestionText());
				ps.setString(3,q.getOptionA());
				ps.setString(4,q.getOptionB());
				ps.setString(5,q.getOptionC());
				ps.setString(6,q.getOptionD());
				ps.setString(7,q.getCorrectOption());
				int success=ps.executeUpdate();
				if(success>0)
				{
					ResultSet rs=ps.getGeneratedKeys();
					if(rs.next())
					{
						System.out.println("[QuestionDAO] Question added successfully");
						System.out.println("[QuestionDAO] Inserted, generated key = " + rs.getInt(1));
						return rs.getInt(1);
					}
					rs.close();
					
				}
				
			}
		catch (SQLIntegrityConstraintViolationException e) 
		{
			e.printStackTrace();
			System.out.println("[QuestionDAO] Insertion failed :"+e.getMessage());
		}
		catch(SQLException e)
		{
			System.out.println("[QuestionDAO] Database error:"+e.getMessage());
			e.printStackTrace();
		}
			
		catch (Exception e) 
		{
			
			System.out.println("[QuestionDAO] Some error occured :"+e.getMessage());
			e.printStackTrace();
		}
		return -1;
	}
	
	public List<Question> getQuestionsByQuizId(int qid)
	{
		String selectQuery="SELECT * FROM QUESTIONS WHERE QID = ? ORDER BY QUE_ID ASC;";
		ArrayList<Question> questions=new ArrayList<>();
		
		try(Connection con=DBConnection.getConnection();
			PreparedStatement ps=con.prepareStatement(selectQuery);)
		{
			ps.setInt(1, qid);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				Question q=new Question();
				q.setQueId(rs.getInt("QUE_ID"));
				q.setQid(qid);
				q.setQuestionText(rs.getString("QUE_TEXT"));
				q.setOptionA(rs.getString("OPT_A"));
				q.setOptionB(rs.getString("OPT_B"));
				q.setOptionC(rs.getString("OPT_C"));
				q.setOptionD(rs.getString("OPT_D"));
				q.setCorrectOption(rs.getString("ANSWER"));
				questions.add(q);
			}
			System.out.println("[QuestionDAO] Questions fetched for qid: "+qid);
			return questions;
			
		}
		catch(SQLException e)
		{
			System.out.println("[QuestionDAO] Database error:"+e.getMessage());
			e.printStackTrace();
		}
			
		catch (Exception e) 
		{
			
			System.out.println("[QuestionDAO] Some error occured :"+e.getMessage());
			e.printStackTrace();
		}
		return Collections.emptyList();
		
	}
	
	public boolean deleteQuestion(int queId)
	{
		 String deleteQuery="DELETE FROM QUESTIONS WHERE QUE_ID = ?;";
		 try(Connection con=DBConnection.getConnection();
			PreparedStatement ps=con.prepareStatement(deleteQuery);)
		{
			 ps.setInt(1,queId);
			 int delete=ps.executeUpdate();
			 
			 if(delete>0)
			 {
				 System.out.println("[QuestionDAO] Question "+queId+" was deleted");
				 return true;
			 }
		}
		catch(SQLException e)
			{
				System.out.println("[QuestionDAO] Database error:"+e.getMessage());
				e.printStackTrace();
			}
					
		catch (Exception e) 
		 	{
					
				System.out.println("[QuestionDAO] Some error occured Question: "+queId+" was not deleted: "+e.getMessage());
				e.printStackTrace();
			}
		 	return false;
			 
	    }
	
	public Question getQuestionById(int queid)
	{
		String selectQuery="SELECT * FROM QUESTIONS WHERE QUE_ID = ?;";
		 try(Connection con=DBConnection.getConnection();
			PreparedStatement ps=con.prepareStatement(selectQuery);)
		 {
			 ps.setInt(1, queid);
			 ResultSet rs=ps.executeQuery();
			
			 if(rs.next())
			 {
				 Question q= new Question();
				 q.setQueId(queid);
				 q.setQid(rs.getInt("QID"));
				 q.setQuestionText(rs.getString("QUE_TEXT"));
				 q.setOptionA(rs.getString("OPT_A"));
				 q.setOptionB(rs.getString("OPT_B"));
				 q.setOptionC(rs.getString("OPT_C"));
				 q.setOptionD(rs.getString("OPT_D"));
				 q.setCorrectOption(rs.getString("ANSWER"));
				 return q;
				 
			 }
			
		 }
		 
		 catch(SQLException e)
			{
				System.out.println("[QuestionDAO] Database error:"+e.getMessage());
				e.printStackTrace();
			}
					
		catch (Exception e) 
		 	{
					
				System.out.println("[QuestionDAO] Could not find Question "+queid+": "+e.getMessage());
				e.printStackTrace();
			}
		 	return null;
			 
	    }
	
	public boolean updateQuestion(Question q)
	{
		String updateQuery="UPDATE QUESTIONS SET QUE_TEXT = ?,OPT_A = ?,OPT_B = ?,OPT_C = ?,OPT_D = ?,ANSWER = ? WHERE QUE_ID = ?;";
		try(Connection con=DBConnection.getConnection();
			PreparedStatement ps=con.prepareStatement(updateQuery);)
			{
				ps.setString(1,q.getQuestionText());
				ps.setString(2,q.getOptionA());
				ps.setString(3,q.getOptionB());
				ps.setString(4,q.getOptionC());
				ps.setString(5,q.getOptionD());
				ps.setString(6,q.getCorrectOption());
				ps.setInt(7, q.getQueId());
				int update=ps.executeUpdate();
				
				if(update>0)
				{
					System.out.println("[QuestionDAO] Question :"+q.getQueId()+" updated successfully");
					return true;
				}
			}
		
		 catch(SQLException e)
		{
			System.out.println("[QuestionDAO] Database error:"+e.getMessage());
			e.printStackTrace();
		}
				
		catch (Exception e) 
	 	{
				
			System.out.println("[QuestionDAO] Question "+q.getQueId()+" updated: "+e.getMessage());
			e.printStackTrace();
		}
		return false;
		
	}
	

	
	

		
	
		 
	

}
