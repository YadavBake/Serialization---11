import java.io.*;		
class Animal
{
	int i =10;
	Animal()
	{
		System.out.println("Animal Cosntructor call");
	}
	/*Animal(int i )
	{
		System.out.println("Animal Cosntructor call");
	}*/
	
}
class Dog extends Animal implements Serializable
{		
		int j =20;
	Dog()
	{
		//super(10);		
		System.out.println("Dog constructor call");
	}
}

class InheritSerializeDemo2
{
	public static void main(String args[])throws Exception
	{
		Dog d1 = new Dog();
		d1.i = 888;
		d1.j = 999;
		System.out.println(d1.i+"..."+d1.j); //10...20
		FileOutputStream fos = new FileOutputStream("abd.ser");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(d1);
		System.out.println("Deserialization started");
		
		FileInputStream fis  = new FileInputStream("abd.ser");
		ObjectInputStream ios = new ObjectInputStream(fis);
		Dog d2 = (Dog)ios.readObject();
		
		System.out.println(d2.i+"..."+d2.j); //10...20
	}
}