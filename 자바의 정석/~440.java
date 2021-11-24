public class tutorial {
	public static void main(String[] args){
		System.out.println("\n[p.279]");
		//p.279_Consider methods in class with definition as static, if it can work without instance.
		System.out.println(MyMath.add(200L, 100L));//Callable without creating object.
		System.out.println(MyMath.subtract(200L, 100L));
		System.out.println(MyMath.multiply(200L, 100L));
		System.out.println(MyMath.divide(200.0, 100.0));
		
		MyMath mm=new MyMath();//make instance
		mm.a=200L;
		mm.b=100L;
		System.out.println(mm.add());//Callable with creating object only.
		System.out.println(mm.subtract());
		System.out.println(mm.multiply());
		System.out.println(mm.divide());
		
		//We can use it also if we need only result of add method with instance
		long addResult=new MyMath().methodGetLong();
		System.out.println(addResult);
		
		
		System.out.println("\n[p.306]");
		//p.306_Simple example of initialization block that contains same part of Constructors
		Product p1=new Product();
		Product p2=new Product();
		Product p3=new Product();
		
		System.out.println("Serial Number of p1: "+p1.serialNo);
		System.out.println("Serial Number of p2: "+p2.serialNo);
		System.out.println("Serial Number of p3: "+p3.serialNo);
		System.out.println("Total products count: "+Product.count);
		//We can use this idea(instance variable by static variable) to creating new file with name like "Untitled1.txt", "Untitled2.txt"...
		
		
		System.out.println("\n[p.321]");
		//p.321_Use System.out.println() for printing object by definition of method 'toString()'
		Deck d=new Deck();
		Card c=d.pick(0);
		System.out.println(c);//[==System.out.println(c.toString());]
		
		d.shuffle();
		c=d.pick(0);
		System.out.println(c);//[==System.out.println(c.toString());]
		
		/*
		System.out.println("\n[p.332]");
		//p.332_If we don't use super() in Derived's Constructor, Compiler automatically execute super().
		//But in this time, if Based's default constructor_Based(){} is not defined, Error will be occured.
		Point3D p3=new Point3D();
		System.out.println("p3.x: "+p3.x);
		System.out.println("p3.y: "+p3.y);
		System.out.println("p3.z: "+p3.z);
		*/
		
		System.out.println("\n[p.359]");
		//p.359_nessecity of instanceof & simple example of casting to Based, Derived
		Car car=null;
		FireEngine fe=new FireEngine();
		FireEngine fe2=null;
		
		fe.water();//Good!
		car=fe;
		//car.water();//ERROR! car doesn't have water method although instanceof(car) is FireEngine.
		
		fe2=(FireEngine)car;
		fe2.water();//GOOD! For use water(), casting is needed!(downcasting)
		
		
		System.out.println("\n[p.364]");
		//p.364_On overriding method, virtual or instanceof is automatically applied. Based is just container for Derived
		Parent P=new Child();//Child object to Parent type
		Child C=new Child();
		
		System.out.println("P.x: "+P.x);//P.x: 100	Regardless of P's type, Child object works!
		P.method();//Child Method
		
		System.out.println("C.x: "+C.x);//C.x: 200
		C.method();//Child Method
		
		
		System.out.println("\n[p.388]");
		//p.388_Return type is interface==consisted object will be returned
		Parseable parser=ParserManager.getParser("XML");
		parser.parse("document.xml");
		parser=ParserManager.getParser("HTML");
		parser.parse("document2.html");
		
		
		System.out.println("\n[p.439]");
		//p.439_User defined Exception_only check Class under..
	}
}

class MyMath{//[p.279]
	long a, b;
	long add() { return a+b; }//These methods use instance variable a, b. so it doesn't need argument
	long subtract() { return a-b; }
	long multiply() { return a*b; }
	double divide() { return a/b; }
	long methodGetLong() { System.out.println("Instance Method in MyMath!"); return 2000L; }
	
	//[We have to consider defining method as static if it doesn't need any instance variable&method for Optimization of compiler.]
	static long add(long a, long b) { return a+b; }//These methods use argument only regardless of instance variables.
	static long subtract(long a, long b) { return a-b; }
	static long multiply(long a, long b) { return a*b; }
	static double divide(double a, double b) { return a/b; }
}


class Product{//[p.306]
	static int count=0;
	int serialNo;
	
	{//[initialization block! for constructors]
		++count;
		serialNo=count;
	}
	public Product() {};
}


class Deck{//[p.321]
	final int CARD_NUM=52;
	Card cardArr[]=new Card[CARD_NUM];//Card object array
	
	Deck() {//initialization of cardArr
		int i=0;
		for(int k=Card.KIND_MAX; k>0; k--)//Shape
			for(int n=0; n<Card.NUM_MAX; n++)//Number
				cardArr[i++]=new Card(k, n+1);
	}
	
	Card pick(int index) { return cardArr[index]; }//index pick
	Card pick() { return pick( (int)(Math.random()*CARD_NUM) ); }//random pick
	
	void shuffle() {
		for(int i=0; i<cardArr.length; i++) {
			int r=(int)(Math.random()*CARD_NUM);
			
			Card temp=cardArr[i];
			cardArr[i]=cardArr[r];
			cardArr[r]=temp;
		}
	}
}
class Card{
	static final int KIND_MAX=4;
	static final int NUM_MAX=13;
	
	static final int SPADE=4;
	static final int DIAMOND=3;
	static final int HEART=2;
	static final int CLOVER=1;
	int kind;
	int number;
	
	Card(){ this(SPADE, 1); }
	Card(int kind, int number){ this.kind=kind; this.number=number; }
	
	public String toString() {//[For use in System.out.println()]
		String[] kinds= {"", "CLOVER", "HEART", "DIAMOND", "SPADE"};
		String numbers="0123456789XJQK";//X=10
		
		return "kind: "+kinds[this.kind]+", number: "+numbers.charAt(this.number);
	}
}

/*
class Point{//[p.332]
	int x,y;
	
	Point(int x, int y){ this.x=x; this.y=y; }
	String getLocation() { return "x: "+x+", y: "+y; }
}
class Point3D extends Point {
	int z;
	
	Point3D(int x, int y, int z){
		//super(); is automatically added by compiler, but Point doesn't have default constructor. ERORR OCCUR!
		this.x=x;
		this.y=y;
		this.z=z;
	}
	String getLocation() { return "x: "+x+", y: "+y+", z: "+z; }
}*/


class Car{//[p.359]
	String color;
	int door;
	
	void drive() { System.out.println("drive, Brrrr~"); }
	void stop() {System.out.println("Stop!!!"); }
}
class FireEngine extends Car{
	void water() { System.out.println("Water!!!"); }
}


class Parent{//[p.364]
	int x=100;
	
	void method() { System.out.println("Parent Method"); }
}
class Child extends Parent{
	int x=200;
	
	void method() { System.out.println("Child Method"); }
}


interface Parseable{//[p.388]
	//do parse work
	public abstract void parse(String fileName);
}
class ParserManager{
	public static Parseable getParser(String type) {
		if(type.equals("XML"))
			return new XMLParser();//return consisted object
		else {
			Parseable p=new HTMLParser();
			return p;//return consisted object
		}
	}
}
class XMLParser implements Parseable{
	public void parse(String fileName) {
		//parse work
		System.out.println(fileName+"- XML parsing completed.");
	}
}
class HTMLParser implements Parseable{
	public void parse(String fileName) {
		//parse work
		System.out.println(fileName+"- HTML parsing completed.");
	}
}


class MyException extends Exception{//[p.439]_User defined Exception. throw Error==make new Exception object.
	private final int ERR_CODE;
	
	MyException(String msg, int errCode){
		super(msg);//it can be got by getMessage(). (Exception e; e.getMessage(); )
		ERR_CODE=errCode;
	}
	MyException(String msg){
		this(msg, 100);
	}
	
	public int getErrCode() { return ERR_CODE; }
}