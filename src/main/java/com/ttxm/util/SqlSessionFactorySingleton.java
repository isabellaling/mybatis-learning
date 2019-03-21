package com.ttxm.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionFactorySingleton
{
	//使用volatile关键字来禁止instance = newSingleton()非原子语句被JVM指令重排序，导致多线程环境下执行出错
	private volatile static SqlSessionFactory sqlSessionFactory = null;
	
	private SqlSessionFactorySingleton () {}
	
	public static SqlSessionFactory getInstance()
	{
		if (null == sqlSessionFactory) 
		{
			synchronized (SqlSessionFactorySingleton.class) 
			{
				if (null == sqlSessionFactory) 
				{
					try(InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml"))
					{
						sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
						
					} catch (IOException e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		return sqlSessionFactory;
	}
	
	

}
