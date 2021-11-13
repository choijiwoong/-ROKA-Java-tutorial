/*1. SIMPLE EXAMPLE OF ABSTRACT CLASS
public class Main{
	public static void main(String[] args){
		SubImplemented sub1=new SubImplemented();
		sub1.showInfo();
		
		//TopAbtract top1=new SubImplemented();//ERROR!
		//top1.showInfo();
	}
}

abstract class TopAbstract{//0
	public abstract void showInfo();//we don't have to put abstract keyword on method like AbstractClass
	//String description;
	//public void show_des(){ System.out.println("des="+description); }//we can create method in Abstract class also.
}
class SubImplemented extends TopAbstract{//1
	@Override
	public void showInfo(){ System.out.println("*-this is sub implementation"); }
}

abstract class SubAbstract extends TopAbstract{//1
	pubilc abstract void subAbstract2ShowInfo();
}
class SubAbstract2 extends SubAbstract{//2
	public void subAbstract2ShowInfo(){ System.out.println("SubAbstract2 ShowInfo"); }
	
	@Override
	public void showInfo(){ System.out.println("SubAbstract2 implemented Top overrided"); }
	
	@override
	public void subAbstractShowInfo(){ System.out.println("SubAbstract1 ShowInfo implemented"); }
}*/

//2. SIMPLE EXAMPLE OF TEMPLATE BY ABSTRACT(반복되는 작업을 모아 처리하자. 다른 작업은 derived에서 처리하자)
import java.util.ArrayList;

public class Main{
	public static void main(String[] args){
		ArrayList<myTemplate> tmpList=new ArrayList<myTemplate>();
		
		myTemplate tmp1=new implementedOne();//use polymorphism of doSomething() code in myTemplate
		myTemplate tmp2=new implementedTwo();
		
		System.out.println("[----temp-1----]");
		tmp1.run();
		System.out.println("[----temp-2----]");
		tmp2.run();
		
		tmpList.add(tmp1);
		tmpList.add(tmp2);
		
		for(myTemplate mt: tmpList){
			System.out.println("[----temp ArrayList----]");
			mt.run();
		}
	}
}

abstract class myTemplate{
	public void initializing(){ System.out.println("Initializing..."); }
	public void start(){ System.out.println("Template Start"); }
	public abstract void doSomething();
	public void end(){ System.out.println("Template End");
	final public void run(){
		initializing();
		start();
		doSomething();
		end();
	}
}

class implementOne extends myTemplate{
	@Override
	public void doSomething(){ System.out.println("*Do Something Implementd - One");}
}
class implementedTwo extends myTemplate{
	@Override
	public void doSomething(){ System.out.println("*Do Something Implemented - Two"); }
}

/*
1.	추상클래스로 계속 내려가며 함수를 추가할 수 있고(Override도 포함), 마지막에만 구현하면 된다.
2.	추살클래스로 템플릿을 만든 후 특정 부분만 다르게 구현되게끔 상속받는 클래스를 만들어 오버라이드 한다. 그럼 ArrayList에서와 같이 Template으로 통일한 뒤, 부분적으로 다르게 작동하는 여러 작업들을 담을 수 있다.
*/