package com.chenxushao.java.juc.bestpractice;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
 

/******************************************************************************
 * 
 * <p>Description:  自定义的线程工厂，以便自定义线程名，方便查找问题</p> 
 * Project: job
 * Package: com.kscloud.boss.charge.parallel
 *    File: NamedThreadFactory.java
 * 
 * @author chenxushao@kingsoft.com
 * @Created on 2016年11月14日 上午11:48:35
 * @since 1.0
 *****************************************************************************/
public class NamedThreadFactory implements ThreadFactory
{
	private static final AtomicInteger POOL_SEQ = new AtomicInteger(1);

	private final AtomicInteger mThreadNum = new AtomicInteger(1);

	private final String mPrefix;

	private final boolean mDaemo;

	private final ThreadGroup mGroup;

	public NamedThreadFactory()
	{
		this("pool-" + POOL_SEQ.getAndIncrement(),false);
	}

	public NamedThreadFactory(String prefix)
	{
		this(prefix,false);
	}

	public NamedThreadFactory(String prefix,boolean daemo)
	{
		mPrefix = prefix + "-thread-";
		mDaemo = daemo;
        SecurityManager s = System.getSecurityManager();
        mGroup = ( s == null ) ? Thread.currentThread().getThreadGroup() : s.getThreadGroup();
	}

	public Thread newThread(Runnable runnable)
	{
		String name = mPrefix + mThreadNum.getAndIncrement();
        Thread ret = new Thread(mGroup,runnable,name,0);
        ret.setDaemon(mDaemo);
        return ret;
	}

	public ThreadGroup getThreadGroup()
	{
		return mGroup;
	}
}