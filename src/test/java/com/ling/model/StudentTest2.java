package com.ling.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.Work;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StudentTest2 {
	
	private SessionFactory sessionFactory;
	private Session session;
//	private Transaction transaction;
	
	@Before
	public void init() {
		//创建配置对象
		Configuration configuration = new Configuration().configure();
		//创建服务注册对象
		StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
//		StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().build();
		//创建会话工厂
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		//创建会话
		session = sessionFactory.openSession();
		//开启事务
//		transaction = session.beginTransaction();
	}
	
	@After
	public void destory() {
//		transaction.commit();//提交事务
		session.close();//关闭会话
		sessionFactory.close();//关闭会话工厂
	}
	
	@Test
	public void testStudent() {
		Student s = new Student(1, "李东海", "女", new Date(), "信联制度");
		
		//1.设置自动提交
		session.doWork(new Work() {
			@Override
			public void execute(Connection connection) throws SQLException {
				connection.setAutoCommit(true);
			}
		});
		session.save(s);
		//2.通过flush()发送sql语句
		session.flush();
		
	}

}
