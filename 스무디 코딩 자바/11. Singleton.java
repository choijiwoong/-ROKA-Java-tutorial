public class Main{
	public static void main(String[] args){
		mySingleton inst1=mySingleton.getInstance();
		mySingleton inst2=mySingleton.getInstance();
		System.out.println(inst1);
		System.out.println(inst2);
		System.out.println("cmp inst1, inst2");
		System.out.println(inst1==inst2);//they have to be same
		
		System.out.println(inst1.getId());
		System.out.println(inst1.getName());
		
		inst1.setId(2005);
		inst1.setName("The New Manager");
		
		System.out.println(inst2.getId());
		System.out.println(inst2.getName());
	}
}

class mySingleton{
	private int id;
	private String name;
	
	private static mySingleton instance=new mySingleton();//static variable allocation without initialization
	private mySingleton(){
		this.id=1001;
		this.name="The Instance Manager";
	}
	
	public static mySingleton getInstance(){
		if(instance==null)
			instance=new mySingleton();//잘 이해가 가진 않았지만..우선 static변수로 mySingleton이 자동생성되고 getInstance시 만약? static변수가 없으면 기존에 만들어진걸 위의 constructor로 초기화하고? 반환
		return instance;
	}
	
	public int getId(){ return id; }
	public void setId(int id){ this.id=id; }
	public String getName(){ return name; }
	public void setName(String name){ this.name=name; }
}

/*
1.	Singleton모델은 new를 사용할 원천을 차단해버리므로 프로그램 오류나 메모리 유출 등 편하게 프로그래밍이 가능하다.
	GC가 있지만, new자체를 안쓸 수 있다면 사용자가 못쓰게 막아두자 라는 개념이다.
*/