package com.library.dao;

import java.util.Date;

import com.library.entity.Books;
import com.library.entity.Transactions;
import com.library.entity.Users;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Transaction {
    EntityManagerFactory emf;

    public Transaction(EntityManagerFactory emf) {
        this.emf = Persistence.createEntityManagerFactory("libraryPU");
    }

    public void borrowBook(int bookId, int userId) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Books book = em.find(Books.class, bookId);
            Users user = em.find(Users.class, userId);

            if (book == null || user == null) {
                System.out.println("Book not found");
                return;
            }

            if (book.isBook_availability() == false) {
                System.out.println("Book is already borrowed, please wait for the borrower to return it.");
                return;
            }

            Transactions newT = new Transactions();
            newT.setBooks(book);
            newT.setUsers(user);

            newT.setTransaction_date(new Date());
            newT.setReturnDate(null);
            book.setBook_availability(true);
            em.persist(newT);
            em.merge(book);
            em.getTransaction().commit();
            System.out.println(
                    "Book with bookID: " + book.getId() + "borrowed successfully by the user: " + user.getUser_name());
        } finally {
            em.close();
        }
    }

    public void returnBook(int bookId) {
        EntityManager em = emf.createEntityManager();
        Transactions newT2 = new Transactions();
        try {
            em.getTransaction().begin();

            Books book = em.find(Books.class, bookId);
            if (book == null) {
                System.out.println("Book not found");
                return;
            }

            if (!book.isBook_availability()) {
                System.out.println("Book is not borrowed");
                return;
            }

            book.setBook_availability(false);
            em.merge(book);
            newT2.setReturnDate(new Date());
            em.getTransaction().commit();
            System.out.println(
                    "Book with bookID: " + book.getId() + " returned successfully on: " + newT2.getReturnDate());
        } finally {
            em.close();
        }
    }
}
