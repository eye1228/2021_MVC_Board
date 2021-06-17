package com.ict.db;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class DBService {
	static private SqlSessionFactory factory;
	
	// static 초기화
	static {
		try {
			factory = new SqlSessionFactoryBuilder().build(
					Resources.getResourceAsReader("com/ict/db/config.xml"));
		} catch (Exception e) {
			System.out.println(e + "  여기");
		}
	}
	
	public static SqlSessionFactory getFactory() {
		return factory;
	}
}
