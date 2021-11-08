/*[0. Call by value & reference]
class Updater{
	public void update_by_value(int count){ count++; }
	public void update_by_object(Counter counter){ counter.count++; }
}

public class Counter{//many class in one file is ok, but class that's name is same to file name, it has to make as public.
	int count=0;
	public static void main(String[] args){
		Counter myCounter=new Counter();
		System.out.println("before update: "+myCounter.count);
		
		Updater myUpdater=new Updater();
		myUpdater.update_by_value(myCounter.count);
		System.out.println("after update: "+myCounter.count);//no change! [Call by value]
		
		myUpdater.update_by_object(myCounter);
		System.out.println("after update: "+myCounter.count);//change! [call by reference]
	}
}*/

//[1. inheritance]
class Animal{
	String name;
	public void setName(String name){ this.name=name; }
}


class HouseDog extends Main{
	public void sleep() { System.out.println(this.name+" zzz in house"); }
	public void sleep(int hour) { System.out.println(this.name+" zzz in hous for "+hour+" hours"); }//overload
}

public class Main extends Animal{
	public void sleep(){ System.out.println(this.name+" zzz"); }
	
	public static void main(String[] args){
		Main dog=new Main();
		dog.setName("poppy");
		System.out.println(dog.name);
		dog.sleep();
		
		//[2. IS-A] Dog is a Animal!
		Animal hot__dog=new Main();//possible!
		//Main cold_dog=new Animal();//impossible!
		
		//[3. All class are inheritanced Object Class]
		Object animal=new Animal();//possible by IS-A
		Object animal2=new Main();//possible by IS-A
		
		//[4. Method overriding] parent's method is covered by derived's method
		HouseDog houseDog=new HouseDog();
		houseDog.setName("happy");
		houseDog.sleep();//print "happy zzz in house" not "happy zzz" because of Method overriding!
		
		//[5. Method overloading]
		houseDog.sleep(3);//same method name, but compiler judge as different method by arguments
		
		//[6. multiple inheritance] Java doesn't support multiple inheritance.
		//class A{ public void msg(){ System.out.println("A message");}}
		//class B{ public void msg(){ System.out.println("B message");}}
		//class C extends A,B{ public void static main(String[] args){
		//	C test=new C();
		//	test.msg();
		//}
		//Java removes this ambigous situations. without introduction of priority
	}//후...파일 분할된거 파일 하나에 담으려니 힘드넴.. 요약하긴 이게 좋은데 어찌하징..
}