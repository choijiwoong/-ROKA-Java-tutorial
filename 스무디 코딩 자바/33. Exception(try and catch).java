/*[1. Simple example of exception]
public class Main{
	public static void main(String[] args){
		int[] myArray={1,3,5,7,9};
		try{
			for(int i=0; i<6; i++)
				System.out.print(myArray[i]+", ");
		}catch (ArrayIndexOutofBoundsException e){
			System.out.println();
			System.out.println(e);
			System.out.println("try & catch");//well work
		}
		System.out.println("Control program");//well work
	}
}*/

/*[2. finally]
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main{
	public static void main(String[] args){
		FileInputStream fIStream=null;
		System.out.println("---Test File---");
		
		try{
			System.out.println("open file");
			fIStream=new FileInputStream("test.txt");
		}catch (FileNotFoundException e){
			e.printStackTrace();//print error detail
		}finally{
			if(fIStream!=null){
				try{
					fIStream.close();
				} catch(IOException e){
					e.printStackTrace();
				}
			}
		}
		
		System.out.println("Exception handling of File Input Stream");
	}
}*/

/*[3. AutoCloseable Interface]
public class Main{
	public static void main(String[] args){
		try(AutoClose obj=new AutoClose()){
			throw new Exception();// automatically close method of AutoClose will be called!
		} catch(Exception e){
			e.printStackTrace();
			System.out.println("Exception Expressing");
		}
	}
}
class AutoClose implements AutoCloseable{
	@Override//must for auto destruction
	public void close() throws Exception{ System.out.println("close resouce"); }
}*/

//4. Throws for delay catch of Exception
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main{
	public static void main(String[] args){
		Main m1=new Main();
		System.out.println("Program Start!");
		try{
			m1.loadClass("test.txt", "java.lang.String");
		} catch(FileNotFoundException e){
			e.printStackTrace();
		} catch(ClassNotFoundException e){
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println("End of program");
	}
	
	public class loadClass(String fileName, String className) throws FileNotFoundException, ClassNotFoundException{
		FileInputStream fileInputStream=new FileInputStream(fileName);
		Class c1=Class.forName(className);//get class name of dynamically instantiated object.
		return c1;
	}
}

/*
1.	ArrayIndexOutofBoundsException은 배열인덱스가 크기를 초과했을때 처리해주는 클래스이다.
2.	finally는 try-catch 맨 마지막에 실행되며, 자원을 catch마다 반환하는 것을 방지하여 공통적으로 처리해야 할 자원의 반환을 final코드로 작성할 수 있다.
	정석대로면 finally안에서도 파일을 닫는 경우와 같이 예외처리try_catch를 다시 해야하는 경우에 해야한다.
3.	AutoCloseable interface를 사용하면 finally에서 매번 반환해야 하는 고정적인 자원들을 명시적으로 반호나하지 않아도 알아서 반환하도록 구현한다.
	close() method에 실제로 자원을 반환하는 코드를 구현해주면, 예외 발생시에 자동으로 close메소드를 호출해준다. AutoCloseable 인터페이스를 implements했기에 override로 덮어야 한다.
4.	throw를 이용하여 new Exception();처럼 예외 객체를 생성하여 던지면 강제 예외 생성이 가능하다.
5.	매번 예외가능부분을 try catch로 하나하나 다 잡는 것이 아닌 퉁쳐서 클래스 헤드에 throws로 집어넣으면 컴파일시 오류가 발생하지 않지만, 예외 발생시 퉁쳐서 나오기에 정식 프로그램은 try-catch를 사용하는 것이 좋다.
	Exception처리를 안하면 컴파일이 불가능한 경우에, 자바에서 예외처리방법으로 보다 정교하게 오류처리를 하게 하기 위하여 throws와 throw 두가지 옵션을 준 것이다.
*/