package org.learnhibernate;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class App {

	public static void main(String[] args) {
		deleteDataFromDB();

	}

	private static SessionFactory connect(){
		return new Configuration().addAnnotatedClass(Student.class).configure().buildSessionFactory();
	}


	private static void addEntryToDB() {
		SessionFactory sessionFactory = null;
		Session session = null;
		try{
			Student s1 = new Student();
			s1.setRollNo(101);
			s1.setName("arun");
			s1.setAge(11);
			Configuration cfg = new Configuration();
			cfg.addAnnotatedClass(org.learnhibernate.Student.class);
			cfg.configure();
			sessionFactory = cfg.buildSessionFactory();
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.persist(s1);
			transaction.commit();
			System.out.println(s1);
		}catch (Exception e){
			e.printStackTrace();
		}
		finally {
			assert session != null;
			session.close();
			sessionFactory.close();
		}
	}


	private static void getDataFromDB() {
		SessionFactory sessionFactory = null;
		Session session = null;
		try{
			Student s2 = null;
			Configuration cfg = new Configuration();
			cfg.addAnnotatedClass(org.learnhibernate.Student.class);
			cfg.configure();
			sessionFactory = cfg.buildSessionFactory();
			session = sessionFactory.openSession();
			s2 = session.find(Student.class, 1L);
			System.out.println(s2);
			EntityManager entityManager = session.getEntityManagerFactory().createEntityManager();
			List<Student> studentList = entityManager.createQuery("FROM Student", Student.class).getResultList();
			studentList.forEach(System.out::println);
			if (entityManager.isOpen()){
				entityManager.close();
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		finally {
			assert session != null;
			session.close();
			sessionFactory.close();
		}
	}

	private static void updateDataToDB() {
		SessionFactory sessionFactory = connect();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Student student = session.find(Student.class, 101);
		student.setName("Kiran");
		session.merge(student);
		transaction.commit();
		session.close();
		sessionFactory.close();
	}

	private static void deleteDataFromDB(){
		try(SessionFactory sessionFactory = connect()) {
			Session session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			Student student = session.find(Student.class, 103);
			session.remove(student);
			transaction.commit();
			session.close();
		}catch (Exception e){
			e.printStackTrace();
		}

	}
}