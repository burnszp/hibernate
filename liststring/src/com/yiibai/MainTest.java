package com.yiibai;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.*;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.*;

public class MainTest {
	public static void main(String[] args) {
		// ����5.1.0�汾���ܣ�hibernate����������·�ʽ��ȡ��
		// 1. �������Ͱ�ȫ��׼����ע���࣬���ǵ�ǰӦ�õĵ������󣬲����޸ģ���������Ϊfinal
		// ��configure("cfg/hibernate.cfg.xml")�����У������ָ����Դ·����Ĭ������·����Ѱ����Ϊhibernate.cfg.xml���ļ�
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure("hibernate.cfg.xml").build();
		// 2. ���ݷ���ע���ഴ��һ��Ԫ������Դ����ͬʱ����Ԫ���ݲ�����Ӧ��һ��Ψһ�ĵ�session����
		SessionFactory sessionFactory = new MetadataSources(registry)
				.buildMetadata().buildSessionFactory();

		/**** ����������׼�������濪ʼ���ǵ����ݿ���� ******/
		Session session = sessionFactory.openSession();// �ӻỰ������ȡһ��session

		// creating transaction object
		Transaction t = session.beginTransaction();

		ArrayList<String> list1 = new ArrayList<String>();
		list1.add("java is a programming language");
		list1.add("java is a platform");

		ArrayList<String> list2 = new ArrayList<String>();
		list2.add("Servlet is an Interface");
		list2.add("Servlet is an API");

		Question question1 = new Question();
		question1.setQname("Java ��ʲô?");
		question1.setAnswers(list1);

		Question question2 = new Question();
		question2.setQname("Hibernate ��ʲô?");
		question2.setAnswers(list2);

		session.persist(question1);
		session.persist(question2);

		t.commit();

		// ��ѯ����
		Query<Question> query = session.createQuery("from Question");
		List<Question> list = query.list();

		Iterator<Question> itr = list.iterator();
		while (itr.hasNext()) {
			Question q = itr.next();
			System.out.println("Question Name: " + q.getQname());

			// printing answers
			List<String> list21 = q.getAnswers();
			Iterator<String> itr2 = list21.iterator();
			while (itr2.hasNext()) {
				System.out.println(itr2.next());
			}

		}
		session.close();
		System.out.println("success");

	}
}