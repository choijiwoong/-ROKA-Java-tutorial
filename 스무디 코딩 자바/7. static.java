public class Main{
	public static void main(String[] args){
	//[1. static variable]
		//1. character of static variable
		System.out.println(myStatic.count);//exceution without instantiation of myStatic class
		
		//2. static variable can be used every instance
		System.out.println(myStatic.count);//print static var
		myStatic m1=new myStatic();//make instance
		System.out.println(m1.count);//print instance's static var
		myStatic m2=new myStatic();//make instance 2
		System.out.println(m1.count);//print m1's static var
		m1.count++;
		m2.count++;//plus at each instance
		myStatic.count++;
		System.out.println(myStatic.count);//5
	//[2. static method]
		myStatic.showInfo();
		myStatic.count++;
		myStatic.showInfo();
		
	//[3. private static]
		System.out.println(PmyStatic.getCount());
		PmyStatic.setCount(10);
		System.out.println(PmyStatic.getCount());
		
		PmyStatic pm1=new PmyStatic();
		pm1.setCount(20);
		System.out.println(pm1.getCount());
	//[4. common mistake in static]
		//PmyStatic.showName();//BAD!
		PmyStatic solob=new PmyStatic();
		solob.showName();//BAD! this in static showName is name of class! if we use this.name in showName(), we have to change String name to static String name
	}
}
class myStatic{
	static int count=0;//it's called 'class variable' too
	public myStatic(){//Constructor
		count++;
	}
	//
	public static void showInfo(){ System.out.println("count is : "+count); }
}
class PmyStatic{
	public static int getCount(){ return count; }
	public static void setCount(int count){ PmyStatic.count=count; }
	private static int count=0;//can't use in other class without public getter & setter!
	
	//if we use instance variable in static method that is not instantiated?
	String name;
	public static void showName(){ System.out.println(this.name); }//Error occur! instance's variable is called in static function.
	//(p.s if you want to execute this code statically, String name change to static String name=new String("asjaisfj"), this.name change to name.)
	//(p.s if you want to execute this code in instance, remove static of public static void showname()~
}

/*
	[static variable]
1.	인스턴스 변수들이 서로 값을 공유해야 하는 경우에 자원의 낭비를 막기 위해 사용한다. 프로그램 실행 시 이미 메모리에 생성이 된다. 클래스와 인스턴스가 공유하는 값이라는 것이 포인트이다.
2.	인스턴스의 참조변수(해시값)는 스택메모리에 있다. 이들의 실제 값은 힙메모리 영역에 있다. 데이터 영역은 프로그램 시작부터 끝까지 남아있는데, Stack과 Heap영역은 GC에 의하여 더이상 쓸모없다고 판단되면 해제한다.
	이때 메모리 유출을 방지하기 위해 Heap영역을 먼저 해제시킨다.
	
	[static method]
1.	static variable과 같이 instantiation없이 실행이 가능한 method이다.
2.	private static 변수는 다른 클래스에서 사용하기 위해서는 getter와 setter을 사용해야한다.
*/