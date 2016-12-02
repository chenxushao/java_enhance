package com.chenxushao.java.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.HashSet;
import java.util.Set;

import com.chenxushao.java.model.User;

/*
 * 软引用
 * 为方便测试中，我们故意将堆内存设置得较小
 * 使用的VM参数为
 * -Xms1M -Xmx1M　　只为堆分配1MB的内存
 * 同时为了查看GC日志，添加了打印GC日志的VM参数
 * -verbose:gc -XX:+PrintGCDetails
 */
public class TestSoftReference {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 创建引用队列
		ReferenceQueue<User> rq = new ReferenceQueue<User>();
		Set<SoftReference<User>> userSet = new HashSet<SoftReference<User>>();
		// 为造成内存不中，我们一次性创建5000个User对象
		for (int i = 0; i < 5000; i++) {
			userSet.add(new SoftReference<User>(new User("user"
					+ String.valueOf(i)), rq));
		}

		/*
		 * [GC [DefNew: 512K->64K(576K), 0.0019196 secs] 512K->310K(960K),
		 * 0.0019659 secs] [GC [DefNew: 576K->63K(576K), 0.0018645
		 * secs][Tenured: 537K->601K(640K), 0.0116122 secs] 822K->601K(1216K),
		 * 0.0135478 secs] Finalizing User user59 Finalizing User user274
		 * Finalizing User user303 Finalizing User user328 Finalizing User
		 * user355
		 * 
		 * 其它打印结果省略
		 */

		/*
		 * 由输出结果可知，内存不中时，虚拟机进行了两垃圾回收。 并将SoftReference引用的对象回收掉
		 */

	}

}
