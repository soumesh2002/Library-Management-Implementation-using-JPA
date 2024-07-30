package com.library.main;

import java.util.Date;
import java.util.Scanner;

import com.library.entity.Books;
import com.library.entity.Transactions;
import com.library.entity.Users;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainProgram {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("libraryPU");
        EntityManager em = emf.createEntityManager();

        try {

            em.getTransaction().begin();

            // Books book = em.find(Books.class, 1);
            // Users user = em.find(Users.class, 1);

            Books book2 = em.find(Books.class, 5);
            Users user2 = em.find(Users.class, 9);

            Transactions transaction = new Transactions();
            transaction.setBooks(book2);
            transaction.setUsers(user2);
            transaction.setTransaction_date(new Date());
            transaction.setReturnDate(null);

            em.persist(transaction);

            em.getTransaction().commit();

        } finally {
            em.close();
        }
    }
}
