import java.io.*;
class Sender
{
	public static void main(String args[])throws Exception
	{
		Dog1 d1 = new Dog1();
		FileOutputStream fos = new FileOutputStream("baake.ser");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(d1);
	}
}