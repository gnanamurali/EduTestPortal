package com.EduTestPortal.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.EduTestPortal.model.Result;

public class ResultsDAO 
{
	

	public boolean saveResult(int sid,int qid,int score)
	{
		String insertQuery= "INSERT INTO RESULTS (SID , QID , SCORE) VALUES (? ,? ,?);";
		
		 try(Connection con=DBConnection.getConnection();
		     PreparedStatement ps = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
)
		 {
			 ps.setInt(1, sid);
			 ps.setInt(2, qid);
			 ps.setInt(3, score);
			 
			 int success=ps.executeUpdate();
			 if(success>0)
			 {
				 System.out.println("[ResultsDao] Results uploaded for student: S"+sid+" for quiz: Q"+qid);
				 ResultSet rs = ps.getGeneratedKeys();
				 if (rs.next()) 
				 {
				     System.out.println("[ResultDAO] Auto-generated RID: " + rs.getInt(1));
				 }
				 return true;
			 }
		 }
		 catch(SQLException e)
			{
				System.out.println("[ResultsDAO] Database error:"+e.getMessage());
				e.printStackTrace();
			}
					
		catch (Exception e) 
		 	{
					
				System.out.println("[ResultsDAO] Some error occured Results uploaded for student:  S"+sid+" for quiz: Q"+qid+": "+e.getMessage());
				e.printStackTrace();
			}
		 	return false;
		
	}
	
	
	public boolean hasAttemptedQuiz(int sid, int qid) 
	{
	    String checkQuery = "SELECT COUNT(*) FROM RESULTS WHERE SID = ? AND QID = ?";
	    
	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(checkQuery)) 
	    {
	        ps.setInt(1, sid);
	        ps.setInt(2, qid);
	        ResultSet rs = ps.executeQuery();
	        
	        if (rs.next()) 
	        {
	            int count = rs.getInt(1);
	            if (count > 0) 
	            {
	                System.out.println("[ResultsDAO] Student S" + sid + " has already attempted Quiz Q" + qid);
	                return true;
	            }
	        }
	        System.out.println("[ResultsDAO] Student S" + sid + " has not attempted Quiz Q" + qid);
	    } 
	    catch (SQLException e) 
	    {
	        System.out.println("[ResultsDAO] Database error: " + e.getMessage());
	        e.printStackTrace();
	    } 
	    catch (Exception e) 
	    {
	        System.out.println("[ResultsDAO] Some error occurred while checking quiz attempt: " + e.getMessage());
	        e.printStackTrace();
	    }
	    
	    return false;
	   
	}
	
	public List<Result> getResultsByStudent(int sid)
	{
	    String selectQuery = "SELECT * FROM RESULTS WHERE SID = ? ORDER BY taken_at DESC";
	    List<Result> results = new ArrayList<>();

	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(selectQuery))
	    {
	        ps.setInt(1, sid);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next())
	        {
	            Result r = new Result();
	            r.setRid(rs.getInt("RID"));
	            r.setSid(rs.getInt("SID"));
	            r.setQid(rs.getInt("QID"));
	            r.setScore(rs.getInt("SCORE"));
	            r.setTakenAt(rs.getTimestamp("TAKEN_AT"));

	            results.add(r);
	        }

	        System.out.println("[ResultsDAO] Results fetched successfully for Student S" + sid);
	        rs.close();
	        return results;
	    }
	    catch (SQLException e)
	    {
	        System.out.println("[ResultsDAO] Database error while fetching results: " + e.getMessage());
	        e.printStackTrace();
	    }
	    catch (Exception e)
	    {
	        System.out.println("[ResultsDAO] Some error occurred while fetching results for S" + sid + ": " + e.getMessage());
	        e.printStackTrace();
	    }

	    return Collections.emptyList();
	}

	
	
}
