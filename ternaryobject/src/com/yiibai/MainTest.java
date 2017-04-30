package com.yiibai;

import java.util.HashMap;
import org.hibernate.*;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.*;

public class MainTest {
	public static void main(String[] args) {
		// ��5.1.0�汾�У�hibernate����������·�ʽ��ȡ��
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

		HashMap<String, User> map1 = new HashMap<String, User>();
		map1.put("java is a programming language", new User("��С��",
				"user2@gmail.com", "usa"));
		map1.put("java is a platform", new User("������",
				"user1@gmail.com", "China"));

		HashMap<String, User> map2 = new HashMap<String, User>();
		map2.put("servlet technology is a server side programming", new User(
				"John Milton", "john.su@gmail.com", "usa"));
		map2.put("Servlet is an Interface", new User("Ashok Kumar",
				"as-top@gmail.com", "China"));

		Question question1 = new Question("Java��ʲô?", map1);
		Question question2 = new Question("Servlet��ʲô?", map2);

		session.persist(question1);
		session.persist(question2);

		t.commit();
		session.close();
		System.out.println("successfully stored");
	}
}