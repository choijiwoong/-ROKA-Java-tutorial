public class Main{
	public static void main(String[] args){
		myClassB inst1=new myClassB();
		inst1.showInfo();
		
		//
		System.out.println("\n----------creating base base ------------");
		baseClass myDCs=new baseClass();
		myDCs.showData();
		
		baseClass myCs=new baseClass(101, "base Class");
		myCs.showDate();
		
		System.out.println("\n------------ sub extends base -----------");
		subClass mySb=new subClss(201, "sub Class-1", "this is sub Class");
		mySb.showDate();
		mySb.showProtected();
		
		System.out.println("\n-------- casting to base -----------");
		baseClass base1=new subClass(301, "sub Class cast", "casting example");//IS-A
		base1.showData();
		
		System.out.println("\n--------- casting to sub explicit ----------");
		subClass sub1=(subClass)base1;//up-casting explicitly
		sub1.showProtected();
	}
}


class myClassA{
	private int id;
	private String name;
	
	public myClassA(int id, String name){
		this.id=id;
		this.name=name;
	}
	public void showInfo(){
		System.out.println("id = "+id);
		System.out.println("name = "+name);
	}
}

class myClassB extends myClassA{
	private String color;
	myClassB(){
		super(1001, "Don");//constructor of Parent class
	}
}

//
class baseClass{
	private int id;
	private String name;
	protected String text1;
	
	public baseClass(){
		id=100;
		name="null";
		System.out.println("[0- default base Class Created...]");
	}
	public baseClass(int id, String name){
		this.id=id;
		this.name=name;
		this.text1="protected one";
		System.out.println("[1- base Class Created...]");
	}
	public void showDate(){
		System.out.println("*id = "+id);
		System.out.println("*name = "+name);
	}
}

class subClass extends baseClass{
	private String description;
	
	public subClass(){
		super();
		description="no contents";
	}
	public subClass(int id, String name, String description){
		super(id, name);
		this.description=description;
		System.out.println("[2- sub Class Created...]");
	}
	
	public void showData(){
		super.showDate();
		System.out.println("#description = "+this.description);
	}
	public void showProtected(){
		System.out.println("#super.text1 = "+super.text1);
	}
	
	public void tryPrivate(){
		//System.out.println("id = "+id);//error
	}
}

/*
1.	super은 상속도에서 상위 클래스의 생성자를 호출하는 키워드이다. this와 비슷하다. super는 base츼 참조, this는 현재 클래스의 참조이다.(인스턴스에 접근하는 해시 키)
2.	base클래스는 일반형클래스이기에 최소한의 기능만 두고, 확장으로 심화시켜 추상클래스(abstract class)를 만든다.
	상속받은 것중에 바꾸고 싶은 것은 메소드 오버라이딩을 하고, 기존의 코드를 사용하고 싶다면 super을 이용하여 public과 protected를 사용하자.
3.	base타입의 sub인스턴스는 부분집합적으로 sub의 인스턴스에 접근할 수 있지만, sub타입(확장)에 base를 넣어버리면 sub가 base에 없는 멤버변수에 접근이 불가하기에 불가능하다.
	(업 캐스팅이면 가능, 다운캐스팅은 불가. 다만 강제적인 캐스팅은 가능? virtual in C++?)
*/