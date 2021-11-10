//[1. 예외(Exception)]
//1. example.1 of exception(unknown file name)
Buffered Reader br=new BufferedReader(new FileReader("에베베베에"));
//Exception in thread "main" java.io.FileNotFoundException: 에베베베에
br.readLine();
br.close();

//2. example.2 of excpetion
int c=4/0;
//Exception in thread "main" java.lang.ArithmeticException: / by zero

//3. example.3 of exception
int[] a={1,2,3};
System.out.println(println(a[3]));
//Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: 3



//[2. Exception Handling]

//1. solution of example.2
int c;
try{
	c=4/0;
} catch(ArithmeticException e){
	c=-1;
}



//[3. finally for important part]

//1. necessity of finally
public class Test{
	public void shouldBeRun(){ System.out.println("ok thanks.")}
	
	public static void main(String[] args){
		Test test=new Test();
		int c;
		try{
			c=4/0;//EXCEPTION OCCUR!
			test.shouldBeRun();//NEVER EXECUTION!
		} catch(ArithmeticException e){
			c=-1;
		}
	}
}

//2. solution
public class Test{
	public void shouldBeRun(){ System.out.println("ok thanks."); }
	
	public static void main(String[] args){
		Test test=new Test();
		int c;
		try{
			c=4/0;
		} catch(ArithmeticException e){
			c=-1;
		} finally{//Not bad idea..C++ uses Stack unwinding that is automatically destructing defined object in stack. without exception in constructor!
			test.shouldBeRun();
		}
	}
}


//[4. Make exception by Throw, Throws]
//1. Exception & RuntimeException
public class FoolException extends Exception{}//RuntimeException{}//make FoolException class!
//Exception is distinguished by Exception(predicatable) & RuntimeException(Unpredictable).
//Exception is also called Checked Exception, RuntimeException is also called Unchecked Exception.

//2. throw catch in sayNick method.
public class Test{
	public void sayNick(String nick){
		try{
			if("fool".equals(nick)){
				//return;//we can make exception directly!
				throw new FoolException();//Exception in thread "main" FoolException
			}
			System.out.println("Your nickname is "+nick);
		} catch(FoolException e){//well compiled!
			System.err.println("FoolException is occured.");
		}
	}
	
	public static void main(String[] args){
		Test test=new Test();
		test.sayNick("fool");
		test.sayNick("genious");
	}
}

//3. throws catch in main method that calls sayNick class.
public class FoolException extends Exception{}

public class Test{
	public void sayNick(String nick) throws FoolException{
		if("fool".equals(nick))
			throw new FoolException();
		System.out.println("Your nickname is "+nick);
	}
	
	public static void main(String[] args){
		Test test=new Test();
		try{
			test.sayNick("fool");
			test.sayNick("genious");
		} catch(FoolException e){
			System.err.println("FoolException is occured!");
		}
	}
}

//exception in called method by throw & exception in main method by throws are different.
//first exception is occured in test.sayNick("fool"), so next test.sayNick("genious") is well executed.
//But Second exception is occured in try part of main, so next test.sayNick("genious") is not executed.

//[4. Transaction_unit of work]
//Suppose product_Send transaction. if any process makes exception, all product_send transaction have to stop.
//In this situation, The good idea is throw-catch is woked in produce_send().
//If we use throw-catch in each process, mess situation will be occured like packing is done but no sending, no packing but sending,,,
//So management of transaction is important.


//1. example of transaction by Pseudo code.
//1. Good
product_send(){
	try{
	packing();
	print_receipt();
	send();
	} catch(Exception e){
		all_cancel();
	}
}

packing() throws exception{
	
}

print_receipt() throws exception{
	
}

send() throws exception{
	
}

//2. Bad
product_send(){
	packing();
	print_receipt();
	send();
}

packing(){
	try{
		
	}catch(exception){
		cancel_packing();
	}
}

print_receipt(){
	try{
		
	}catch(exception){
		cancel_receipt();
	}
}

send(){
	try{
		
	}catch(exception){
		calcel_send();
	}
}