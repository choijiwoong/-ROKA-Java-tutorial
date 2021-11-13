public class Main{
	public static void main(String[] args){
		System.out.println("[definition & creation of inteerface]");
		System.out.println("[--------------------------------]");
		
		Draw d1=new myDrawing();//myDrawing to Draw interface
		d1.initialize();
		d1.defaultInitialize();
		d1.drawPoint(50, 50);
		d1.drawRectangle(100, 100, 70, 70);
		d1.endDraw();
		
		myDrawing dc=(myDrawing)d1;//casting for use myDrawing's own method
		dc.showInfo();
		System.out.println("[-------------------------------]");
		myDrawing d2=new myDrawing();
		d2.showInfo();
	}
}

interface Draw{
	//public static finals
	String defaultColor="BLACK";
	int defaultSize=2;
	
	public void initialize();
	public void drawPoint(int posX, int posY);
	public void drawRectangle(int posX, int posY, int width, int height);
	public void endDraw();
	
	default void defaultInitialize(){//default can make body(for 어쩔수없는상황)
		System.out.println("...this can have a body- default");
		currentStatus();
		staticMethod();
	}
	
	public static int rectangleSize(int width, int height){ return width*height; }//static possible
	
	//private method can be used in default method & static method can be used also in static method
	private void currentStatus(){ System.out.println("... Draw interface implemented");
	private static void staticMethod(){ System.out.println("... this is private static method of interface"); }
}

class myDrawing implements Draw{
	int posX=0, posY=0;
	int width=0, height=0;
	
	@Override
	public void initialize(){ System.out.println("...Initializing GDI Drawing"); }
	
	@Override
	public void drawPoint(int posX, int posY){
		this.posX=posX;
		this.posY=posY;
		System.out.println("- Draw line at "+this.posX+", "+this.posY);
	}
	
	@Override
	public void drawRectangle(int posX, int posY, int width, int height){
		this.posX=posX;
		this.posY=posY;
		this.width=width;
		this.height=height;
		System.out.println("--Draw Rectangle at "+this.posX+", "+this.posY);
		System.out.println("--width: "+this.width+" height: "+this.height);
		System.out.println("--Rectangle size: "+Draw.rectangleSize(this.width, this.height));
	}
	
	@Override
	public void endDraw(){ System.out.println("...Return to the system");
	
	public void showInfo(){ System.out.println("this class implements Draw interface"); }
}

/*
1.	인터페이스에서 정의한 변수는 컴파일러가 public static final키워드를 붙인다. 즉 변수를 정의할 수 없다.
	Abstract의 body구현은 선택이었지만, interface는 default를 제외한 head선언만 허용된다.
2.	인터페이스와 추상 클래스의 가장 큰 차이점은 클래스가 base하나인 것과 달리, 인터페이스는 다중상속이 가능하다는 것이다.(p.s 인터페이스는 인스턴스없음..)
	또다른 차이는 Abstract은 sub클래스에서 base메소드 접근이 가능했지만, 인터페이스는 타입캐스팅을 해야한다.
3.	인터페이스 내부에서만 돌아가는 private메소드를 만들 수 있다. private메소드는 default메소드에서 호출이 가능하다.
	인터페이스 내에서 private static메소드가 가능하며, 이는 static메소드에서 사용이 가능하다.
*/

//SIMPLE EXAMPLE OF MULTI INTERFACE
public class Main{
	public static void main(String[] args){
		MultiInterface m1=new MultiInterface();
		
		A1 a1=m1;//virtual
		a1.styleA();
		a1.styleSame();
		
		System.out.println("-------------");
		B1 b1=m1;//virtual
		b1.styleB();
		b1.styleSame();
		
		System.out.println("--------------");
		m1.styleA();//ok
		m1.styleB();
		m1.styleSame();
		
		System.out.println("---------------");
		MultiInterface m2=(MultiInterface)b1;//b1 also got MultiInterface virtually
		m2.styleA();
		m2.styleB();
		m2.styleSame();
	}
}

interface A1{
	public void styleA();
	default public void styleSame(){ System.out.println("A1 implementation"); }
}
interface B1{
	public void styleB();
	default public void styleSame(){ System.out.println("B1 implementation"); }
}
class MultiInterface implements A1, B1{
	@Override
	public void styleA(){ System.out.println("multi interface implements A1 stype"); }
	@Override
	public void styleB(){ System.out.println("multi interface implements B1 stype"); }
	@Override
	public void styleSame(){ System.out.println("this should be overrided"); }
}

//SIMPLE EXAMPLE OF MULTI INTERFACE INHERITANCE
public class Main{
	public static void main(String[] args){
		Brotherhood bro1=new Brotherhood();
		bro1.playGame();
		bro1.playSports();
		bro1.playChess();
		
		System.out.println("------------------");
		OlderBrother bro2=new Brotherhood();
		bro2.playGame();
		
		System.out.println("------------------");
		LittleBrother bro3=new Brotherhood();
		bro3.playSports();
		
		System.out.println("------------------");
		Brotherhood bro4=new Brotherhood();
		bro4.playGame();
		bro4.playSports();
		bro4.playChess();
	}
}

interface OlderBrother{
	void playGame();
}
interface LittleBrother{
	void playSports();
}
interface MyBrother extends OlderBrother, LittleBrother{
	void playChess();
}

class Brotherhood implements MyBrother{//we can use both like class Brotherhood extends A1 implements MyBrother
	@Override
	public void playGame(){ System.out.println("play game"); }
	@Override
	public void playSports(){ System.out.println("play sports"); }
	@Override
	public void playChess(){ System.out.println("play chess"); }
}

//전하고자 하는 바를 잘 모르겠고, interface를 implements하여 바로 구현하는 것이 아니라 인터페이스의 확장으로서 상속이 가능하다 정도.
//p.s JDBC