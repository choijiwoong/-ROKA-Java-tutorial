/*[1. Simple use of Systm.in by using .read()]
public class Main{
	public static void main(String[] args){
		int i=0;
		
		try{
			while((i=System.in.read())!=-1)
				System.out.print((char)i+"("+i+")\n");
		} catch(Exception ec){ return ; }
		//결과가 좀 이상한데ㅋㅋㅋ왜 ()이 아 \n인가 아니면 엔터인가 그러네 (10)이LF로 Line Feed->다음 줄로네
	}
}*/

/*[2. Scanner]
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main{
	public static void main(String[] args) throws FileNotFoundException{
		System.out.println("----Get String Source----");
		Scanner sc=new Scanner("Hello Scanner!");
		String myStr1=sc.nextLine();
		System.out.println("1. String: "+myStr1);
		sc.reset();
		sc.close();
		
		System.out.println("----Get File Source----");
		File myFile=new File("test.txt");
		if(myFile.exists()){
			Scanner sc1=new Scanner(myFile);//we have to make Scanner object after checking file.exists()
			while(sc1.hasNext())
				System.out.println(sc1.nextLine());
			sc1.reset();
			sc1.close();
		}
		
		System.out.println("----Get Standard Input Source----");
		System.out.println("input name: ");
		Scanner sc2=new Scanner(System.in);
		String text=sc2.nextLine();
		System.out.println("Your name: "+text);
	}
}*/

/*[3. FileInputStream]
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main{
	public static void main(String[] args){
		File newFile=new File("test.txt");
		if(!newFile.exists()){//check isExist, if not make new file
			try{//we have to handle exception in FileIO
				newFile.createNewFile();
				System.out.println("---new file created---");
			} catch(IOException e){ e.printStackTrace(); }
		}
		
		FileInputStream myFis=null;
		try{
			myFis=new FileInputStream("test.txt");
			int o;
			while((o=myFis.read())!=-1)//EOF의 값은 프로그램마다 다르지만 보통 -1로 정의한다
				System.out.print((char)o);
		}catch(FileNotFoundException o){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		} finally{
			try{ myFis.close(); }
			catch (IOException){ e.printStackTrace(); }
		}
	}
}*/

/*[4. FileInput by Array with FileInputStream]
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main{
	public static void main(String[] args){
		try{
			FileInputStream fis=new FileInputStream("test.txt");
			byte[] readByte=new byte[30];
			int b;
			while((b=fis.read(readByte))!=-1){//save content to readByte and return Bytecount to b
				for(byte a: readByte)
					System.out.print((char)a);
				System.out.println(" | "+b+" Byte read");
			}
		}catch(FileNotFoundException e){ e.printStackTrace(); 
		}catch(IOException e){ e.printStackTrace(); }
		
	}
}*/

/*[5. FileOutputStream]
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main{
	public static void main(String[] args){
		try{
			FileOutputStream fos=new FileOutputStream("test.txt");
			for(int i=65; i<91; i++)
				fos.write(i);
		}catch(FileNotFoundException e){ e.printStackTrace();
		}catch(IOException e){ e.printStackTrace(); }
	}
}*/

/*[6. FileOutput by Array with FileOutputStream]
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main{
	public static void main(String[] args){
		try{
			FileOutputStream fos=new FileOutputStream("test.txt");
			
			byte[] b1=new byte[26];//just make content of byte[] for print to FileOutputStream
			for(int i=0; i<b1.length; i++)
				b1[i]=(byte)(i+65);
			fos.write(b1);//write by byte array
			System.out.println("---Writing Completed---");
		}catch(FileNotFoundException e){ e.printStackTrace();
		}catch(IOException e){ e.printStackTrace(); }
	}
}*/

//[7. 보조스트림을 이용한 한글 출력]
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStreamReader;

public class Main{
	public static void main(String[] args) throws IOException{
		InputStreamReader isr=new InputStreamReader(new FileINputStream("test.txt"));
		
		int i;
		while(i=isr.read())!=-1)
			System.out.print((char)i);//read by char
	}
}

/*
1.	System.in은 표준 입력 객체, Systen.out은 표준 출력 객체이다. 이들은 우리가 인스턴스생성없이 사용해왔던 것 처럼 static객체이다.
2.	Scanner를 사용하여 일반적인 string source부터 file, system.in등 여러 소스의 input을 담을 수 있다.
3.	FileInputStream을 사용하면 FileReader의 readLine()과 다르게 읽을때도 일반적인 스트림과 같이 .read()메소드를 사용할 수 있다.
4.	FileInputStream에 read()인자로 Array에 내용 저장이 가능하다.
5.	FileOutputStream도 FileWriter와 사용이 비슷한 것 같은데, 뭔가 지금 느껴지는 차이점은 FileWrite로는 완성된 String같은 것을 주었다면 조금 더 작은 단위로 보내는 느낌이 든다.(사실 차이를 잘 모르겠다)
6.	FileOutputStream에 write()인자로 Array을 넣어 출력이 가능하다.
7.	FileInputStream은 바이트 단위로 읽지만, 보조 스트림인 InputStreamReader을 사용하면 문자 단위로 읽을 수 있다. 인자로 FileInputStream을 생성하여 전달하면 된다.
	Buffered 스트림 클래스도 파일작업시 효율을 높여주는 대표적인 보조스트림인데, 기본스트림이 있고 거기에서 넘어오는 자료를 한번 더 처리하는 클래스들이 보조스트림이다.
*/