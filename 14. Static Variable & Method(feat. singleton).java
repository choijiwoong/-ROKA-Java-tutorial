//[1. Static Variable]

//1. simple concept of static variable
public class HousePark{
	//String lastname="Park";//little memory waste because String lastname is always making, but that's value won't be changed.
	static String lastname="Park";//one allocation!
	final static String information="it's const static variable! it can't be changed.";//like symbol(equ) in NASM!
	
	public static void main(String[] args){
		HousePark pey=new HousePark();
		HousePark pes=new HousePark();
	}
}

//2. necesity of static variable
//Counter program that adds view per visiting of website
public class Counter{
	int count=0;
	Counter(){
		this.count++;
		System.out.println(this.count);
	}
	
	public static void main(String[] args){
		Counter c1=new Counter();
		Counter c2=new Counter();//they get different memory!
	}
}
//solution
public class Counter{
	static int count=0;
	Counter(){
		this.count++;
		System.out.println(this.count);
	}
	
	public static void main(String[] args){
		Counter c1=new Counter();//1
		Counter c2=new Counter();//2
	}
}

//[2. Static Method]
//1. Simple concept of Static Method
public class Counter{
	static int count=0;
	Counter(){ this.counter++; }
	public static int getCount(){ return count; }//static method can access only static variable. not instance variable.
	
	public static void main(String[] args){
		Counter c1=new Counter();
		Counter c2=new Counter();
		
		System.out.println(Counter.getCount());
	}
}

//2. Static Method is well used for utilitic method like this
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util{
	public static String getCurrentDate(String fmt){//get format of Date by String as argument
		SimpleDateFormat sdf=new SimpleDateFormat(fmt);//SimpleDateFormat get format of data that will be printed
		return sdf.format(new Date());//new Date() return Date of today. SimpleDateFormat object convert date information to saved format by String.
	}
	
	public static void main(String[] args){
		System.out.println(Util.getCurrentDate("yyyyMMdd"));
	}
}

//[3. Singleton Pattern_only one object can be created forcely]
class Singleton{
	private static Singleton one;
	private Singleton(){}
	
	public static Singleton getInstance(){
		if (one==null)
			one=new Singleton();
		
		return one;
	}
}

public class SingletonTest{
	public static void main(String[] args){
		Singleton singleton1=Singleton.getInstance();//set static variable, get
		Singleton singleton2=Singleton.getInstance();//get static variable
		System.out.println(singleton1==singleton2);
		//Of coursely, This example of singleton is not safe in Tread.
	}
}