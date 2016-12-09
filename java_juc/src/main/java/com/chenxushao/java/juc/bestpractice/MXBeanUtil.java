package com.chenxushao.java.juc.bestpractice;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MXBeanUtil {

	public static String dumpThread() {
		ThreadMXBean bean = ManagementFactory.getThreadMXBean();
		ThreadInfo[] infos = bean.dumpAllThreads(true, true);
		StringBuilder sb = new StringBuilder();
		for (ThreadInfo info : infos) {
			sb.append(info);
		}
		return sb.toString();
	}

	public static String getJVM() {
		StringBuilder sb = new StringBuilder();
		sb.append("Heap Memory Usage\n");
		formatMemoryUsage(sb, ManagementFactory.getMemoryMXBean().getHeapMemoryUsage());
		sb.append("NonHeap Memory Usage\n");
		formatMemoryUsage(sb, ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage());
		List<MemoryPoolMXBean> mpmList = ManagementFactory.getMemoryPoolMXBeans();
		for (MemoryPoolMXBean mpm : mpmList) {
			sb.append(mpm.getName()).append("\n");
			formatMemoryUsage(sb, mpm.getUsage());
		}
		return sb.toString();
	}

	public static String findDeadlockedThreads() {
		StringBuilder dls = new StringBuilder();
		ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
		Set<Long> deadTheadIdsSet = new HashSet<Long>();
		long[] deadTheadIds = threadMXBean.findDeadlockedThreads();
		if (deadTheadIds == null || deadTheadIds.length == 0) {
			return null;
		}
		for (long deadTheadId : deadTheadIds) {
			deadTheadIdsSet.add(deadTheadId);
		}
		Map<Thread, StackTraceElement[]> stackTraceMap = Thread.getAllStackTraces();
		for (Entry<Thread, StackTraceElement[]> threadStack : stackTraceMap.entrySet()) {
			if (deadTheadIdsSet.contains(threadStack.getKey().getId())) {
				StackTraceElement[] value = threadStack.getValue();
				for (StackTraceElement stackTraceElement : value) {
					dls.append(stackTraceElement).append("\n");
				}

			}
		}
		return dls.toString();
	}

	private static void formatMemoryUsage(StringBuilder sb, MemoryUsage usage) {
		sb.append("  used=").append(usage.getUsed()).append("(").append(kbToM(usage.getUsed())).append("MB)\n");
		sb.append("  init=").append(usage.getInit()).append("(").append(kbToM(usage.getInit())).append("MB)\n");
		sb.append("  max=").append(usage.getMax()).append("(").append(kbToM(usage.getMax())).append("MB)\n");
		sb.append("  rate=").append(((double) usage.getUsed()) / usage.getMax() * 100).append("").append("%\n");
	}

	private static double kbToM(long kb) {
		return kb / 1024.0 / 1024.0;
	}

	public static void main(String[] args) {
		 System.out.println(getJVM());
		 System.out.println(dumpThread());
	}
}
