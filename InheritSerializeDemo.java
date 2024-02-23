import java.io.*;
		class Animal implements Serializable
		{
			int i =10;
		}
		class Dog extends Animal 
		{
			int j = 20;
		}
		
		class InheritSerializeDemo
		{
			public static void main(String args[])throws Exception
			{
				Dog d1 = new Dog();
				System.out.println(d1.i+"..."+d1.j); //10...20
				FileOutputStream fos = new FileOutputStream("abd.ser");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(d1);
				
				
				FileInputStream fis  = new FileInputStream("abd.ser");
				ObjectInputStream ios = new ObjectInputStream(fis);
				Dog d2 = (Dog)ios.readObject();
				
				System.out.println(d2.i+"..."+d2.j); //10...20
			}
		}
		