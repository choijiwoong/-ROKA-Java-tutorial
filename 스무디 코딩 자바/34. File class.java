/*[1. Simple use of File Class]
import java.io.File;
import java.io.IOException;

public class Main{
	public static void main(String[] args) throws IOException{
		FileEx fe=new FileEx("aFile.txt");
		File myFile=fe.getMyFile();
		
		System.out.println("is File?: "+myFile.isFile());
		System.out.println("is Directory?: "+myFile.isDirectory());
		System.out.println("Name?: "+myFile.getName());
		System.out.println("AbsolutePath: "+myFile.getAbsolutePath());
		System.out.println("Path: "+myFile.getPath());
		
		myFile.deleteOnExit();//Delete
	}
}

class FileEx{
	File myFile;
	
	FileEx(String fileName) throws IOException{
		myFile=new File(fileName);
		myFile.createNewFile();//Make
	}
	
	public File getMyFile(){ return myFile; }
}*/

//[2. RandomAccessFile]
import java.io.IOException;
import java.io.RandomAccessFile;

public class Main{
	public static void main(String[] args) throws IOException{
		RandomAccessFile randomF=new RandomAccessFile("a.txt", "rw");
		
		System.out.println("Type 4bytes Integer");
		randomF.WriteInt(777);//Write real number not text by WriteInt
		
		System.out.println("File Pointer: "+randomF.getFilePointer());//4
		System.out.println("initialization by seek method");
		randomF.seek(0);
		System.out.println("File pointer: "+randomF.getFilePointer());//0
		
		int myInt=randomF.readInt();//readInt read bytes.
		System.out.println("Get saved number: "+myInt);
		System.out.println("File Pointer: "+randomF.getFilePointer());//4
		
		System.out.println("Use UTF");//Unicode Transformation Format
		randomF.writeUTF("UTF Hello World!");
		
		System.out.println("File Pointer: "+randomF.getFilePointer());//22
		System.out.println("print by moving File Pointer");
		randomF.seek(4);
		String myUTF=random.readUTF();
		System.out.println(myUTF);
	}
}

/*
1.	File클래스는 IOException과 함께 사용한다. 파일 자체가 입출력의 추상화이다 파일은 데이터를 입력받기도, 데이터를 출력시키기도 하기 떄문에 가상화(Virtualization)과 추상화(Abstraction)의 개념이 필요하다.
2.	파일의 입출력은 속도효율성을 위하여 뭉탱이(스트림)으로 작동한다.
3.	RandomAccessFile은 파일포인터를 바이트단위로 이동시켜서 파일에 접근이 가능하기에 실제 값은 메모장에서 열어보면 깨진 글자처럼 보인다.
*/