public class Main{
	public static void main(String[] args){
		baseClass bc1=new baseClass(101, "describing baseClass");
		bc1.generateMsg("generating base1");
		
		System.out.println("--------------------------------------");
		subClass sc1=new subClass(201, "describing subClass", "subText");
		sc1.generateMsg("generating subClass 1");
	}
}

class baseClass{
	private int id;
	private String description;
	
	public baseClass(){
		id=100;
		description="base one";
	}
	public baseClass(int id, String description){
		this.id=id;
		this.description=description;
	}
	public void generateMsg(String text){
		System.out.println("text = "+text);
		System.out.println("description = "+description);
	}
}

class subClass extends baseClass{
	private String subText;
	
	public subClass(){super();}
	public subClass(int id, String description, String subText){
		super(id, description);
		this.subText=subText;
	}
	@Override//explicit expression to compiler..!
	public void generateMsg(String text){
		super.generateMsg(text);
		System.out.println("subText = "+subText);
		System.out.println("Override base method");
	}
}

/*
1.	상속받은 것을 사용할때 그냥 base의 변수이름 그대로 사용해도 되지만, super.을 사용하면 가독성이 좋아진다.
*/