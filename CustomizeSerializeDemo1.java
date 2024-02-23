import java.io.*;
class Account implements Serializable
{
	String uname ="Durga";
	transient String pwd = "Anushka";
	
	private void writeObject(ObjectOutputStream os) throws Exception
	{
		os.defaultWriteObject();
		String epwd="123"+pwd;
		os.writeObject(epwd);
	}
	
	private void readObject(ObjectInputStream is) throws Exception
	{
		is.defaultReadObject();
		String epwd = (String)is.readObject();
		pwd = epwd.substring(3);
	}
	
}

class CustomizeSerializeDemo1
{
	public static void main(String args[])throws Exception 
	{
		Account a1 = new Account();
		System.out.println(a1.uname+"..."+a1.pwd);// Durga...Anushak
		
		FileOutputStream fos = new FileOutputStream("abcd.ser");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(a1);

		FileInputStream fis = new FileInputStream("abcd.ser");
		ObjectInputStream ois = new ObjectInputStream(fis);
		Account a2 =(Account)ois.readObject();
		System.out.println(a2.uname+"..."+a2.pwd);
	}
}