import java.io.File;//p.513
import java.math.BigInteger;//p.520
import java.util.Scanner;//p.513
import java.util.StringJoiner;//p.472
import java.util.StringTokenizer;//p.516
import java.util.regex.Pattern;//p.506
import java.util.regex.Matcher;//p.506

public class tutorial {
	public static void main(String[] args) throws Exception{//throws p.512
		System.out.println("\n[p.446]");
		//p.446_how can we use chained exception, how to make user customized exception, how to change checked exception to uncheck exception by chained exception
		try {
			install();
		}catch(InstallException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		System.out.println("\n[p.452]");
		//p.452_How can we overload method of Object class by casting. example of using instanceof method of Object class.
		Person p1=new Person(8011081111222L);
		Person p2=new Person(8011081111222L);
		
		if(p1==p2)//check reference's address
			System.out.println("p1, p2 same");
		else
			System.out.println("p1, p2 different");
		
		if(p1.equals(p2))//check value by overloading with casting to Object
			System.out.println("p1, p2 same");
		else
			System.out.println("p1, p2 differend");
		
		
		System.out.println("\n[p.460]");
		//p.460_Shallow&Deep copy to Cleanable class by using object.clone() with Covariance
		Circle c1=new Circle(new Point(1,1), 2.0);
		Circle c2=c1.shallowCopy();
		Circle c3=c1.deepCopy();
		
		System.out.println("c1: "+c1);
		System.out.println("c2: "+c2);
		System.out.println("c3: "+c3);
		
		c1.p.x=9;
		
		
		c1.p.y=9;
		System.out.println("After change c1");
		System.out.println("c1: "+c1);
		System.out.println("c2: "+c2);
		System.out.println("c3: "+c3);
		
		
		System.out.println("\n[p.472]");
		//p.472_StringJoiner example
		String animals="dog,cat,bear";
		String[] arr=animals.split(",");
		
		StringJoiner sj=new StringJoiner("/", "[","]");
		for(String s: arr)
			sj.add(s);
		System.out.println(sj.toString()); 
		
		
		System.out.println("\n[p.504]");
		//p.504_Make getRand function for extract element in array randomly
		for(int i=0; i<RECORD_NUM; i++) {
			System.out.println(" INSERT INTO "+TABLE_NAME
							+" VALUES ("
							+" '"+getRandArr(CODE1)+"'"
							+", '"+getRandArr(CODE2)+"'"
							+", '"+getRandArr(CODE3)+"'"
							+",  "+getRand(100,200)
							+"); ");
		}
		
		
		System.out.println("\n[p.506]");
		//p.506_Regex example
		String[] data= {"bat", "baby", "bouns", "c", "cA", "ca", "co", "c.", "c0", "c#", "car", "combat", "count", "date", "disc"};
		String[] pattern= {".*", "c[a-z]*", "c[a-z]", "c[a-zA-z]", "c[a-zA-Z0-9]", "c.", "c.*", "c\\.", "c\\w", "c\\d", "c.*t", "[b|c].*", ".*a.*", ".*a.+", "[b|c].{2}"};
		for(int x=0; x<pattern.length; x++) {
			Pattern p=Pattern.compile(pattern[x]);//Make Pattern object by compiling pattern array.
			System.out.print("Pattern: "+pattern[x]+" // result: ");
			for(int i=0; i<data.length; i++) {
				Matcher m=p.matcher(data[i]);//Make Matcher object by Pattern object's matcher method with target data
				if(m.matches())//check is it matches.
					System.out.print(data[i]+",");
			}
			System.out.println();
		}
		
		
		System.out.println("\n[p.513]");
		//p.513_Scanner effectively not only System.in
		Scanner sc=new Scanner(new File("data.txt"));
		int cnt=0, totalSum=0;
		
		while(sc.hasNextLine()) {
			String line=sc.nextLine();
			Scanner sc2=new Scanner(line).useDelimiter(",");//We can use Scanner like split interprinter mode with Delimiter.
			
			int sum=0;
			while(sc2.hasNextInt()) {
				sum+=sc2.nextInt();
			}
			System.out.println(line+", sum: "+sum);
			totalSum+=sum;
			cnt++;
		}
		System.out.println("Line: "+cnt+", Total: "+totalSum);
		
		
		System.out.println("\n[p.516]");
		//p.516_Effective example of StringTokenizer
		String input="삼십만삼천백십오";
		System.out.println(input);
		System.out.println(hangulToNum(input));
		
		
		System.out.println("\n[p.520]");
		//p.520_Simple example of BigInt
		for(int i=1; i<100; i++) {
			System.out.printf("%d!=%s%n", i, calcFactorial(i));
			Thread.sleep(300);
		}
		
	}//END
	
	//p.446
	static void install() throws InstallException{
		try {
			startInstall();
			copyFiles();
		}catch(SpaceException se) {//catched exception is in se. but we have to allow Exception occured in install too.  
			InstallException ie=new InstallException("Exception occur in install");//So make new Exception of Install.
			ie.initCause(se);//And set InstallException's Cause Exception to catched SpaceException.
			throw ie;//rethrow(chained) Exception object that set InstallException to cause Exception.
		}catch(MemoryException me) {
			InstallException ie=new InstallException("Exception occur in install");
			ie.initCause(me);
			throw ie;
		}finally {
			deleteTempFiles();
		}
	}
	static void startInstall() throws SpaceException, MemoryException{
		if(!enoughSpace()) {
			throw new SpaceException("Not enough Space for install");
		}
		
		if(!enoughMemory()) {
			throw new MemoryException("Not enough Memory");
			//throw new RuntimeException(new MemoryException("Not enough Memory"));//Make checked Exception to unchecked Exception by using chained Exceptions
		}
	}

	static void copyFiles() {}//code etc..
	static void deleteTempFiles() {}
	static boolean enoughSpace() {return false;}
	static boolean enoughMemory() {return true;}
	
	
	//p.504_Good for make test cases
	final static int RECORD_NUM=10;
	final static String TABLE_NAME="TEST_TABLE";
	final static String[] CODE1= {"010", "011", "017", "018", "019"};
	final static String[] CODE2= {"Man", "Woman"};
	final static String[] CODE3= {"Age10", "Age20", "Age30", "Age40", "Age50"};
	public static String getRandArr(String[] arr) { return arr[getRand(arr.length-1)]; }
	public static int getRand(int n) { return getRand(0, n); }
	public static int getRand(int from, int to) { return (int)(Math.random()*(Math.abs(to-from)+1))+Math.min(from, to); }
	
	
	//p.516_hangulToNum
	public static long hangulToNum(String input) {
		long result=0;//final
		long tmpResult=0;//for save 십백천
		long num=0;
		
		final String NUMBER="영일이삼사오육칠팔구";
		final String UNIT="십백천만억조";
		final long[] UNIT_NUM= {10, 100, 100, 10000, (long)1e8, (long)1e12};
		
		StringTokenizer st=new StringTokenizer(input, UNIT, true);//Tokenize input by UNIT
		while(st.hasMoreTokens()) {
			String token=st.nextToken();
			int check=NUMBER.indexOf(token);//find index of toke in NUMBERn, if not found, return -1
			
			if(check==-1) {//unit***???
				if("만억조".indexOf(token)==-1) {//"십백천"
					tmpResult+=(num!=0?num:1)*UNIT_NUM[UNIT.indexOf(token)];//add number before unit, and multiple Unit to Number amount by using index
				}else {//"만억조"
					tmpResult+=num;
					result+=(tmpResult!=0?tmpResult:1)*UNIT_NUM[UNIT.indexOf(token)];
					tmpResult=0;
				}
				num=0;
			}else {//number
				num=check;
			}
		}
		
		return result+tmpResult+num;
	}
	
	
	//p.520_calcFactorial by BigInt
	static String calcFactorial(int n) { return factorial(BigInteger.valueOf(n)).toString(); }
	static BigInteger factorial(BigInteger n) {
		if(n.equals(BigInteger.ZERO))//if 0
			return BigInteger.ONE;//return 1
		else
			return n.multiply(factorial(n.subtract(BigInteger.ONE)));//BigInteger have to be calculated by method only.
	}
	
}//END

//[p.446]
class InstallException extends Exception{//User customized Exception by extending Exception
	InstallException(String msg){ super(msg); }
}
class SpaceException extends Exception{
	SpaceException(String msg){ super(msg); }
}
class MemoryException extends Exception{
	MemoryException(String msg){ super(msg); }
}


//[p.452]
class Person{
	long id;
	
	public boolean equals(Object obj) {//Get as Object object for using Object's methods.
		if(obj instanceof Person)//check it's real class.
			return id==((Person)obj).id;//access id after checking
		else//if it's different class with Person, return false.
			return false;
	}
	
	Person(long id){ this.id=id; }
}


//[p.460]
class Circle implements Cloneable{
	Point p;
	double r;
	
	Circle(Point p, double r){ this.p=p; this.r=r; }
	
	public Circle shallowCopy() {
		Object obj=null;
		
		try {
			obj=super.clone();//clone copy object's member. But if reference variable is exist, copy it's address because it judge address as value.
		}catch(CloneNotSupportedException e) {}
		
		return (Circle)obj;//Covariance(get Based object by Derived class as return type) used
	}
	public Circle deepCopy() {
		Object obj=null;
		
		try {
			obj=super.clone();
		}catch(CloneNotSupportedException e) {}
		
		Circle c=(Circle)obj;
		c.p=new Point(this.p.x, this.p.y);//so we have to allocate reference variable for make other object for deepCopy
		
		return c;
	}
	
	public String toString() { return "[p: "+p+", r: "+r+"]"; }
}
class Point{
	int x,y;
	
	Point(int x, int y){ this.x=x; this.y=y; }
	
	public String toString() { return "("+x+", "+y+")"; }
}