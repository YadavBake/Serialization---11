import java.io.*;
class ExternalizableDemo implements Externalizable
	{
		
		String s;
		int i;
		int j;
		
		/*transient String s;
		transient int i;
		transient int j;*/
		
		ExternalizableDemo(String s, int i, int j)
		{
			this.s=s;
			this.i=i;
			this.j=j;
		}
		public ExternalizableDemo(){
			
			System.out.println("no-argument constructor");
		}
		
		public void writeExternal(ObjectOutput out) throws IOException
		{
			out.writeObject(s);
			out.writeInt(i);
		}
		public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
		{
			s = (String)in.readObject();
			i = in.readInt();
		}
		
	
		public static void main(String args[]) throws Exception
		{
			
			ExternalizableDemo t1 = new ExternalizableDemo("Durga",10,20);
			
			FileOutputStream fos = new FileOutputStream("wer.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(t1);
			
				
			FileInputStream fis = new FileInputStream("wer.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			ExternalizableDemo t2 =(ExternalizableDemo)ois.readObject();
			
			
			System.out.println(t2.s+"..."+t2.i+"..."+t2.j); // Durga...10...0
		}
	}
	
	