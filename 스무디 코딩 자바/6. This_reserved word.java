public class Main{
	public static void main(String[] args){
		thisTest tT=new thisTest();
		tT.setPrice(3);//use 'this'
		System.out.println(tT.getPrice());
		tT.showHashKey();//show hashkey by 'this'
		System.out.println(tT.getProduct());
	}
}

class thisTest{
	private int price;
	private String product;
	
	public int getPrice(){ return price; }
	public void setPrice(int price){ this.price=price; }//distinguish member variable & argument
	public void showHashKey(){ System.out.println(this); }//we can get hashkey(virtual address_for JVM) of instance by this.
	public void setProduct(String product){ this.product=product; }
	public String getProduct(){ return product; }
	
	thisTest(){
		this(0, "none product");//Special Grammer of This! now, this is used as name of class.
		//this points sometimes instance, sometimes class by context.
	}
	thisTest(int price, String product){
		setPrice(price);//this.price=price;
		this.product=product;//setProduct(product)
	}
}

/*
1.	this를 통하여 생성자 오버로드 시 다른 생성자를 호출할 수 있다.
2.	this는 파이썬의 Self와도 비슷한데, OOP에는 캡슐화를 통해 외부와 내부를 분리하기에 필연적으로 인스턴스를 내부에서 사용하는 로직이 필요하다.
*/