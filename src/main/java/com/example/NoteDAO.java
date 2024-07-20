/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kidoo
 */

public class NoteDAO {
    private static final String URL = "jdbc:sqlite:notes.db";

    public void addNote(String description) {
        String sql = "INSERT INTO notes(description) VALUES(?)";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, description);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Note> getAllNotes() {
        String sql = "SELECT id, description, timestamp FROM notes ORDER BY timestamp DESC";
        List<Note> notes = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Note note = new Note();
                note.setId(rs.getInt("id"));
                note.setDescription(rs.getString("description"));
                note.setTimestamp(rs.getString("timestamp"));
                notes.add(note);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return notes;
    }

    public void deleteNoteById(int id) {
        String sql = "DELETE FROM notes WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
