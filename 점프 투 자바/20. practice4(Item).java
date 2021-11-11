package java_project_1;

public class Item {
	private String name;
	private int length;
	private String value;
	
	//Getter & Setter
	public String getName() { return name; }
	public void setName(String name) { this.name=name; }
	
	public int getLength() { return length; }
	public void setLength(int length) { this.length=length; }
	
	public String getValue() { return value; }
	public void setValue(String value) { this.value=value; }
	//we can also use Source._Generatr Getter and Setter in Eclipse

	//return value
	public String raw() {
		StringBuffer padded=new StringBuffer(this.value);
		while(padded.toString().getBytes().length<this.length)//No padded.length() beacuse of Korean(2bytes). java count Korean per 1byte. so count by byte. for preventing error
			padded.append(' ');//for make space
		
		return this.value;
	}
	
	//item_create method
	public static Item create(String name, int length, String value) {
		Item item=new Item();
		item.setName(name);
		item.setLength(length);
		item.setValue(value);
		return item;
	}

//	public static void main(String[] args) {
//		Item item=new Item();
//		item.setName("name");
//		item.setLength(10);
//		item.setValue("ChoiJiWoong");
//		System.out.println("["+item.raw()+"]");
//	}
}