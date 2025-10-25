package com.EduTestPortal.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.EduTestPortal.model.QuizStats;
import com.EduTestPortal.model.Result;

public class ResultsDAO {

    /* ===============================
       SAVE RESULT WITH PERCENTAGE
    =============================== */
    public boolean saveResult(int sid, int qid, int score, double percentage) {
        String insertQuery = "INSERT INTO RESULTS (SID, QID, SCORE, PERCENTAGE) VALUES (?, ?, ?, ?);";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, sid);
            ps.setInt(2, qid);
            ps.setInt(3, score);
            ps.setDouble(4, percentage);

            int success = ps.executeUpdate();
            if (success > 0) {
                System.out.println("[ResultsDAO] Result uploaded for Student S" + sid + ", Quiz Q" + qid);
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    System.out.println("[ResultsDAO] Auto-generated RID: " + rs.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            System.out.println("[ResultsDAO] Database error while saving result: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("[ResultsDAO] Unexpected error while saving result for S" + sid + ", Q" + qid + ": " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    /* ===============================
       CHECK IF STUDENT HAS ATTEMPTED QUIZ
    =============================== */
    public boolean hasAttemptedQuiz(int sid, int qid) {
        String checkQuery = "SELECT COUNT(*) FROM RESULTS WHERE SID = ? AND QID = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(checkQuery)) {
            ps.setInt(1, sid);
            ps.setInt(2, qid);
            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("[ResultsDAO] Student S" + sid + " has already attempted Quiz Q" + qid);
                return true;
            }
        } catch (SQLException e) {
            System.out.println("[ResultsDAO] Database error while checking quiz attempt: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    /* ===============================
       GET RAW RESULTS BY STUDENT (BASIC)
    =============================== */
    public List<Result> getResultsByStudent(int sid) {
        String selectQuery = "SELECT * FROM RESULTS WHERE SID = ? ORDER BY TAKEN_AT DESC";
        List<Result> results = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(selectQuery)) {
            ps.setInt(1, sid);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Result r = new Result();
                r.setRid(rs.getInt("RID"));
                r.setSid(rs.getInt("SID"));
                r.setQid(rs.getInt("QID"));
                r.setScore(rs.getInt("SCORE"));
                r.setPercentage(rs.getDouble("PERCENTAGE"));
                r.setTakenAt(rs.getTimestamp("TAKEN_AT"));
                results.add(r);
            }

            System.out.println("[ResultsDAO] Basic results fetched successfully for Student S" + sid);
            rs.close();
            return results;

        } catch (SQLException e) {
            System.out.println("[ResultsDAO] Database error while fetching basic results: " + e.getMessage());
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    /* ===============================
       GET RESULTS WITH QUIZ DETAILS FOR A STUDENT
    =============================== */
    public List<Result> getResultsByStudentId(int sid) {
        String joinQuery = """
            SELECT r.RID, r.SCORE, r.PERCENTAGE, r.TAKEN_AT, q.QID, q.TITLE, q.SUBJECT
            FROM RESULTS r
            JOIN QUIZZES q ON r.QID = q.QID
            WHERE r.SID = ?
            ORDER BY r.TAKEN_AT DESC;
        """;
        List<Result> studentResults = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(joinQuery)) {
            ps.setInt(1, sid);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Result r = new Result();
                r.setRid(rs.getInt("RID"));
                r.setSid(sid);
                r.setQid(rs.getInt("QID"));
                r.setScore(rs.getInt("SCORE"));
                r.setPercentage(rs.getDouble("PERCENTAGE"));
                r.setTakenAt(rs.getTimestamp("TAKEN_AT"));
                r.setQuizTitle(rs.getString("TITLE"));
                r.setQuizSubject(rs.getString("SUBJECT"));
                studentResults.add(r);
            }

            System.out.println("[ResultsDAO] Results with quiz details fetched for Student S" + sid);
            rs.close();
            return studentResults;

        } catch (SQLException e) {
            System.out.println("[ResultsDAO] Database error while fetching detailed results: " + e.getMessage());
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    /* ===============================
       GET RESULTS FOR A SPECIFIC QUIZ
    =============================== */
    public List<Result> getResultsByQuizId(int qid) {
        String joinQuery = """
            SELECT r.RID, r.SID, s.NAME, s.BATCH, r.SCORE, r.PERCENTAGE, r.TAKEN_AT
            FROM RESULTS r
            JOIN STUDENTS s ON r.SID = s.SID
            WHERE r.QID = ?
            ORDER BY r.SCORE DESC;
        """;
        List<Result> quizResults = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(joinQuery)) {
            ps.setInt(1, qid);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Result r = new Result();
                r.setRid(rs.getInt("RID"));
                r.setSid(rs.getInt("SID"));
                r.setStudentName(rs.getString("NAME"));
                r.setStudentBatch(rs.getString("BATCH"));
                r.setScore(rs.getInt("SCORE"));
                r.setPercentage(rs.getDouble("PERCENTAGE"));
                r.setTakenAt(rs.getTimestamp("TAKEN_AT"));
                quizResults.add(r);
            }

            System.out.println("[ResultsDAO] Quiz results fetched successfully for Quiz Q" + qid);
            rs.close();
            return quizResults;

        } catch (SQLException e) {
            System.out.println("[ResultsDAO] Database error while fetching quiz results: " + e.getMessage());
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    /* ===============================
       QUIZ STATISTICS (AVERAGE, HIGH, LOW, COUNT)
    =============================== */
    public QuizStats getQuizStatistics(int qid) {
        String query = """
            SELECT COUNT(*) AS total,
                   MAX(SCORE) AS maxScore,
                   MIN(SCORE) AS minScore,
                   AVG(SCORE) AS avgScore,
                   AVG(PERCENTAGE) AS avgPercentage
            FROM RESULTS WHERE QID = ?;
        """;
        QuizStats stats = new QuizStats();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, qid);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                stats.setTotalAttempts(rs.getInt("total"));
                stats.setHighestScore(rs.getInt("maxScore"));
                stats.setLowestScore(rs.getInt("minScore"));
                stats.setAverageScore(rs.getDouble("avgScore"));
                stats.setAveragePercentage(rs.getDouble("avgPercentage"));
            }
            rs.close();
            System.out.println("[ResultsDAO] Quiz statistics fetched successfully for Q" + qid);
            return stats;

        } catch (SQLException e) {
            System.out.println("[ResultsDAO] Database error while fetching quiz stats: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
