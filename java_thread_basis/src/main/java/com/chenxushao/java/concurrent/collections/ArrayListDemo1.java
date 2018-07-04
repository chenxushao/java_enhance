package com.chenxushao.java.concurrent.collections;

import com.chenxushao.java.concurrent.util.RandomUtil;
import com.chenxushao.java.concurrent.util.ThreadUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * ArrayList，线程不安全。会抛出java.util.ConcurrentModificationException异常.
 * CopyOnWrite则不会
 * @author chenxushao
 */
public class ArrayListDemo1 {

    public static void main(String[] args) {

        List<String> datas = new ArrayList<>();
//        List<String> datas = Collections.synchronizedList(new ArrayList<>());
//        List<String> datas = new CopyOnWriteArrayList<>();
//        List<String> datas =  new Vector<>();

        DataContainer dataContainer = new DataContainer(datas);

        Thread addThread = new Thread(new AddTask(dataContainer),"addThread");
        addThread.start();

        Thread removeThread = new Thread(new RemoveTask(dataContainer),"removeThread");
        removeThread.start();
    }


    private static class AddTask implements Runnable {

        private DataContainer dataContainer;

        public AddTask(DataContainer dataContainer) {
            this.dataContainer = dataContainer;
        }


        @Override
        public void run() {
            while (true) {
                dataContainer.addData();
                ThreadUtil.sleep(1, TimeUnit.SECONDS);
            }
        }
    }

    private static class RemoveTask implements Runnable {

        private DataContainer dataContainer;

        public RemoveTask(DataContainer dataContainer) {
            this.dataContainer = dataContainer;
        }


        @Override
        public void run() {
            while (true) {
                dataContainer.removeData();
            }
        }
    }

    private static class DataContainer {

        private List<String> datas;

        public DataContainer(List<String> datas) {
            this.datas = datas;
        }

        public void addData() {
            String var = RandomUtil.randomStringFixLength(8);
            System.out.println("add var: " + var);
            datas.add(var);
        }

        public void removeData() {
            for (String data : datas) {
                System.out.println("remove: " + data);
                datas.remove(data);
            }
        }

    }
}



