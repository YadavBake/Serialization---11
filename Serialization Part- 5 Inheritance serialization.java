
											Serialization Part- 5||Inheritance serialization
										 =====================================================

 =============================================
 4. Serialization with respect to Inheritance.
 =============================================

 Case 1: 
	
	-> Eeven though child doesn't implements serializable we can serialize child class object if parent class implements
	   serializable interface. That is serializable nature is inheriting from parent from child.Hence a if parent is 
	   serializable then by default every child is serializable	
	   
	   Ex. 
		
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
                                      |                abd.ser 
        		--------			  |						  
        		|	   |			  | // Deserialization			
         d2---->| i=10 |			  |						  
        		| j=20 |			  |						  
        		|	   |<-------------|								
        		--------									  
				
	   - In the above example even though dog class doesn't implements serializable we can serialize dog object because 
		 it's parent Animal class implements serializable.
		 
		 
  Note: 

	- Object class doesn't implement serializable interface 
 
 ------------
  Conclusion
 ------------
	
 1. Eeven thoug parent class doesn't implement serializable we can serialize child class object if child class
    implements interface.
	  
 2. That is to serialize child class object parent class need not be serializable.

 3. At the time of serialization JVM will check is any variable inheriting from non-serializable parent or not, If any 
	variable inheriting from non-serializable parent then JVM ignors original value and save default value to the file.

 4. At the time of Deserialization JVM will check is any parent class non-serializable or not if any parent class is
	non-serializable then JVM will execute instance control flow in every non-serializable parent and share it's 
	instance variable to the current object.
	
 5. While executing instance control flow of non-serializable parent JVM will always call no-argument constructor hence 
	every non-serializable class should compulsory containe no-argument constructor it may be default constructor
	generated by compiler or customized constructor explicitly provided by programmer.otherwise we will Runtime 
	exception saying InvalidClassException.
	 
	Ex. 
		import java.io.*;		
		class Animal 
		{
			int i =10;									    			 
		}																
		class Dog extends Animal implements Serializable
		{
			int j = 20;
		}
		
		class InheritSerializeDemo
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
		
		
		
        		--------
        		|	888|
         d1---->| i=10 |<--------------
        		| j=20 |			  |	
        		|	999|			  |// Serialization 
                --------			  |
        							  |				--------------
                                      |             | --------    |
                                      |-------------->|		 |    |
                                                    | | i= 0 |    |
                                                    | | j=999|    |
                                      |-------------->|      |	  |
                i = 10                |             | --------	  |
                     |                |             ---------------
                     |------|         |                abd.ser 
        		--------	|		  |						  
        		|	   |	|		  | // Deserialization			
         d2---->| i=10 |<---|		  |						  
        		| j=999|	 		  |						  
        		|	   |<-------------|								
        		--------									  

		import java.io.*;		
		class Animal
		{
			int i =10;
			Animal()
			{
				System.out.println("Animal Cosntructor call");
			}
		}
		class Dog extends Animal implements Serializable
		{
				int j =20;
			Dog()
			{
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
		ouput:

		Animal Cosntructor call		
		Dog constructor call
		888...999
		Deserialization started
		Animal Cosntructor call
		10...999
		
		- If we commenting Animal class constructor then the output is :
		 
			Dog constructor call
			888...999
			Deserialization started
			10...999
		
		- IF we doesn't provide no-arg constructor and provide arg constructor then we will get Runtime exception is:

			Animal Cosntructor call
			Dog constructor call
			888...999
			Deserialization started
			Exception in thread "main" java.io.InvalidClassException: Dog; no valid constructor
					at java.base/java.io.ObjectStreamClass$ExceptionInfo.newInvalidClassException(ObjectStreamClass.java:173
					at java.base/java.io.ObjectStreamClass.checkDeserialize(ObjectStreamClass.java:792)
					at java.base/java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:2253)
					at java.base/java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1762)
					at java.base/java.io.ObjectInputStream.readObject(ObjectInputStream.java:540)
					at java.base/java.io.ObjectInputStream.readObject(ObjectInputStream.java:498)
					at InheritSerializeDemo2.main(InheritSerializeDemo2.java:40)
					
			
			
			
        		--------
        		|	888|
         d1---->| i=10 |<--------------
        		| j=20 |			  |	
        		|	999|			  |// Serialization 
                --------			  |
        							  |				--------------
                                      |             | --------    |
                                      |-------------->|		 |    |
                                                    | | i= 0 |    |
                                                    | | j=999|    |
                                      |-------------->|      |	  |
                i = 10                |             | --------	  |
                     |                |             ---------------
                     |------|         |                abd.ser 
        		--------	|		  |						  
        		|	   |	|		  | // Deserialization			
         d2---->| i=10 |<---|		  |						  
        		| j=999|	 		  |						  
        		|	   |<-------------|								
        		--------									  