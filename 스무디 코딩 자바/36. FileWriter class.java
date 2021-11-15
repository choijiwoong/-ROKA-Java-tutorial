/*[1. File Write]
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException

public class Main{
	public static void main(String[] args) throws IOException{
		System.out.println("Write by FileWriter only");
		FileWriter fw=new FileWriter("file_writer.txt");
		fw.write("Hello this is file writer");
		fw.close();
		
		System.out.println("Write&Pass by buffer of BufferedWriter");
		BufferedWriter bw=new BufferedWriter(new FileWriter("test.txt"));
		bw.write("Hello\nusing Buffered Writer");
		bw.write("777");
		bw.close();//witout this, not crate File!
	}
}*/

/*[2. File Read]
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException

public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new FileReader("test.txt"));
		String str;
		while((Str=br.readLine())!=null)//Good Expression
			System.out.println(str);
		br.close();
	}
}*/

//[3. File Copy]
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class Main{
	public static void main(String[] args) throws IOException{
		BufferedWriter bw=new BufferedWriter(new FileWriter("copied_b.txt"));
		BufferedReader br=new BufferedReader(new FileReader("test.txt"));
		String str;
		
		while((str=br.readLine())!=null){
			System.out.println(str);
			bw.write(str+"\n");
		}
		br.close();
		bw.close();
	}
}

/*
1.	파일 출력은 FileWriter그대로 쓰는 방법과 BufferedWriter로 출력내용을 버퍼에 받아서 쓰는 방법이 있다. BufferedWriter을 사용하면 close를 해야만 파일 작성이 완료된다.
	또한 BufferedWriter는 Writer로써 받는 매개변수 객체 종류에 따라 출력을 어디에 할 것인지를 결정할 수 있는데, 콘솔에도 출력할 수 있다.
2.	FileReader(BufferedReader)을 사용하여 File내용을 가져올 수 있다.
3.	FileCopy는 별도의 모듈은 없고, 위의 출력과 입력을 같이 진행하면 된다.
*/