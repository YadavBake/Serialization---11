
												Serialization Part- 1 || Introduction
											  =========================================

---------------
 Serialization
---------------

  1. Introduction.
  2. Object Graphs in Serialization.
  3. Customized Serialization.
  4. Serialization with respect to Inheritance.
  5. Externalization.
  6. SerialVersinoUID


==========================================================================================================================

												Introduction.
											====================
											
											
  Serialization 
 ---------------

  -> The process of writing state of an object to a file is called serialization but strictally speeking it is the 
     process converting an object from java supported form into either file supported form or Network supported form.

  -> By using FileOutputStream(FOS) and ObjectOutputStream(OOS) classes we can impliment serialization.



			---------							   ---------------
			|		|--------------------		   | --------    |
	d1----->|		|				 ---|----------  |		|    |
			| Object|	-> -> -> -> -> ->  -> ->  -> |		|    |
			|		|				 ---|--------- | |Object|    |
			|		|-------------------	FOS	   | |		|	 |
			|		| OOS.writeObject(d1);		   | --------	 | 
			---------							   ---------------	
													  abc.txt 

  Deserialization 
------------------

 -> The process of reading state of an object from the file is called deserialization but strictally speeking it is the 
	process converting an object supported from either supported or Network supported  form into java supported form.
	
 
 -> By using FileInputStream(FIS) and ObjectInputStream(OIS) classes we can impliment Deserialization. 
	
    		---------							   ---------------
    		|		|--------------------		   | --------    |
    d2----->|		|				 ---|----------  |		|    |
    		| Object|<- <- <- <- <- <-  <- <-  <-    |		|    |
    		|		|				 ---|--------- | |Object|    |
    		|		|-------------------	FIS	   | |		|	 |
    		|		| OiS.readObject(d2);		   | --------	 | 
    		---------							   ---------------	
    												  abc.txt 
													  
													  
	Ex.
		
		import java.io.*;
		class Dog implements Serializable
		{
			int i = 10;
			int j = 20;
		}
		class SerializeDemo 
		{
			public static void main(String args[])throws IOException
			{
				Dog d1 = new Dog();
				FileOutputStream fos = new FileOutputStream("abc.ser");<-
				ObjectOutputStream oos = new ObjectOutputStream(fos);	|// These 3 line by default considard as 
				oos.writeObject(d1);<------------------------------------// serialization.
				
				FileInputStream fis = new FileInputStream("abc.ser");<-|/* These 3 line by default considard as 
				ObjectInputStream ois = new ObjectInputStream(fis);    |   Deserialization*/	
				Dog d2 = (Dog).ois.readObject();<----------------------|
				
				System.out.println(d2.i+"...."+d2.j); // 10...20 
			}
		}
				
				--------
				|	   |
		 d1---->| i=10 |<--------------
				| j=20 |			  |	
				|	   |			  |// Serialization 
		        --------			  |
									  |				--------------
                                      |             | --------    |
                                      |-------------->|		 |    |
                                                    | | i=10 |    |
                                                    | | j=20 |    |
                                      |-------------->|      |	  |
                                      |             | --------	  |
                                      |             ---------------
                                      |                abc.ser 
				--------			  |						  
				|	   |			  | // Deserialization						  
		 d2---->| i=10 |			  |						  
				| j=20 |			  |						  
				|	   |<-------------|									  
				--------									  
	
	- We can serializes only serializable objects.

	- An object is said to be serializable if and only if the corresponding class implements serializable interface.
	
	- Serializable interface prasent in java.io package and it doesn't containe any methods it is a marker interface.
	
	- If we are trying to serializes a non-serializable object then we will get Runtime exception saying 
	  NotSerializableException.
													  

--------------------
 transient keyword 	
--------------------

 -> Transient keyword (modifier) applicable only for variables but not for methods and classes.
 
 -> At the time of serialization if we don't want to save the value of a particular variable to meet security constrants
    then we should declare that variable as transient.
	
 -> While performing serialization JVM ignores origanel  value of transient variable and save default value to the file.

 -> Hence a transient means not to serialize .
 
  
	Ex. 
 
	import java.io.*;
    class Dog implements Serializable
    {
				  int i = 10;
    	transient int j = 20;
    }
    class SerializeDemo 
    {
    	public static void main(String args[])throws IOException
    	{
    		Dog d1 = new Dog();
    		FileOutputStream fos = new FileOutputStream("abc.ser");<-
    		ObjectOutputStream oos = new ObjectOutputStream(fos);	|// These 3 line by default 
    		oos.writeObject(d1);<------------------------------------// serialization.
    		
    		FileInputStream fis = new FileInputStream("abc.ser");<-|/* These 3 line by default c
    		ObjectInputStream ois = new ObjectInputStream(fis);    |   Deserialization*/	
    		Dog d2 = (Dog).ois.readObject();<----------------------|
    		
    		System.out.println(d2.i+"...."+d2.j); // 10...20 
    	}
    }
    		
    		--------
    		|	   |
     d1---->| i=10 |<--------------
    		| j=20 |			  |	
    		|	   |			  |// Serialization 
            --------			  |
    							  |				--------------
                                  |             | --------    |
                                  |-------------->|		 |    |
                                                | | i=10 |    |
                                                | | j= 0 |    |
                                  |-------------->|      |	  |
                                  |             | --------	  |
                                  |             ---------------
                                  |                abc.ser 
			--------			  |						  												  
			|	   |			  | // Deserialization						  												  
	 d2---->| i=10 |			  |						  												  
			| j= 0 |			  |						  												  
			|	   |<-------------|									  												  
    		--------									  
			
			
 ======================			
  transient vs static 
 ======================

  -> static variable is not part of object state and hence a it won't participate in serialization due to this declaring 
	 static variable as transient there is no use.
	 
	
	import java.io.*;
	class Dog implements Serializable
	{
			   int i = 10;
		static int j = 20;
	}
	class SerializeDemo 
	{
		public static void main(String args[])throws Exception
		{
			Dog d1 = new Dog();
			FileOutputStream fos = new FileOutputStream("abc.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(d1);
			
			FileInputStream fis = new FileInputStream("abc.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);    
			Dog d2 = (Dog)ois.readObject();
			
			System.out.println(d2.i+"...."+d2.j); // 10...20 
		}
	}
	
	       --------
	|----->|j = 20|
	|	   --------	
	|	   
	|	   		 -------
    |       	 |	   |
    |     d1---->| i=10|<--------------
    |       	 | 	   |			  |	
    |       	 |	   |			  |// Serialization 
    |            -------			  |
    |      							  |				--------------
    |                                 |             | --------    |
    |                                 |-------------->|		 |    |
    |                                               | | i=10 |    |
    |                                               | | 	 |    |
    |                                 |-------------->|      |	  |
    |                                 |             | --------	  |
    |                                 |             ---------------
    |                                 |                abc.ser 
    |      		--------			  |						  		
    |---------->| 	   |			  | // Deserialization			
         d2---->| i=10 |			  |						  		
           		|      |			  |						  		
           		|	   |<-------------|								
           		--------									  
				
====================				
 final vs transient 
====================

 ->  final variables will be participated in serialization directely by the value.
 
 -> Declaring a final variable as transient there is impact.
 
 
	import java.io.*;
    class Dog implements Serializable
    {
    			  int i = 10;
    	transient final int j = 20;
    }
    class SerializeDemo 
    {
    	public static void main(String args[])throws IOException
    	{
    		Dog d1 = new Dog();
    		FileOutputStream fos = new FileOutputStream("abc.ser");<-
    		ObjectOutputStream oos = new ObjectOutputStream(fos);	|// These 3 line by 
    		oos.writeObject(d1);<------------------------------------// serialization.
    		
    		FileInputStream fis = new FileInputStream("abc.ser");<-|/* These 3 line by d
    		ObjectInputStream ois = new ObjectInputStream(fis);    |   Deserialization*/
    		Dog d2 = (Dog).ois.readObject();<----------------------|
    		
    		System.out.println(d2.i+"...."+d2.j); // 10...20 
    	}
    }
    		
    		--------
    		|	   |
     d1---->| i=10 |<--------------
    		|   20 |			  |	
    		|	   |			  |// Serialization 
            --------			  |
    							  |				--------------
                                  |             | --------    |
                                  |-------------->|		 |    |
                                                | | i=10 |    |
                                                | |   20 |    |
                                  |-------------->|      |	  |
                                  |             | --------	  |
                                  |             ---------------
                                  |                abc.ser 
    		--------			  |						  								
    		|	   |			  | // Deserialization						  			
     d2---->| i=10 |			  |						  								
    		|   20 |			  |						  								
    		|	   |<-------------|									  					
    		--------									  
   
  Summary 

		 --------------------------------------------------------
		 |	Declaration 					|		output      |
		 --------------------------------------------------------
		 |	int i=10;						|		10          |
		 |	int j=20;						|		20          |
		 |	                                |                   |
		 |	transient int i =20;			|		 0          |
		 |	int j =20;						|		20          |
		 |	                                |                   |
		 |	transient static int i=10;		|		10          |
		 |	transient int j =20;			|		 0          |
         |                                  |                   |
		 |	transient int i=10;				|		 0		    |
		 |	transient final j=20;			|		20          |
		 |	                                |                   |
		 |	transient static int i=10;		|		10          |
		 |	transient final int j =20;		|		20          |
         |                                  |                   |
		 --------------------------------------------------------