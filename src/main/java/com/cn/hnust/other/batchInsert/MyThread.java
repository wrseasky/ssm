package com.cn.hnust.other.batchInsert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class MyThread extends Thread {
	public void run() {
		String url = "jdbc:mysql://localhost:3307/jpa";
		String name = "com.mysql.jdbc.Driver";
		String user = "root";
		String password = "root";
		Connection conn = null;
		try {
			Class.forName(name);
			conn = DriverManager.getConnection(url, user, password);// 获取连接
			conn.setAutoCommit(false);// 关闭自动提交，不然conn.commit()运行到这句会报错
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 开始时间
		Long begin = new Date().getTime();
		String prefix = "insert into jpa.batchinsert (name,sex,description) VALUES ";
		try {
			StringBuffer suffix = new StringBuffer();
			conn.setAutoCommit(false);
			PreparedStatement pst = (PreparedStatement) conn.prepareStatement("");
			for (int i = 1; i <= 10; i++) {
				suffix = new StringBuffer();
				for (int j = 1; j <= 100000; j++) {
					suffix.append("('" + i * j + "','男'" + ",'教师'" + "),");
				}
				String sql = prefix + suffix.substring(0, suffix.length() - 1);
				pst.addBatch(sql);
				pst.executeBatch();
				conn.commit();
				suffix = new StringBuffer();
			}
			pst.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Long end = new Date().getTime();
		System.out.println("100万条数据插入花费时间 : " + (end - begin) / 1000 + " s" + "  插入完成");
	}
}