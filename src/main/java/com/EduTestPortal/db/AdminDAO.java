package com.EduTestPortal.db;

import java.sql.*;
import com.EduTestPortal.model.Admin;

public class AdminDAO {

    public boolean addAdmin(Admin admin) {
        String insertQuery = "INSERT INTO ADMINS (NAME,EMAIL,PASSWORD) VALUES (?,?,?);";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, admin.getName());
            ps.setString(2, admin.getEmail());
            ps.setString(3, admin.getPassword());

            int success = ps.executeUpdate();
            if (success > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    System.out.println("[AdminDAO] Admin added successfully with ID: " + rs.getInt(1));
                    rs.close();
                }
                return true;
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("[AdminDAO] Insertion failed: Duplicate entry - " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("[AdminDAO] Database error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("[AdminDAO] Some error occurred: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public Admin getAdminByEmail(String email) {
        String selectQuery = "SELECT * FROM ADMINS WHERE EMAIL = ?;";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(selectQuery)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Admin admin = new Admin();
                admin.setAid(rs.getInt("aid"));
                admin.setName(rs.getString("name"));
                admin.setEmail(rs.getString("email"));
                admin.setPassword(rs.getString("password"));
                admin.setRegisteredAt(rs.getTimestamp("registered_at"));
                rs.close();
                return admin;
            }
        } catch (SQLException e) {
            System.out.println("[AdminDAO] Database error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("[AdminDAO] Some error occurred: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    // ✅ Fetch admin by ID (for profile)
    public Admin getAdminById(int aid) {
        String query = "SELECT * FROM ADMINS WHERE AID = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, aid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Admin admin = new Admin();
                admin.setAid(rs.getInt("aid"));
                admin.setName(rs.getString("name"));
                admin.setEmail(rs.getString("email"));
                admin.setPassword(rs.getString("password"));
                admin.setRegisteredAt(rs.getTimestamp("registered_at"));
                rs.close();
                return admin;
            }
        } catch (SQLException e) {
            System.out.println("[AdminDAO] Error fetching admin by ID: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    // ✅ Update admin details (name, email only)
    public boolean updateAdmin(Admin admin) {
        String query = "UPDATE ADMINS SET NAME=?, EMAIL=? WHERE AID=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, admin.getName());
            ps.setString(2, admin.getEmail());
            ps.setInt(3, admin.getAid());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("[AdminDAO] Error updating admin: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}
