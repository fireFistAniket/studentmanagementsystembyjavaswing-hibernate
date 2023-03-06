package com.dao.student;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.databasetable.student.Student;

public class StudentDao {
	private EntityManagerFactory factory;
	private EntityManager manager;
	private EntityTransaction transaction;

	public StudentDao() {
		super();
		// TODO Auto-generated constructor stub
		factory = Persistence.createEntityManagerFactory("Aniket");
		manager = factory.createEntityManager();
		transaction = manager.getTransaction();
	}

	public void saveStudent(Student s) {
		transaction.begin();
		manager.persist(s);
		transaction.commit();
	}

	public Student findStudentById(int id) {
		transaction.begin();
		Student student = manager.find(Student.class, id);
		transaction.commit();
		return student;
	}

	public List<Student> getAllStudents() {
		transaction.begin();
		String q = "SELECT s FROM Student s";
		Query query = manager.createQuery(q);
		List<Student> students = query.getResultList();
		transaction.commit();
		return students;
	}

	public void updateStudent(Student s) {
		transaction.begin();
		manager.merge(s);
		transaction.commit();
	}

	public void deleteStudent(Student s) {
		transaction.begin();
		manager.remove(manager.find(Student.class, s.getId()));
		transaction.commit();
	}

}
