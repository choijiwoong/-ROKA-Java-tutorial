public class Main{
	public static void main(String[] args){
		MyCar a1=new MyCar();
		a1.showInfo();
		System.out.println(a1.carName);
		System.out.println(a1.carColor);
		System.our.println(a1.speed);
		//System.out.println(a1.speed);//ERROR!
		
		a1.setPassword("1q2w3e4r@");//we can approach private member variable of MyCar class by public method in MyCar class.
		System.out.println(a1.getPassword());//for preventing user mistake
	}
}
class MyCar{
	String carName;
	String carColor;
	private String password;//private can access in that class only. (Informatioin Hiding) like "local variable than global variable"
	int speed;
	
	MyCar(){//constructor
		carName="Sedan";
		carColor="Grey";
		password="1q2w3e4r!";
		speed=100;
	}
	void showInfo(){
		System.out.println("Car name: "+carName+", color: "+carColor+", speed: "+speed);
	}
	
	//Getter & Setter of password(notepad++ TT not eclipes)
	public String getPassword(){ return password; }
	public void setPassword(String password){ this.password=password; }
}

/*
	[Access Modifier]
1.	public:		제한 없음
	private:	클래스 내부에서만
	protected:	같은 패키지 내부와 상속 관계 클래스
	default:	같은 패키지 내부
*/