import java.io.ClassNotFoundException;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Main{
	public static void main(String[] args) throws IOExcpetion, ClassNotFoundExcpetion{
		MySerial ms1=new MySerial("string", 10);
		MySerial ms2=new MySerial("text test", 7);
		
		FileOutputStream fos=new FileOutputStream("meSerial.out");
		ObjectOutputStream oos=new ObjectOutputStream(fos);
		
		oos.writeObject(ms1);//Serialize.
		oos.writeObject(ms2);
		
		FileInputStream fis=new FileInputStream("mySerial.out");
		ObjectInputStream ois=new ObjectInputStream(fis);
		
		MySerial mc3=(MySerial)ois.readObject();//BackSerialize
		MySerial mc4=(MySerial)ois.readObject();
		
		mc3.show();
		mc4.show();
	}
}

class MySerial implements Serializable{
	private static final long serialVersionUID=-20012326502821447L;
	int var;
	String str;
	
	public MySerial(){}
	public MySerial(String str, int var){ this.str=str; this.var=var;}
	
	public void show(){ System.out.println("var: "+var+", str: "+str); }
}

/*
1.	자바 직렬화는 인스턴스를 다루는 기법으로 객체와 바이트 스트림 간의 변환을 직렬화라고 한다.
	상태를 유지하던 인스턴스가 런타임 도중에 프로그램이 종료되면 메모리에서 해제되기에 보존이 안되기에 계속 보관하려면 파일로 저장해야 한다.
	이왕 파일입출력을 이용할 거 많은 스트림들을 이용할 수 있기 때문에 자바의 직렬화는 객체의 저장, 입출력에 많은 이점을 갖는다.
2.	클래스에 Serializable을 구현하여 객체를 ObjectStream으로 전환해보자. 
	ObjectIOStream에 FileIOStream을 인자로 넣고, writeObject메소드와 readObject메소드로 작업이 가능하다.
3.	역직렬화하는 쪽에서도 당연히 같은 클래스가 있어야 한다. 다만 메소드의 경우 클래스에서 반복사용되는 코드이기에 상태를 저장할 필요는 없다.
*/	