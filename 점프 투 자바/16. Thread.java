//[1. Thread]
//1. start() method
public class Test extends Thread{//inheritance Thread class. 
	int seq;
	
	public Test(int seq){//constructor with seq as argument
		this.seq=seq;
	}
	
	public void run(){
		System.out.println(this.seq+" thread start.");
		try{
			Thread.sleep(1000);
		} catch(Exception e){}//for InterruptedException
		System.out.println(this.seq+" thread end.");
	}
	
	public static void main(String[] args){
		for(int i=0; i<10; i++){
			Thread t=new Test(i);
			t.start();//execute run method
			//if we want to exit main after finishing all threads, use join().
		}
		
		System.out.println("main end.");//before ending thread, main method is done!
	}
}

//2. join method
import java.util.ArrayList;

public class Test extends Thread{
	int seq;
	
	public Test(int seq){ this.seq=seq; }
	
	public void run(){
		System.out.println(this.seq+" thread Start.");
		try{
			Thread.sleep(1000);
		} catch(Exception e){}//for InterruptedException
		System.out.println(this.seq+" thread end.");
	}
	
	public static void main(String[] args){
		ArrayList<Thread> threads=new ArrayList<Thread>();
		for(int i=0; i<10; i++){
			Thread t=new Test(i);
			t.start();
			threads.add(t);//save to ArrayList for join
		}
		
		for(int i=0; i<thread.size(); i++){
			Thread t=threads.get(i);
			try{
				t.join();//wait for return. if use join without start, no execute!
			} catch(Exception e){}
		}
		
		System.out.println("main end");//it's printed after finishing threads.
	}
}

//[2. Runnable]
//Special difference is not. but just more flexiable for inheritance or..etc!
import java.util.ArrayList;

public class Test implements Runnable{//Runnable is already existing package
	int seq;
	public Test(int seq){this.seq=seq;}
	public void run(){
		System.out.println(this.seq+" thread start.");
		try{
			Thread.sleep(1000);
		}catch(Exception e){}//for InterruptedException
		
		System.our.println(this.seq+" thread end.");
	}
	public static void main(String[] args){
		ArrayList<Thread> threads=new ArrayList<Thread>();
		for(int i=0; i<10; i++){
			Thread t=new Thread(new Test(i));//By constructor of Thread, we can pass object that is made by Runnable interface.
			//Maybe,, the meaning of runnable, can get not only 'new Test();" but also class that is defined Runnable.
			t.start();
			threads.add(t);
		}
		for(int i=0; i<threads.size(); i++){
			Thread t=threads.get(i);
			try{
				t.join();
			}catch(Exception e){}
		}
		System.out.println("main end.");
	}
}