import java.util.ArrayList;

public class Main{
	public static void main(String[] args){
		ArrayList<BaseA> bsLst=new ArrayList<BaseA>();
		
		BaseA bc1=new BaseA();
		BaseA bc2=new SubA();
		BaseA bc3=new SubB();
		BaseA bc4=new SubC();
		
		bsLst.add(bc1);
		bsLst.add(bc2);
		bsLst.add(bc3);
		bsLst.add(bc4);
		
		System.out.println("Let's go to polymorphism by ArrayList");
		for(BaseA bs: bsLst)
			bs.showInfo();
		
		//safe down-casting by using instanceof keyword
		if(bc2 instanceof SubA){
			SubA sb1=(SubA) bc2;
			System.out.println("down-casting success!");
		}
	}
}

class BaseA{
	public void showInfo(){ System.out.println("Base A"); }
}
class SubA extends BaseA{
	public void showInfo(){ System.out.println("Sub A"); }
}
class SubB extends BaseA{
	public void showInfo(){ System.out.println("Sub B"); }
}
class SubC extends BaseA{
	@Override//해도 상관없궁..
	public void showInfo(){ System.out.println("Sub C"); }
}

/*
1.	클래스도 하나의 자료형이기 때문에 인스턴스를 만들기 전에 클래스가 먼저 로드외어 여러 인스턴스가 하나의 같은 명령어 메소드를 공유한다.
	메소드는 코드이므로 segment .text에 저장이 되어 여러 인스턴스는 하나의 메소드 in segment .text를 가리킨다.
	인스턴스와 메소드를 연결시키기 위해서 클래스의 메타 데이터에는 가상 메서드 테이블이 존재한다.(메소드도 하나의 주소가 존재)
2.	자바의 모든 function은 virtual이다..
3.	HAS-A는 소속의 개념 멤버변수의 관계이고, IS-A는 확장하는 관계이다.
4.	instanceof키워드를 사용하여 Base 타입에 담긴 sub인스턴스가 어느 sub class인것인지를 확인할 수 있다.
	이를 통해 보다 실제 인스턴스의 클래스를 확인함으로써 Base변수에 담아두었던 객체를 보다 안전하게 down-casting할 수 있다.
5.	
*/