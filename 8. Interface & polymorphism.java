/*[1. neccessity of interface] 약간 인터페이스라는 네이밍이 좀 의미있어 보이넴
interface Predator{
	public String getFood();//no body of method! it will be created in implemented class! it's smillar with pure virtual function in C++
}

class Animal{
	String name;
	public void setName(String name){ this.name=name; }
}

//class Tiger extends Animal{}
//class Lion extends Animal{}
class Tiger extends Animal implements Predator, Barkable{
	public String getFood(){return "apple";}
	public void bark(){System.out.println("uh!");}
}
class Lion extends Animal implements Predator, Barkable{
	public String getFood(){return "banana";}
	public void bark(){System.out.println("ung!");}
}
//new Predator is added!
class Crocodile extends Animal implements Predator, Barkable{
	public String getFood(){return "human";}
	public void bark(){System.out.println("ang");}
	int a;
}

class ZooKeeper{//Method overloading!
	//public void feed(Tiget tiger){System.out.println("feed apple");}
	//public void feed(Lion lion){System.out.println("feed banana");}
	//[2. polymorphism]
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

interface Barkable{
	public void bark();
}

class Bouncer{
	public void barkAnimal(Animal animal){//(p.s)animal is instance of Animal!
		//Animal class can get object of Tiger or Lion!
		//if(animal instanceof Tiger)//instanceof is internal keyword that is used checking object is special class's object
		//	System.out.println("uh!");
		//else if(animal instanceof Lion)
		//	System.out.println("ung!");
		//else if(animal instanceof Crocodile)
		//	System.out.println(((Crocidile)animal).a);//*****is it safe? maybe not in my think. or just we have to use this function depending on interface?****
		//
		
		//make it simple by using interface!
		anlmal.bark();//polymorphism!
	}
}

public class Main{
	public static void main(String args[]){
		// [1. interface]
		//ZooKeeper zooKeeper=new ZooKeeper();
		//Tiger tiger=new Tiger();
		//Lion lion=new Lion();
		//Crocodile crocodile=new Crocodile();
		//zooKeeper.feed(tiger);//feed apple
		//zooKeeper.feed(lion);//feed banana
		//zooKeeper.feed(crocodile);//feed human
		
		Tiger tiger=new Tiger();
		Lion lion=new Lion();
		Crocodile crocodile=new Crocodile();
		crocodile.a=1;
		
		Bouncer bouncer=new Bouncer();
		bouncer.barkAnimal(tiger);
		bouncer.barkAnimal(lion);
		bouncer.barkAnimal(crocodile);
		
		//Tiger object can be expressed by many type
		//1. Tiger tiger=new Tiger();
		//2. Animal animal=new Tiger();//it's maybe not safe...
		//3. Predator predator=new Tiger();//only getFood method!
		//4. Barkable barkable=new Tiger();//only bark method!
		
		//if we want to use both methods, we can make new interface like below ways
		//1.
		//interface BarkablePredator{
		//	public void bark();
		//	public String getFood();
		//}
		//2.
		//interface BarkablePredator extends Predator, Barkable{}
		//interface can be inheritanced many interface by "extends"
	}
}*/

//more simple example of interface...그냥 인터페이스랑 다형성이랑 다른 파일로 빼둘걸..너무 난잡해졌네... 예제코드가 같아서 따로 뺴기 좀 아깝더라고...실수였지..대신 아래 코드로 퉁 치자..
interface Barkable{
	public void bark();
}
interface Predator{
	public String getFood();
}

interface BarkablePredator extends Barkable, Predator{}

class Animal{
	String name;
	void set_name(String name) {
		this.name=name;
	}
}

class Tiger extends Animal implements Predator, Barkable{
	public String getFood(){
		return "apple";
	}
	
	public void bark(){
		System.out.println("wow!");
	}
}
class Lion extends Animal implements BarkablePredator{
	public String getFood(){
		return "banana";
	}
	
	public void bark(){
		System.out.println("genius!");
	}
}

class ZooKeeper{
	public void feedAnimal(Predator animal){
		System.out.println("feed "+animal.getFood());
	}
}
class Bouncer{
	public void barkAnimal(Barkable animal){
		animal.bark();
	}
}

public class Main{
	public static void main(String[] args){
		Tiger tiger=new Tiger();
		Lion lion=new Lion();
		
		ZooKeeper zooKeeper=new ZooKeeper();
		zooKeeper.feedAnimal(tiger);
		zooKeeper.feedAnimal(lion);
		
		Bouncer bouncer=new Bouncer();
		bouncer.barkAnimal(tiger);
		bouncer.barkAnimal(lion);
	}
}