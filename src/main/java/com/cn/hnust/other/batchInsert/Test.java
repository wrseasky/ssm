package com.cn.hnust.other.batchInsert;

public class Test {
	public static void main(String[] args) {
		
		for (int i = 1; i <= 10; i++) {
			new MyThread().start();
		}
		
	}
}
