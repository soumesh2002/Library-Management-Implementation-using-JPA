package com.library.main;

import java.util.Scanner;

import com.library.dao.Transaction;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainProgram {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("libraryPU");
        Scanner sc = new Scanner(System.in);
        Transaction trasaction = new Transaction(emf);

        while (true) {
            System.out.println("Enter a choice of operations: ");
            System.out.println("1.Borrow a book");
            System.out.println("2.Return a book");
            System.out.println("3.Exit");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Borrow Operation");
                    System.out.println("----------------");

                    System.out.println("Enter the bookID to borrow: ");
                    int bookID = sc.nextInt();

                    System.out.println("Enter the userID: ");
                    int userID = sc.nextInt();
                    trasaction.borrowBook(bookID, userID);
                    break;

                case 2:
                    System.out.println("Return Operation");
                    System.out.println("----------------");

                    System.out.println("Enter the bookID to return: ");
                    int bookId = sc.nextInt();
                    trasaction.returnBook(bookId);
                    break;

                case 3:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again");
                    break;
            }
            emf.close();
        }
    }
}
