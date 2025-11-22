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
		String insertQuery="INSERT INTO QUIZZES (TITLE,SUBJECT,TID,DURATION) VALUES (?,?,?,?)"; 
		try(Connection con=DBConnection.getConnection();
			PreparedStatement ps=con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS))
		{
			ps.setString(1, quiz.getTitle());
			ps.setString(2, quiz.getSubject());
			ps.setInt(3, quiz.getTid());
			ps.setInt(4, quiz.getDuration());
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

	public List<Quiz> getQuizzesByTeacherId(int tid) {
	    String selectQuery = "SELECT QID, TITLE, SUBJECT, CREATED_AT, IS_VISIBLE FROM QUIZZES WHERE TID=?";
	    ArrayList<Quiz> quizzes = new ArrayList<>();
	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(selectQuery)) {
	        ps.setInt(1, tid);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            Quiz q = new Quiz();
	            q.setQid(rs.getInt("QID"));
	            q.setTitle(rs.getString("TITLE"));
	            q.setSubject(rs.getString("SUBJECT"));
	            q.setCreatedAt(rs.getTimestamp("CREATED_AT"));
	            q.setTid(tid);
	            q.setVisible(rs.getBoolean("IS_VISIBLE")); // ✅ add this
	            quizzes.add(q);
	        }
	        System.out.println("[QuizDAO] Quiz details fetched for TID: " + tid);
	        return quizzes;
	    } catch (SQLException e) {
	        System.out.println("[QuizDAO] Database error: " + e.getMessage());
	        e.printStackTrace();
	    } catch (Exception e) {
	        System.out.println("[QuizDAO] Some error occurred: " + e.getMessage());
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
		String updateQuery="UPDATE QUIZZES SET TITLE =?, SUBJECT=?,DURATION=? WHERE QID=?";
		try(Connection con=DBConnection.getConnection();
			PreparedStatement ps=con.prepareStatement(updateQuery);)
		{
			ps.setString(1,quiz.getTitle());
			ps.setString(2,quiz.getSubject());
			ps.setInt(3,quiz.getDuration());
			ps.setInt(4, quiz.getQid());
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
	    String selectQuery = "SELECT * FROM QUIZZES WHERE QID=?";

	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(selectQuery)) {

	        ps.setInt(1, qid);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            Quiz quiz = new Quiz();
	            quiz.setQid(rs.getInt("QID"));
	            quiz.setTitle(rs.getString("TITLE"));
	            quiz.setSubject(rs.getString("SUBJECT"));
	            quiz.setTid(rs.getInt("TID"));
	            quiz.setCreatedAt(rs.getTimestamp("CREATED_AT"));
	            quiz.setDuration(rs.getInt("DURATION")); // ✅ THIS LINE IS THE FIX
	            quiz.setVisible(rs.getBoolean("IS_VISIBLE"));

	            System.out.println("[QuizDAO] Quiz details fetched for QID:" + qid + 
	                               " (Duration = " + quiz.getDuration() + " mins)");
	            return quiz;
	        }

	    } catch (SQLException e) {
	        System.out.println("[QuizDAO] Database error:" + e.getMessage());
	        e.printStackTrace();
	    } catch (Exception e) {
	        System.out.println("[QuizDAO] Some error occurred :" + e.getMessage());
	        e.printStackTrace();
	    }
	    return null;
	}

	
	public List<Quiz> getQuizzesByBatch(String batch)
	{
		String selectQuery="SELECT q.* FROM QUIZZES q JOIN QUIZ_BATCHES qb ON q.QID = qb.QID WHERE qb.BATCH = ? AND q.IS_VISIBLE = TRUE ORDER BY q.CREATED_AT DESC;";
		ArrayList<Quiz> quizzes=new ArrayList<>();
		try(Connection con=DBConnection.getConnection();
			PreparedStatement ps=con.prepareStatement(selectQuery);)
		{
			ps.setString(1, batch);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				Quiz q=new Quiz();
				q.setQid(rs.getInt("QID"));
				q.setTitle(rs.getString("TITLE"));
				q.setSubject(rs.getString("SUBJECT"));
				q.setCreatedAt(rs.getTimestamp("CREATED_AT"));
				q.setTid(rs.getInt("TID"));
				q.setVisible(rs.getBoolean("IS_VISIBLE"));

				quizzes.add(q);			
			}
			System.out.println("[QuizDAO] Quiz details fetched for Batch:"+batch);
			rs.close();
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
	
	
	public boolean toggleQuizVisibility(int qid, boolean visible) {
	    String query = "UPDATE QUIZZES SET IS_VISIBLE = ? WHERE QID = ?";
	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(query)) {
	        ps.setBoolean(1, visible);
	        ps.setInt(2, qid);
	        System.out.println("[QuizDAO] Toggled visibility for quiz ID " + qid + " → " + visible);

	        return ps.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("[QuizDAO] Error updating visibility: " + e.getMessage());
	    }
	    return false;
	}
	
	
	public List<Quiz> getAllQuizzes() {
	    String query = "SELECT q.*, t.NAME AS TEACHER_NAME FROM QUIZZES q JOIN TEACHERS t ON q.TID = t.TID ORDER BY q.CREATED_AT DESC";
	    List<Quiz> quizzes = new ArrayList<>();
	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(query)) {

	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            Quiz q = new Quiz();
	            q.setQid(rs.getInt("QID"));
	            q.setTitle(rs.getString("TITLE"));
	            q.setSubject(rs.getString("SUBJECT"));
	            q.setTid(rs.getInt("TID"));
	            q.setCreatedAt(rs.getTimestamp("CREATED_AT"));
	            q.setDuration(rs.getInt("DURATION"));
	            q.setVisible(rs.getBoolean("IS_VISIBLE"));
	            q.setTeacherName(rs.getString("TEACHER_NAME"));
	            quizzes.add(q);
	        }
	        rs.close();
	    } catch (SQLException e) {
	        System.out.println("[QuizDAO] Database error while fetching all quizzes: " + e.getMessage());
	        e.printStackTrace();
	    } catch (Exception e) {
	        System.out.println("[QuizDAO] Some error occurred while fetching all quizzes: " + e.getMessage());
	        e.printStackTrace();
	    }
	    return quizzes;
	}
	
	
	public int getTotalQuizCount() {
	    String query = "SELECT COUNT(*) FROM QUIZZES";
	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(query);
	         ResultSet rs = ps.executeQuery()) {
	        if (rs.next()) return rs.getInt(1);
	    } catch (SQLException e) {
	        System.out.println("[QuizDAO] Error fetching quiz count: " + e.getMessage());
	    }
	    return 0;
	}

	public int getVisibleQuizCount() {
	    String query = "SELECT COUNT(*) FROM QUIZZES WHERE IS_VISIBLE = TRUE";
	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(query);
	         ResultSet rs = ps.executeQuery()) {
	        if (rs.next()) return rs.getInt(1);
	    } catch (SQLException e) {
	        System.out.println("[QuizDAO] Error fetching visible quiz count: " + e.getMessage());
	    }
	    return 0;
	}



	
}
	
	
