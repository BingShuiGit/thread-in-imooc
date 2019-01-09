package com.imooc.concurrent.base;

/**
 * 隋唐演义的大戏舞台
 * @author HD
 *
 */
public class Stage extends Thread {
	
	public void run() {
		
		System.out.println("欢迎收看隋唐演义！");
		//让观众安静片刻，等待大戏上演
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		System.out.println("大幕徐徐拉开");
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		System.out.println("话说隋朝当年，隋军与农民起义军杀的昏天暗地...");
		
		ArmyRunnable armyTaskOfSuiDynasty = new ArmyRunnable();
		ArmyRunnable armyTaskOfRevolt = new ArmyRunnable();
		
		//使用Runnable对象创建线程
		Thread armyOfSuiDynasty = new Thread(armyTaskOfSuiDynasty,"隋军");
		Thread armyOfDevolt = new Thread(armyTaskOfSuiDynasty,"农名起义军");
		
		//启动线程，让军队开始作战
		armyOfSuiDynasty.start();
		armyOfDevolt.start();
		
		//舞台线程休眠，大家专心观看军队的厮杀
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
		
//		armyTaskOfSuiDynasty.keepRunning = false;
//		armyTaskOfRevolt.keepRunning = false;
//		
//		try {
//			armyOfDevolt.join();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
		//关键人物出场
		System.out.println("正当双方激战，半路杀出个程咬金！");
		Thread mrCheng = new KeyPersonThread();
		mrCheng.setName("程咬金");
		System.out.println("程咬金的理想就是结束战争，使百姓安居乐业！");
		
		//军队停止作战，注意：停止线程不能使用armyOfSuiDynasty.Stop()方法，这个方法不管线程是否执行完就停止线程，让实时功能戛然而止
		armyTaskOfSuiDynasty.keepRunning = false;
		armyTaskOfRevolt.keepRunning = false;
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		/**
		 * 历史大戏让给关键人物
		 */
		mrCheng.start();
		try {
			//所有的线程会等待调用join()的线程执行结束
			mrCheng.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("战争结束，陈咬金实现理想！");
		
	}
	
	public static void main(String[] args) {
		new Stage().start();
	}

}
