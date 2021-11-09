//[1. neccessity of interface] 약간 인터페이스라는 네이밍이 좀 의미있어 보이넴
interface Predator{
	public String getFood();//no body of method! it will be created in implemented class! it's smillar with pure virtual function in C++
}

class Animal{
	String name;
	public void setName(String name){ this.name=name; }
}

//class Tiger extends Animal{}
//class Lion extends Animal{}
class Tiger extends Animal implements Predator{
	public String getFood(){return "apple";}
}
class Lion extends Animal implements Predator{
	public String getFood(){return "banana";}
}
//new Predator is added!
class Crocodile extends Animal implements Predator{
	public String getFood(){return "human";}
}

class ZooKeeper{//Method overloading!
	//public void feed(Tiget tiger){System.out.println("feed apple");}
	//public void feed(Lion lion){System.out.println("feed banana");}
	public void feed(Predator predator){System.out.println("feed "+predator.getFood());}
	
	//we have to add new feed function if new animal is added. like below
	//public void feed(Crocodile crododile){}~
	//public void feed(Leopard leopard){}~
	//we can approach this code esaily by using interface
	
	//if new animal is added, just define as Predator interface
	//now, tiger: object of Tiger class & Predator interface
	//	   lion : object of Lion  class & Predator interface
	//That one object has one more type is called 'polymorphism'
	
	//Now, ZooKeeper class bacome independent class with kind of Animal
	
	//Q. Why it's called as interface?
	//A. harddisk, memory stick, digital camera...etc can be used by USB port. if we know standard of USB port, we can use it regardless of devices.
	//	 like ZooKeeper use Animal by Predator regardless of Tiger, Lion...etc!
	//	 In addition, for using USB port, any devices follow special rule liek getFood() method in Predator interface:)
}

public class Main{
	public static void main(String args[]){
		ZooKeeper zooKeeper=new ZooKeeper();
		Tiger tiger=new Tiger();
		Lion lion=new Lion();
		Crocodile crocodile=new Crocodile();
		zooKeeper.feed(tiger);//feed apple
		zooKeeper.feed(lion);//feed banana
		zooKeeper.feed(crocodile);//feed human
	}
}