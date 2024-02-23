import java.io.*;
class Account implements Serializable
{
	String uname= "Durga";
	transient String pwd="Anushka";
	transient int pin = 1234;
	
	private void writeObject(ObjectOutputStream os) throws Exception 
	{
		os.defaultWriteObject();
		String epwd = "123"+pwd;
		int epin = 4444+pin;
		os.writeObject(epwd);
		os.writeInt(epin);
	}
	private void readObject(ObjectInputStream is) throws Exception
	{
		is.defaultReadObject();
		String epwd = (String)is.readObject();
		pwd = epwd.substring(3);
		int epin = is.readInt();
		pin = epin-4444;
	}
		
}

class CustomizeSerializeDemo2
{
	public static void main(String args[])throws Exception 
	{
		Account a1 = new Account();
		System.out.println(a1.uname+"..."+a1.pwd+".."+a1.pin);//Durga...Anushak...1234
		FileOutputStream fos = new FileOutputStream("abce.ser");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(a1);
		
		FileInputStream fis = new FileInputStream("abce.ser");
		ObjectInputStream ois = new ObjectInputStream(fis);
		Account a2 = (Account)ois.readObject();
		
		System.out.println(a2.uname+"..."+a2.pwd+"..."+a2.pin);
	}
}
		