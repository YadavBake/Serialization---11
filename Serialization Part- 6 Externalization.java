
												Serialization Part- 6||Externalization
											  ==========================================

=====================
 5. Externalization.											  
=====================

 -> In serialization everything takes by JVM and programmer doesn't have any control.
 
 -> In serialization it is always possible to save total object to the file and it is not possible to save part of 
    the object, which may creates performance problems.

 -> To overcome this problem we should go for Externalization.

 -> The main advantage of Externalization over serialization is everything takes care by programmer and JVM doesn't have 
    any control.

 -> Based on our requirement we can save either total object or part of the object, which improves performance of the 
	System.
	
 -> To provide Externalizable ability for any java object compulsory the currosponding class should implements 
	Externalizable interface.
	
		class Account implements Externalizable{}
	
	
 -> Externalizable interface defines two methods:
	
		1. writeExternal()
	
		2. readExternal()
	
 -> Externalizable is the child interface of serializable.


	Serializable(I)(1.1V)
		 ^
		 |
		 |
		 |
		 |
		 |			 |->writeExternal()	
	Externalizable(I)|-> came in 1.1v	 
					 |->readExternal()


	1. public void writeExternal(ObjectOut out) throws IOException
       {
       	out.writeObject(s);
       	out.writeInt(i);
       }
	 -> This method we will be executed automatically at the time of serialization.
  
	 -> Within these method we have to write code to save required variables to the file.


	2. public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
       {
			s = String()in.readObject();
			i = in.readInt();
	   }
	 
	 -> This method we will executed automatically at the time of Deserialization.

	 -> Within these method we have to write code to read required variables from the file and assign to current object.
	
	 -> But strictly speking at the Deserialization JVM will create a separate new object by executing public no-argument
		constructor.
		
	 -> On that object JVM will call readExternal() method .
	 
	 -> Hence a every Externalizable implimented class should compulsory containe public no-argument constructor otherwise 
		we will get Runtime Exception saying InvalidClassException.

  Ex.


	import java.io.*;
class ExternalizableDemo implements Externalizable
	{
		
		String s;
		int i;
		int j;
		
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
	
	
	
			
        		----------------
        		| S ="Durga"   |
         t1---->| i=10 		   |<------
        		| j=20 		   |	  |	
        		|			   |	  |// Serialization 
                ----------------	  |
        							  |				--------------
                                      |             | 		      |
                                      |-------------->S = Durga   |
                                                    |             |
                                                    |   	      |
                                      |-------------->i = 10   	  |
                                      |             | 			  |
                                      |             ---------------
									  |                abd.ser 
        		--------			  |						  
        		| Durga|			  | // Deserialization			
         t2---->|s=null|<-------------|						  
        		|i=0   |	 		  |						  
        		| 10   |<-------------|								
        		|j=0   | 
				--------									  
				
		- If the class implements serializable then total object will be saved to the file in this case output is:

				Durga...10...20
				
		- If the class implements Externalizable then only required variables will be saved to the file in these case 
		  output is:
		  
				no-argument constructor 
				Durga...10...0
			
		
 Note: 
 
	- In serialization transient keyword will play role but Externalization transient keyword won't play any role 
	  ofcourse transient keyword not required in Externalization.

	import java.io.*;
	class ExternalizableDemo implements Externalizable
	{
			
		transient String s;
		transient int i;
		transient int j;
		
		ExternalizableDemo(String s, int i, int j)
		{
			this.s=s;
			this.i=i;
			this.j=j;
		}
		public ExternalizableDemo(){
			
			System.out.println(" no-argument constructor");
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
	
		no-argument constructor 
        Durga...10...0
		
		
 -> Differences between Serialization and Externalization

	--------------------------------------------------------------------------------------------------------------	
	|	Serialization 							   |					 Externalization						 |
	--------------------------------------------------------------------------------------------------------------
	|                                              |                                                             |
	|1. It is ment for default serialization.	   |1. It is ment for customized serialization.                  |
	|                                              |                                                             |
	|2. Here every things takes care by JVM and	   |2. Here every things takes care by programmer 	             |
	|	programmer doesn't have any control.	   |   and JVM doesn't have any control.                         |
    |                                              |                                                             |
	|3. In this case always possible to save total | 3. Based on our requirement we can save Either              |
    |	object to the file and it is not possible  |   total object or part of the object.                       |
    |	to save part of the object.                |                                                             |
    |                                              |                                                             |
    |4. Relatevily performance is low.			   |4. Relatevily performance is high.                           |
    |                                              |                                                             |
	|5. It is the best choice if we want to save   | 5. It is the best choice if you want to save part           |
    |	total object to the file				   |   of the object to the file.                                |
    |                                              |                                                             |
    |6. Serializable interface doesn't containe    |6. Externalizable interface containes two methods            |
    |	any methods and it is a marker interface.  |   writeExternal() and readExternal(). and hence             |
    |											   |   it is not a marker interface.	                         |
	|                                              |                                                             |
	|7. Serializable implemented class not required| 7. Externalizable implemented class should compulsory       |
    |	to containe public no-argument constructor |	   containe public no-argument constructor otherwise	 |
    |											   |   we will Runtime Exception saying InvalidClassException.	 |
    |                                              |                                                             |
    |8. transient keyword will play role in        | 8. transient keyword won't play any role in Externalization.|
	|	serialization.							   |   ofcourse won't be required.			                     |
	--------------------------------------------------------------------------------------------------------------