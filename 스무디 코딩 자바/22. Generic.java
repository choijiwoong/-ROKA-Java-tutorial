public class Main{
	public static void main(String[] args){
		GenericClass<A1> ngr1=new GenericClass<A1>();
		
		ngr1.setData(new A1());
		ngr1.getData().showInfo();
		
		
		GenericClass<B1> ngr2=new GenericClass<B1>();
		
		ngr2.setData(new B1());
		ngr2.getData().showInfo();
	}
}


class GenericClas<DT>{
	private DT obj1;
	public DT getData(){ return obj1; }
	public void setData(DT obj1){ this.obj1=obj1; }
}
class A1{
	public void showInfo(){ System.out.println("A1 sout"); }
}
class B1{
	public void showInfo(){ System.out.println("B1 sout"); }
}

/*
1.	<Dt>와 같이 정의되지 않은 객체를 Generic안에 두었다면, object로 변형되어 연산할 것이다.
2.	객체가 들어갔다고 해서 큰 규칙이 바뀌는게 아니라 일반적으로 다 사용이 가능한데, getData부분에서 해당 객체의 메소드등을 사용할 수 있다.

1.	컬렉션 프레임워크는 Collection인터페이스에서 시작하는 계층이다.
2.	컬렉션스 자료구조에는 List 인터페이슬ㄹ 구현한 ArrayList, LinkedList, Vector등이 있고, Set을 구현한 HashSet, TreeSet이 있다.
*/