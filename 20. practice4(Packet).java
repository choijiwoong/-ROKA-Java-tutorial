package java_project_1;

import java.util.HashMap;
import java.util.ArrayList;

public class Packet {
	////ArrayList for Item (because amount of Item is unknown)
	private ArrayList<Item> items=new ArrayList<Item>();
	//For access by name
	private HashMap<String, Item> nameAccess=new HashMap<String, Item>();
	
	
	//AddItem method to ArrayList & HashMap
	public void addItem(Item item) {
		this.items.add(item);
		//for prevention of same name
		if(nameAccess.containsKey(item.getName())) {
			throw new RuntimeException("Duplicated item name: ["+item.getName()+"]");
		}
		nameAccess.put(item.getName(), item);
	}
	//GetItem method to ArrayList
	public Item getItem(int index) { return this.items.get(index); }
	
	
	//return items by String
	public String raw() {
		StringBuffer result=new StringBuffer();
		for(Item item: items)
			result.append(item.raw());
		
		return result.toString();
	}
	
	//Parse (ex. 19081215 서울시 송파구 잠실동 123-3)
	public void parse(String data) {//we know protocol of String
		byte[] bdata=data.getBytes();
		int pos=0;
		
		for(Item item: items) {
			byte[] temp=new byte[item.getLength()];
			System.arraycopy(bdata,  pos,  temp,  0,  item.getLength());
			//source, start_point of source, target, start_point of target, copylength
			pos+=item.getLength();
			item.setValue(new String(temp));
		}
	}
	
	public Item getItem(String name) { return nameAccess.get(name); }
	
	public static void main(String[] args) {
		Packet packet=new Packet();
		
		Item item1=Item.create("이름", 10, "홍길동");
		Item item2=Item.create("전화번호", 11, "01012345678");
		
		packet.addItem(item2);
		packet.addItem(item1);
		
		System.out.println("["+packet.raw()+"]");
		
		//
		Packet recvPacket=new Packet();
		recvPacket.addItem(Item.create("생일", 8, null));//make item space
		recvPacket.addItem(Item.create("주소", 30, null));
		recvPacket.parse("19801215서울시 송파구 잠실동 123-3    ");//setting
		
		System.out.println(recvPacket.getItem("주소").raw());
	}
}
