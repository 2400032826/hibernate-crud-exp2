package com.fsad.main;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.fsad.entity.Product;

public class HQLDemo {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();

        Session session = factory.openSession();
        session.beginTransaction();

        // 1. Sorting by price (ascending)
        Query<Product> q1 = session.createQuery("from Product order by price", Product.class);
        List<Product> list1 = q1.getResultList();

        System.out.println("Products sorted by price:");
        for(Product p : list1) {
            System.out.println(p.getName() + " - " + p.getPrice());
        }

        // 2. Pagination (first 3 records)
        Query<Product> q2 = session.createQuery("from Product", Product.class);
        q2.setFirstResult(0);
        q2.setMaxResults(3);

        List<Product> list2 = q2.getResultList();

        System.out.println("\nFirst 3 products:");
        for(Product p : list2) {
            System.out.println(p.getName());
        }

        // 3. Aggregate function
        Query<Long> q3 = session.createQuery("select count(p) from Product p", Long.class);
        Long count = q3.getSingleResult();

        System.out.println("\nTotal number of products: " + count);

        session.getTransaction().commit();
        factory.close();
    }
}