
									Serialization Part- 2||Serialazation in the case of object graphs
								  =====================================================================

 
 
 Note: 
 
	-> We can serialize any number of objects to the file but in which order we serialazed in the same order only we 
	   we have to deserialize that is order of object is important in serialazation 
 
 
	class Dog
	{
	}
	class Cat
	{
	}
	class Rat
	{
	}
	class SerializeDemo1
	{
		public static void main(String args[])throws Exception
		{
			Dog d1 = new Dog();
			Cat c1 = new Cat();														---------
			Rat r1 = new Rat();                                                     |		|
			                                                                        | -----	|
			FileOutputStream fos = new FileOutputStream("abd.ser");                 | |Dog| |
			ObjectOutputStream oos = new ObjectOutputStream(fos);                   | ----- |
			oos.writeObject(d1)                                                     | -----	|
			oos.writeObject(c1)                                                     | |Cat|	|
			oos.writeObject(r1)                                                     | ----- |
																					| -----	|
			FileImageInputStream fis = new FileImageInputStream("abd.ser");         | |Rat| |
			ObjectInputStream ois  = new ObjectInputStream(fis);                    | ----- |
																					---------
			Dog d2 = (Dog)ios.readObject();                                          abd.ser      
			Cat c2 = (Cat)ios.readObject();                                                 
			Rat r2 = (Rat)ios.readObject();
			
			System.out.pritnl(d2+"..."+c2+"..."+r2);
		}
	}
			
			
			
   -> If we don't know order of object in serialazation code look like below code.
   
	
			FileInputStream fis = new FileInputStream("abd.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			Object o = ios.readObject();
			if( o instanceOf Dog)
			{
				Dog d2 = (Dog)o;
				// perfor Dog specific funcationality
			}
			else if (o instanceOf Cat)
			{
				Cat c2 = (Cat)o;
				// perform Cat specific funcationality
			}else if(o instanceOf Rat)
			{
				Rat r2 = (Rat)o;
				// perform Rat specific funcationality
			}
			
===========================================================================================================================

												2. Object Graphs in Serialization.
											  =====================================

 -> Whenever we are serializing an object, the set of all objects which are reacheable from that object will be serialazed
	automatically this group of objects is nothing but object graph.
	
 -> In Object graph every object should be serialazable if at list one object is not serialazable then we will get Runtime 
	exception saying NotSerializableException.
	
 -> 
	
	Ex. 

	class Dog implements Serializable
	{
		Cat c = new Cat();
	}
	class Cat implements Serializable
	{
		Rat r = new Rat();
	}
	class Rat implements Serializable
	{
		int j = 20;
	}
	class SerializableDemo2
	{
		public static void main(String args[])throws Exception
		{
			
			Dog d1 = new Dog();
			FileOutputStream fos = new FileOutputStream("xyz.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(d1);	
			
			FileInput fis = new FileImageInputStream("xyz.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			Dog d2 = Dog)ois.readObject();
			System.out.println(d2.r.c.j);//20
		}
	}
	
	-----------------------------
	|						    |	
	|					------  |
	|			  |---->|	 |  |	Serialization 
	|			  |		|j=20|  |--------------------------
	|			--|---  |    |  |						  |						
	|	  ----->| |	 |  ------  |						  |						
	|	  |	    | r  |          |						  |							
	|	  |     |    |          |					      |
	|	--|---  ------          |                         |
	|	| |	 |                  |						  |	
	|	| c  |                  |				          |
	|	|    |                  |						  |				 ----------------------------------------------
	|	------                  |                         |              |											  |
	|     d1                    |				          |              |                                            |
	-----------------------------					      |              |                                            |
			Object Graph                                  |              |		 ----------------------------         |
                                                          |              |       |						    |         |
                                                          |--------------------->|					------  |         |
                                                                         |       |			  |---->|	 |  |         |
                                                                         |       |			  |		|j=20|  |         |
                                                                         |       |			--|---  |    |  |         |
                                                                         |       |	  ----->| |	 |  ------  |         |
                                                                         |       |	  |	    | r  |          |         |
                                                                         |       |	  |     |    |          |         |
                                                                         |       |	--|---  ------          |         |
                                                            |--------------------|	| |	 |                  |         |
                                                            |            |       |	| c  |                  |         |
                                                            |            |       |	|    |                  |         |
                                                            |            |       |	------                  |         |
                                                            |            |       |     d1                   |         |
                                                            |            |       ----------------------------         |
													        |            |                                            |
													        |            |                                            |
													        |            |                                            |
                                                            |            |                                            |
													        |             ----------------------------------------------
	-----------------------------							|					
    |						    |                           |                     
    |					------  |                           |                     
    |			  |---->|	 |  |                           |                     
    |			  |		|j=20|  |                           |                     
    |			--|---  |    |  |                           |                     
    |	  ----->| |	 |  ------  |                           |                     
    |	  |	    | r  |          |<--------------------------|                                                
    |	  |     |    |          |	Deserialization
    |	--|---  ------          |
    |	| |	 |                  |
    |	| c  |                  |
    |	|    |                  |
    |	------                  |
    |     d2                    |
    -----------------------------
			Object Graph
			
			
  - In the above program whenever we are serializing dog object automatically Cat and Rat objects got serialazed because 
    this are part of object graph of Dog.

  - Among Dog,Cat,and Rat objects if at list one object is not serialazable then we will get Runtime Exception saying 
	NotSerializableException.