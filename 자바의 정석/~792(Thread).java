import java.util.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.locks.Condition;
import javax.swing.JOptionPane;

public class tutorial {
	static final ForkJoinPool pool=new ForkJoinPool();//p.791 
	public static void main(String[] args) throws Exception{
		/*
		System.out.println("[p.730]");//Difference of start(), run()
		ThreadEx1 t1=new ThreadEx1();
		t1.start();//Make CallStack on run
		t1.run();//Just execution method
		Thread.sleep(1);
		
		System.out.println("\n\n[p.742]");//ThreadGroup
		ThreadGroup main=Thread.currentThread().getThreadGroup();//return ThreadGroup current is in.
		ThreadGroup grp1=new ThreadGroup("Group1");
		ThreadGroup grp2=new ThreadGroup("Group2");
		
		ThreadGroup subGrp1=new ThreadGroup(grp1, "SubGroup1");
		
		grp1.setMaxPriority(3);//set priority
		
		Runnable r=new Runnable() {
			public void run() {
				try {
					Thread.sleep(1000);
				}catch(InterruptedException e) {}
			}
		};
		
		new Thread(grp1, r, "th1").start();//make Thread with r to grp1 & start
		new Thread(subGrp1,r,"th2").start();
		new Thread(grp2, r, "th3").start();
		
		System.out.println(">>List of ThreadGroup: "+main.getName()+", Active ThreadGroup: "+main.activeGroupCount()+", Active Thread: "+main.activeCount());
		main.list();
		
		
		System.out.println("\n\n[p.746]");//Check CallStack's status by getAllStackTraces()
		ThreadEx2_1 t3=new ThreadEx2_1("Thread 1");
		ThreadEx2_2 t4=new ThreadEx2_2("Thread 2");//Show All Thread & Show All CallStack per Thread.
		t3.start();
		t4.start();
		
		
		System.out.println("\n\n[p.753]");//let's stop thread by interrupt()
		ThreadEx3 t5=new ThreadEx3();
		t5.start();
		
		String input=JOptionPane.showInputDialog("type any value");
		System.out.println("Input Value: "+input);
		t5.interrupt();//make status of interrupted to true
		System.out.println("isInterrupted(): "+t5.isInterrupted());
		
		
		System.out.println("\n\n[p.757, 759]");//use of suspend(Deprecated), resume(Deprecated), stop(Safe to Deadlock)
		RunImplEx4 r1=new RunImplEx4();//Runnable
		RunImplEx4 r2=new RunImplEx4();
		RunImplEx4 r3=new RunImplEx4();
		Thread th1=new Thread(r1, "*");//Make Thread with Runnable
		Thread th2=new Thread(r2, "**");
		Thread th3=new Thread(r3, "***");
		th1.start();
		th2.start();
		th3.start();
		
		try {
			Thread.sleep(2000);
			r1.suspend();//not th1.suspend()
			Thread.sleep(2000);
			r2.suspend();
			Thread.sleep(3000);
			r1.resume();//why can't revive r1..
			Thread.sleep(3000);
			r1.stop();
			r2.stop();
			Thread.sleep(2000);
			r3.stop();
		}catch(InterruptedException e) {}
		Thread.sleep(5000);
		
		//Advenced Version(more objective)
		System.out.println("\n[p.759]");
		ThreadEx4 th4=new ThreadEx4("*");
		ThreadEx4 th5=new ThreadEx4("**");
		ThreadEx4 th6=new ThreadEx4("***");
		th4.start();
		th5.start();
		th6.start();
		
		try {
			Thread.sleep(2000);
			th4.suspend();
			Thread.sleep(2000);
			th5.suspend();
			Thread.sleep(3000);
			th4.resume();
			Thread.sleep(3000);
			th4.stop();
			th5.stop();
			Thread.sleep(2000);
			th6.stop();
		}catch(InterruptedException e) {}
		
		
		System.out.println("\n\n[p.760]");//If Thread is suspended, yield to other thread. for prevention of busy-wating state(no meaningful loop)
		ThreadEx5 th7=new ThreadEx5("*");
		ThreadEx5 th8=new ThreadEx5("**");
		ThreadEx5 th9=new ThreadEx5("***");
		th7.start();
		th8.start();
		th9.start();
		
		try {
			Thread.sleep(2000);
			th7.suspend();
			Thread.sleep(2000);
			th8.suspend();
			Thread.sleep(3000);
			th7.resume();
			Thread.sleep(3000);
			th7.stop();
			th8.stop();
			Thread.sleep(2000);
			th9.stop();
		}catch(InterruptedException e) {}
		
		
		System.out.println("\n\n[p.765]");//Garbage Collector by using interrupt of Daemon Thread.
		ThreadEx6 gc=new ThreadEx6();
		gc.setDaemon(true);//Daemon
		gc.start();
		
		int requiredMemory=0;
		
		for(int i=0; i<20; i++) {
			requiredMemory=(int)(Math.random()*10)*20;
			
			if(gc.freeMemory()<requiredMemory || gc.freeMemory()<gc.totalMemory()*0.4) {
				gc.interrupt();
			}
			gc.usedMemory+=requiredMemory;
			System.out.println("usedMemory: "+gc.usedMemory);
		}
		
		
		
		System.out.println("\n\n[p.783]");//Condition, Lock(ReentrantLock)
		Table table=new Table();
		
		new Thread(new Cook(table), "COOK1").start();
		new Thread(new Customer(table, "donut"), "CUST1").start();
		new Thread(new Customer(table, "burger"), "CUST2").start();
		
		Thread.sleep(10000);//쉬바류ㅠ
		System.exit(0);
		*/
		
		
		System.out.println("\n\n[p.791]");
		long from=1L, to=100_000_000L;
		
		SumTask task=new SumTask(from, to);
		Long start=System.currentTimeMillis();
		Long result=pool.invoke(task);//add to ForkJoinPool for using fork(), join()
		System.out.println("Elapsed time(4 Core): "+(System.currentTimeMillis()-start));
		
		System.out.printf("Sum of %d~%d=%d%n", from, to, result);
		System.out.println();
		
		result=0L;
		start=System.currentTimeMillis();
		for(long i=from; i<=to; i++)
			result+=i;
		
		System.out.println("Elapsed time(1 Core): "+(System.currentTimeMillis()-start));
		System.out.printf("dum of %d~%d=%d%n", from, to, result);
		
	}//main END
	
}//tutorial END

class ThreadEx1 extends Thread{//p.730
	public void run() {
		throwException();
	}
	
	public void throwException() {
		try {
			throw new Exception();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}


class ThreadEx2_1 extends Thread{//p.746
	ThreadEx2_1(String name){ super(name); }
}

class ThreadEx2_2 extends Thread{
	ThreadEx2_2(String name){ super(name); }
	
	public void run() {
		Map map=getAllStackTraces();//get all stack
		Iterator it=map.keySet().iterator();//make iterator of key
		
		int x=0;
		while(it.hasNext()) {
			Object obj=it.next();//get key
			Thread t=(Thread)obj;//to Thread
			StackTraceElement[] str=(StackTraceElement[])(map.get(obj));//Get Value of Thrad to StackTraceElement[] getting by keyvalue(thread)
			System.out.println("["+ ++x +"]name: "+t.getName()+", group: "+t.getThreadGroup().getName()+ ", daemon: "+t.isDaemon());
			//print Thread's name, Groupname, isdasemon
			for(int i=0; i<str.length; i++)
				System.out.println(str[i]);//print values of Thread
			System.out.println();
		}
	}
}


class ThreadEx3 extends Thread{//p.753
	public void run() {
		int i=10;
		
		while(i!=0&&!isInterrupted()) {
			System.out.println(i--);
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) {
				interrupt();//IMPORTANT! when interrupt occur in sleeping, make InterruptedException and isInterrupted will be initialized to false.
				//Then, while's condition can't catch interrupt(). So we have to add interrupt() in catch for changing isInterrupt's false to true
			}
		}
		System.out.println("End Count!");
	}
}


class RunImplEx4 implements Runnable{//p.757
	boolean suspended=false;
	boolean stopped=false;
	
	public void run() {
		while(!stopped) {
			if(!suspended) {
				System.out.println(Thread.currentThread().getName());
				try {
					Thread.sleep(1000);
				}catch(InterruptedException e) {}
			}
		}
		System.out.println(Thread.currentThread().getName()+"-stopped");
	}
	
	public void suspend() { suspended=true; }
	public void resume() { suspended=false; }
	public void stop(){ stopped=true; }
}
class ThreadEx4 implements Runnable{//p.759
	boolean suspended=false;
	boolean stopped=false;
	
	Thread th;
	
	ThreadEx4(String name){ th=new Thread(this, name); }//Make Thread with this Runnable object.
	
	public void run() {
		while(!stopped) {
			if(!suspended) {
				System.out.println(Thread.currentThread().getName());
				try {
					Thread.sleep(1000);
				}catch(InterruptedException e) {}
			}
		}
		System.out.println(Thread.currentThread().getName()+"-stopped");
	}
	
	public void suspend() { suspended=true; }
	public void resume() { suspended=false; }
	public void stop() { stopped=true; }
	public void start() { th.start(); }
}


class ThreadEx5 implements Runnable{//p.760
	boolean suspended=false;
	boolean stopped=false;
	
	Thread th;
	
	ThreadEx5(String name){ th=new Thread(this, name); }
	
	public void run() {
		String name=th.getName();
		
		while(!stopped) {
			if(!suspended) {
				System.out.println(name);
				try {
					Thread.sleep(1000);
				}catch(InterruptedException e) {
					System.out.println(name+"-interruped");
				}
			}else {
				Thread.yield();
			}
			System.out.println(name+"-stopped");
		}
	}
	
	public void suspend() {
		suspended=true;
		th.interrupt();
		System.out.println(th.getName()+"-interrupt() by suspend()");
	}
	public void stop() {
		stopped=true;
		th.interrupt();
		System.out.println(th.getName()+"interrupt() by stop()");
	}
	public void resume() {suspended=false;}
	public void start() {th.start();}
}


class ThreadEx6 extends Thread{//p.764
	final static int MAX_MEMORY=1000;
	int usedMemory=0;
	
	public void run() {
		while(true) {
			try {//Keep Sleep.
				Thread.sleep(10*1000);
			}catch(InterruptedException e) {//if interrupt occur, awake and do job.
				System.out.println("Awaken by interrupt().");
			}
			gc();
			System.out.println("Garbage Collected. Free Memory: "+freeMemory());
		}
	}
	
	public void gc() {
		usedMemory-=300;
		if(usedMemory<0)
			usedMemory=0;
	}
	public int totalMemory() { return MAX_MEMORY; }
	public int freeMemory() { return MAX_MEMORY-usedMemory; }
}


class Customer implements Runnable{//p.783
	private Table table;//Customer and Cook is just class. mutex playing is on Table class.
	private String food;
	
	Customer(Table table, String food){
		this.table=table;
		this.food=food;
	}
	
	public void run() {
		while(true) {
			try {
				Thread.sleep(100);
				String name=Thread.currentThread().getName();
				
				table.remove(food);
				System.out.println(name+" ate a "+food);
			}catch(InterruptedException e) {}
		}
	}
}
class Cook implements Runnable{
	private Table table;
	
	Cook(Table table){ this.table=table; }
	
	public void run() {
		while(true) {
			int idx=(int)(Math.random()*table.dishNum());
			table.add(table.dishNames[idx]);
			try {
				Thread.sleep(10);
			}catch(InterruptedException e) {}
		}
	}
}

class Table{
	String[] dishNames= {"donut", "donut", "burger"};
	final int MAX_FOOD=6;
	private ArrayList<String> dishes=new ArrayList<>();//current dishes on table
	
	private ReentrantLock lock=new ReentrantLock();//Reentrantable Lock define.
	private Condition forCook=lock.newCondition();//Make condition for activate lock.
	private Condition forCust=lock.newCondition();
	
	public void add(String dish) {//for cook
		lock.lock();//for synchronizing
		
		try {//아직 다 해결하지 못함!! dishNames가 donut이 더 많이 나오게 되어있어 하나의 항목으로 다 접시가 차면 busy-waiting.
			//아쉬발....메인에서 sleep1000때문에 금방 끝난거였어ㅠㅠ내가 잘못 해결한게 아니었다고ㅜㅠㅠㅜㅠㅜ
			if(dishes.size()==MAX_FOOD && !(dishes.contains("donut") && dishes.contains("burger"))) {//when one dish is fulled in dishes
				System.out.println("FULLED!");
				//if(dishes.contains("donut"))
					//dishes.remove("donut");
				//else
					//dishes.remove("burger");
				dishes.clear();
			}
			
			while(dishes.size()>=MAX_FOOD) {//if over dishes, wait for working of Customer.
				String name=Thread.currentThread().getName();
				System.out.println(name+" is waiting. ");
				try {
					forCook.await();//cooker [wait] for customer's eating
					Thread.sleep(500);
				}catch(InterruptedException e) {}	
			}//now, dishes have enough space for new dish
			dishes.add(dish);
			forCust.signal();//[nofity] Food is added! you can eat it
			System.out.println("Dished: "+dishes.toString());//refresh dishes
		}finally {
			lock.unlock();//unlock for customer's activation
		}
	}
	
	public void remove(String dishName) {//for customer
		lock.lock();//synchronized(this)
		String name=Thread.currentThread().getName();
		
		try {
			while(dishes.size()==0) {
				System.out.println(name+" is waiting.");
				try {
					forCust.await();//[wait] customer for making new dish
					Thread.sleep(500);
				}catch(InterruptedException e) {}
			}
			
			while(true) {
				for(int i=0; i<dishes.size(); i++) {
					if(dishName.equals(dishes.get(i))){
						dishes.remove(i);
						forCook.signal();//[notify]
						return;
					}
				}
				
				try {
					System.out.println(name+" is waiting");//wait for making food we want
					forCust.await();//customer wait for making new food
					Thread.sleep(500);
				}catch(InterruptedException e) {}
			}
		} finally {
			lock.unlock();
		}
	}
	
	public int dishNum() { return dishNames.length; }
}


class SumTask extends RecursiveTask<Long>{//p.792 this class(extending RecursiveTask) will be worked in ForkJoinPool
	long from, to;
	
	SumTask(long from, long to){
		this.from=from;
		this.to=to;
	}
	
	public Long compute() {//By it! it will be worked in Pool
		long size=to-from+1;
		
		if(size<=5)
			return sum();
		
		long half=(from+to)/2;
		SumTask leftSum=new SumTask(from, half);
		SumTask rightSum=new SumTask(half+1, to);
		
		leftSum.fork();//computing!
		
		return rightSum.compute()+leftSum.join();//computing! and get
	}
	
	long sum() {
		long tmp=0L;
		
		for(long i=from; i<=to; i++)
			tmp+=i;
		return tmp;
	}
}