/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example;

/**
 *
 * @author Kidoo
 */


import java.util.List;
import java.util.Scanner;

public class NoteApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final NoteDAO noteDAO = new NoteDAO();

    public static void main(String[] args) {
        DatabaseHelper.initializeDatabase(); // Inisialisasi database

        while (true) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addNote();
                    break;
                case 2:
                    displayNotes();
                    break;
                case 3:
                    deleteNote();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("1. Tambah Catatan");
        System.out.println("2. Tampilkan Catatan");
        System.out.println("3. Hapus Catatan");
        System.out.println("4. Keluar");
        System.out.print("Pilih opsi: ");
    }

    private static void addNote() {
        System.out.print("Masukkan deskripsi catatan: ");
        String description = scanner.nextLine();
        noteDAO.addNote(description);
        System.out.println("Catatan berhasil ditambahkan.");
    }

    private static void displayNotes() {
        List<Note> notes = noteDAO.getAllNotes();
        if (notes.isEmpty()) {
            System.out.println("Tidak ada catatan yang tersedia.");
        } else {
            for (Note note : notes) {
                System.out.println(note);
            }
        }
    }

    private static void deleteNote() {
        displayNotes();
        System.out.print("Masukkan ID catatan yang ingin dihapus: ");
        int id = Integer.parseInt(scanner.nextLine());
        noteDAO.deleteNoteById(id);
        System.out.println("Catatan berhasil dihapus.");
    }
}

