package com.fsad.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.fsad.entity.Product;

public class App {

public static void main(String[] args) {

SessionFactory factory = new Configuration()
.configure("hibernate.cfg.xml")
.addAnnotatedClass(Product.class)
.buildSessionFactory();

Session session = factory.openSession();

Product p1 = new Product("Laptop","Dell Laptop",50000,5);

session.beginTransaction();
session.save(p1);
session.getTransaction().commit();

System.out.println("Product inserted successfully");

factory.close();

}

}