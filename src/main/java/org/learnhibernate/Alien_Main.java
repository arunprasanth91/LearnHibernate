package org.learnhibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Alien_Main {

	public static void main(String[] args) {
		createAlien();
	}

	private static SessionFactory connect(){
		return new Configuration().addAnnotatedClass(Alien.class).configure().buildSessionFactory();
	}

	private static void createAlien() {
		Alien alien1 = new Alien();
		alien1.setAlienId(123);
		alien1.setAlienName("arun");
		alien1.setTech("java");
		alien1.setLaptop(new Alien_Laptop().setBrand("Asus").setProcessor("ROG Zephyrus G16").setRam("16 GB"));
		try{
			SessionFactory sessionFactory = connect();
			Session session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.persist(alien1);
			transaction.commit();
			System.out.println(alien1);
			Alien alien2 = session.find(Alien.class, 123);
			System.out.println(alien2);
			session.close();
			sessionFactory.close();
		} catch (Exception s) {
			s.printStackTrace();
		}
	}
}
