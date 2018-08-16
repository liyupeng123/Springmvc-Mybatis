package org.lyp.controller;



import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * 定义�?个MyBatisSession对象的取得工具类，本类的功能有如下几种：<br>
 * 1、可以取得SqlSessionFactory以及SqlSession对象�?<br>
 * 2、可以进行SqlSession的关闭处理操作；<br>
 * 3、可以重新建立SqlSessionFactory�?<br>
 * @author mldn
 *
 */
public class MyBatisSessionFactory {
	/**
	 * 取得MyBatis的核心配置文件，此处定义了文件的路径
	 */
	private static final String CONFIG_FILE = "mybatis.cfg.xml" ;
	/**
	 * 描述的是保存当前线程操作的SqlSession接口对象
	 */
	private static ThreadLocal<SqlSession> threadLocal = new ThreadLocal<SqlSession>() ;
	/**
	 * 定义的是数据库连接池对象，�?�过连接池可以为不同的线程打�?SqlSession
	 */
	private static SqlSessionFactory factory  = null ;
	/**
	 * 对于核心配置文件的读取需要�?�过InputStream类完�?
	 */
	private static InputStream input = null ;
	static {	// 编写�?个静态代码块，主要的目的是�?�过该代码块可以取得SqlSessionFactory对象
		rebuildSqlSessionFactory() ;	// 建立新的SqlSessionFactory连接
	}
	/**
	 * 重新建立�?个新的SqlSessionFactory接口对象
	 */
	public static void rebuildSqlSessionFactory() {
		try {
			input = Resources.getResourceAsStream(CONFIG_FILE); // 读取资源文件的内�?
			factory = new SqlSessionFactoryBuilder().build(input) ; // 创建连接工厂
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 打开或�?�是取得�?个已经存在的SqlSession接口对象
	 * @return SqlSession实例
	 */
	public static SqlSession getSession() {
		SqlSession ses = threadLocal.get() ;	// 通过ThreadLocal取得保存过的SqlSession
		if (ses == null) {	// 此时还未保存SqlSession对象
			if (factory == null) {	// 此时还未创建SqlSessionFactory接口对象
				rebuildSqlSessionFactory(); // 重新创建SqlSessionFactory接口对象
			}
			ses = factory.openSession() ;	// 取得SqlSession对象
			threadLocal.set(ses); 	// 保存SqlSession供下次继续使�?
		}
		return ses ;
	}
	/**
	 * 取得已经创建好的SqlSessionFactory对象
	 * @return SqlSessionFactory对象实例
	 */
	public static SqlSessionFactory getFactory() {
		return factory ;
	}
	/**
	 * 执行SqlSession的关闭处理，同时还需要清空ThreadLocal对象中的保存内容
	 */
	public static void close() {
		SqlSession ses = threadLocal.get() ;	// 通过ThreadLocal取得保存过的SqlSession
		if (ses != null) {
			ses.close() ;
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			threadLocal.remove(); 
		}
	}
}






