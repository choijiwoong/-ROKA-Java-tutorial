/*[1. java.lang.Math]
public class Main{
	public static void main(String[] args){
		//1-1. common use
		double myRand=Math.random();
		
		for(int i=0; i<3; i++){
			System.out.println(i+": "+myRand);
			myRand=Math.random();
		}
		System.out.println("finally: "+myRand);
		//0: 0.778098473452544
		//1: 0.20028682836479783
		//2: 0.6995866111503665
		//finally : 0.946285960565024
		
		//1-2. use to int
		int myRand=0;
		for(int i=0; i<3; i++){
			myRand=(int)(Math.random()*10)+1;
			System.out.println(i+": "+myRand);
		}
		//0: 3
		//1: 7
		//2: 10
		
		//2-3. user defined by Math.random()
		for(int i=0; i<3; i++)
			showMyRandom(5, 15);
	}
	
	public static void showMyRandom(int min, int range){
		int myRand=0;
		System.out.println((int)(Math.random()*range)+min);
	}
}*/

/*[2. java.util.Random]
import java.util.Random;

public class Main{
	public static void main(String[] args){
		Random myRand=new Random();//make instance
		
		System.out.println();
		System.out.println("[default seed]");
		for(int i=0; i<3; i++)
			System.out.println(myRand.nextInt());//get as int format
		
		System.out.println();
		System.out.println("[new seed]");
		Random newSeed=new Random(System.currentTimeMillis()*14);//System.currentTimeMillis() as argument for seed set
		for(int i=0; i<3; i++)
			System.out.println(newSeed.nextInt(10));//argument of nextInt is range. 0~9
	}
}*/

//[3. ThreadLocalRandom]
import java.util.concurrent.ThreadLocalRandom;

public class Main{
	public static void main(String[] args){
		int myRand1=ThreadLocalRandom.current().nextInt(100);
		int myRand2=ThreadLocalRandom.current().nextInt(100);
		
		System.out.println("I: "+myRand1);
		System.out.println("I: "+myRand2);
		
		double myRand3=ThreadLocalRandom.current().nextDouble(100);
		double myRand4=ThreadLocalRandom.current().nextDouble(100);
		
		System.out.println("D: "+myRand3);
		System.out.println("D: "+myRand4);
	}
}

/*
1.	Math패키기의 static random함수를 이용하여 double형 0.0~1.0범위의 난수를 얻을 수 있다. 번거롭지만 우리가 메소드로 정의해서 알맞게 사용하면 된다.
2.	java.util.Random은 java.lang.Math보다 향상된 클래스로 인스턴스를 생성하여 사용하는 unstatic 메소드이다.
	시드값을 인자로 하는 인스턴스를 생성하여 .nextInt(range)등의 원하는 format의 메소드를 사용하면 값이 반환된다.
3.	ThreadLocalRandom은 스레드별로 독립적인 랜덤클래스기때문에, 스레드가 각자 격리된 상태라면 Random클래스보다 고품질의 난수생성을 기대할 수 있다. static이다 current().nextInt()와 같이 사용할 수 있다.
*/