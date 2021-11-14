/*[1. Example of Class class]
public class Main{
	public static void main(String[] args) throws ClassNotFoundException{
		//1
		String str1=new String("my String 1");
		Class cls=str1.getClass();//print instance's class that is made dynamically by new
		
		System.out.println("cls of str1: "+cls.toString());
		
		//2
		Class cls1=String.class;
		System.out.println("cls1: "+cls1.toString());
		
		//3
		Class cls2=Class.forName("java.lang.String");
		System.out.println("cls2: "+cls2.toString());
		
		//------
		//1
		CustomClass cc1=new CustomClass(201, "Custom Class 1");
		Class cls3=cc1.getClass();//default method of class
		System.out.println("cls3.toString(): "+cls3.toString());
		
		//2
		Class cls4=CustomClass.class;
		System.out.println("cls4.toString(): "+cls4.toString());
		
		//3
		Class cls5=Class.forName("com.kay.CustomClass");
		System.out.println("cls5.toString(): "+cls5.toString());
	}
}

class CustomClass{
	int id;
	String name;
	
	public CustomClass(int id, String name){
		this.id=id;
		this.name=name;
	}
}*/

/*[2. Get Class informatino]
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Main{
	public static void main(String[] args) throws ClassNotFoundException{
		Class StrCls=Class.forName("java.lang.String");
		
		System.out.println("------- Constructor");
		Constructor[] constr=strCls.getConstructors();
		for(Constructor cl: constr)
			System.out.println(cl);
		
		System.out.println("------- Fields");
		Field[] fields=strCls.getFields();
		for(Field fl: fields)
			System.out.println(fl);
		
		System.out.println("------- Methods");
		Method[] methods=strCls.getMethods();
		for(Method ml: methods)
			System.out.println(ml);
		
		//Get User Defined Class
		System.out.println("---------------------------");
		Class myCls=Class.forName("com.kay.myTestClass");
		
		System.out.println("-------- Constructor");
		Constructor[] constr2=myCls.getConstructors();
		for(Constructor cl: constr2)
			System.out.println(cl);
		
		System.out.println("-------- Fields");
		Field[] fields2=myCls.getFields();
		for(Field f2: fields2)
			System.out.println(f2);
		
		System.out.println("-------- Methods");
		Method[] methods2=myCls.getMethods();
		for(Method ml: methods2)
			System.out.println(ml);
	}
}

class myTestClass{
	public int id;
	public String name;
	
	public myTestClass(int id, String name){
		this.id=id;
		this.name=name;
	}
	
	public int getId(){ return id; }
	public void setId(int id){ this.id=id; }
	public String getName(){ return name; }
	public void setName(String name){ this.name=name; }
}*/

//[3. Dynamic Loading & Creation of Instance]
import java.lang.reflect.InvocationTargetException;

public class Main{
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException{
		myTestClass test=new myTestClass();
		System.out.println(test);
		
		System.out.println("---------------------");
		Class customClass=Class.forName("com.kay.myTestClass");//get Class object of test object dynamically
		System.out.println(customClass);
		
		myTestClass loadDynamic=(myTestClass)customClass.getDeclaredConstructor().newInstance();//make instance by Class object's constuctor with casting(how can we input arguement to constructor?)
		System.out.println(loadDynamic);//different instance with customClass object.
		loadDynamic.setId(1001);
		loadDynamic.setName("new Instance loaded dynamic");
		
		System.out.println("----------------------");
		System.out.println(loadDynamic.getName());
		System.out.println(loadDynamic.getId());
	}
}

class myTestClass{
	public int id;
	public String name;
	
	public myTestClass(){this(0,"null");}
	public myTestClass(int id, String name){
		this.id=id;
		this.name=name;
	}
	
	public int getId(){ return id; }
	public void setId(int id){ this.id=id; }
	public String getName(){ return name; }
	public void setName(String name){ this.name=name; }
}


/*
	[Common Class in java.lang]
1.	object: 모든 클래스의 조상(base)이다. 운용체제와 JVM사이의 관리를 한다. 모든 클래스는 암묵적으로 extends Object를 한다.
	new, 각종 연산자 등 여러 메소드들이 들어이다.
2.	String: ==로 cmp hashkey, System.identityHashCode(String)으로 진짜 메모리 주소, ""생성과 new생성의 차이가 있다.
	최적화 기법을 사용하는 new가 좀 더 안정적으로 판단된다.
3.	Wrapper: 기본 자료형을 클래스로 만들어주는 클래스이다. Object로 형변환이 가능하다는 것이 가장 큰 이점이다. int보단 아무래도 메소드나 상수들로 인해 속도적으로 불리하지만, 하드웨어성능으로 극복?해보자
	valueof로 String을 Wrapper클래스에 담을 수 있으며, parseInt꼴로 int변수에 String을 담을 수 있다.
	 Wrapper의 또다른 장점은 이 또한 Object의 extension이기때문에 object의 메소드를 사용할 수 있다는 것이다.
4.	Class: .java파일이 자바 컴파일러를 거치면 .class파일이 되는데, 이 파일에 컴파일된 클래스나 인터페이스정보를 가져오는데 사용한다. 즉 자바 프로그램 실행중에 동적인 클래스 로드가 가능하다.
	getClass(), toString(), .class 등으로 동적으로 할당된 인스턴스의 소속(클래스)를 알 수있다.
	 java.lang.reflect.*의 패키지를 사용하여 클래스의 정보들을 가져올 수 있다.
	가져온 클래스의 정보를 토대로 또다른 dynamic로딩방식으로 인스턴스를 생성할 수 있다는 말이다.(p.s try catch와는 별개로 throw를 처리하지 않으면 컴파일이 안될 때도 있기 때문에 추가해주장)
	
*/