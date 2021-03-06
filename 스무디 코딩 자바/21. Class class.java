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
1.	object: ?????? ???????????? ??????(base)??????. ??????????????? JVM????????? ????????? ??????. ?????? ???????????? ??????????????? extends Object??? ??????.
	new, ?????? ????????? ??? ?????? ??????????????? ????????????.
2.	String: ==??? cmp hashkey, System.identityHashCode(String)?????? ?????? ????????? ??????, ""????????? new????????? ????????? ??????.
	????????? ????????? ???????????? new??? ??? ??? ??????????????? ????????????.
3.	Wrapper: ?????? ???????????? ???????????? ??????????????? ???????????????. Object??? ???????????? ??????????????? ?????? ?????? ??? ????????????. int?????? ???????????? ???????????? ???????????? ?????? ??????????????? ???????????????, ???????????????????????? ????????????????
	valueof??? String??? Wrapper???????????? ?????? ??? ?????????, parseInt?????? int????????? String??? ?????? ??? ??????.
	 Wrapper??? ????????? ????????? ??? ?????? Object??? extension??????????????? object??? ???????????? ????????? ??? ????????? ?????????.
4.	Class: .java????????? ?????? ??????????????? ????????? .class????????? ?????????, ??? ????????? ???????????? ???????????? ???????????????????????? ??????????????? ????????????. ??? ?????? ???????????? ???????????? ????????? ????????? ????????? ????????????.
	getClass(), toString(), .class ????????? ???????????? ????????? ??????????????? ??????(?????????)??? ??? ?????????.
	 java.lang.reflect.*??? ???????????? ???????????? ???????????? ???????????? ????????? ??? ??????.
	????????? ???????????? ????????? ????????? ????????? dynamic?????????????????? ??????????????? ????????? ??? ????????? ?????????.(p.s try catch?????? ????????? throw??? ???????????? ????????? ???????????? ?????? ?????? ?????? ????????? ???????????????)
	
*/