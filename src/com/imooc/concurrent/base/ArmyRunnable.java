package com.imooc.concurrent.base;

//军队线程
//模拟作战双方的行为
public class ArmyRunnable implements Runnable {

	//volatile保证了线程可以正确读取其他线程写入的值
	//线程可见性 可以参考JMM，happens-before原则
	volatile boolean keepRunning = true;

	@Override
	public void run() {
		while (keepRunning) {
			//发动5连击
			for (int i = 0; i < 5; i++) {
				System.out.println(Thread.currentThread().getName()+"进攻对方["+i+"]");
				//让出处理器时间，下次该谁进攻还不一定呢！Thread.yield()方法的作用：暂停当前正在执行的线程，并执行其他线程
				//yield()让当前正在运行的线程回到可运行状态，以允许具有相同优先级的其他线程获得运行的机会。
				Thread.yield();
			}
		}
		System.out.println(Thread.currentThread().getName()+"结束了战斗！");
	}

}
