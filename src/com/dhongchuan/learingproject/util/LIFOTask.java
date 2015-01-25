package com.dhongchuan.learingproject.util;

import java.util.concurrent.FutureTask;

/**
 * �������ȼ���������
 * @author dhongchuan
 *
 */
public class LIFOTask extends FutureTask<Object> implements Comparable<LIFOTask>{
	private static long counter = 0;
	private final long priority;

	public LIFOTask(Runnable runnable, int taskTag) {
		super(runnable, taskTag);
		// TODO Auto-generated constructor stub
		//�ڵ��߳��д���
		priority = counter++;
	}
	
	public long getPriority(){
		return priority;
	}

	@Override
	public int compareTo(LIFOTask another) {
		// TODO Auto-generated method stub
		return priority > another.getPriority() ? -1 : 1;
	}

}
