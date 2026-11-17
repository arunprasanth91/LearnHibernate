package org.learnhibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.Arrays;
import java.util.List;

public class Coder_Main {

	public static void main(String[] args) {
		AddCoder();
	}


	private static SessionFactory connect() {
		return new Configuration().addAnnotatedClass(Laptop.class).addAnnotatedClass(Coder.class).configure()
				.buildSessionFactory();
	}


	private static void AddCoder() {
		try{
			SessionFactory sessionFactory = connect();
			Session session = sessionFactory.openSession();
			Laptop laptop1 = new Laptop();
			laptop1.setId(101);
			laptop1.setBrand("asus");
			laptop1.setRam("32");
			laptop1.setProcessor("rog");

			Laptop laptop2 = new Laptop();
			laptop2.setId(102);
			laptop2.setBrand("dell");
			laptop2.setRam("32");
			laptop2.setProcessor("xps");

			Laptop laptop3 = new Laptop();
			laptop3.setId(103);
			laptop3.setBrand("apple");
			laptop3.setRam("16");
			laptop3.setProcessor("m3");

			Coder coder1 = new Coder();
			coder1.setCoder_id(1);
			coder1.setName("arun");
			coder1.setTech("java");


			Coder coder2 = new Coder();
			coder2.setCoder_id(2);
			coder2.setName("prasanth");
			coder2.setTech("python");

			laptop1.setCoder(coder1);
			laptop3.setCoder(coder1);
			laptop2.setCoder(coder2);

			coder1.setLaptops(Arrays.asList(laptop1, laptop3));
			coder2.setLaptops(Arrays.asList(laptop2));


			Transaction transaction = session.beginTransaction();

			session.persist(laptop1);
			session.persist(laptop2);
			session.persist(laptop3);

			session.persist(coder1);
			session.persist(coder2);
			transaction.commit();

			Coder coder_1 = session.find(Coder.class, 1);
			System.out.println(coder_1);
			Query query = session.createQuery("from Laptop where coder=?1");
			query.setParameter(1, coder_1);
			List<Laptop> laptopList = query.getResultList();
			for (Laptop laptop : laptopList) {
				System.out.println(laptop);
			}
			session.close();
			sessionFactory.close();


		}catch (Exception e){
			e.printStackTrace();
		}

	}
}
