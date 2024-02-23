
										Serialization Part- 3||Customized serialization - 1	
									  ========================================================

--------------------------
 Customized Serialization 
--------------------------

 -> During default serialization there may be chance of loss of information because of transient keyword.
 
	Ex. 
		import java.io.*;
		class Account implements Serializable
		{
			String uname ="Durga";
			transient String pwd = "anuska";
		}
		class CustomizeSerializeDemo
		{
			public static void main(String args[])throws Exception 
			{
				Account a1 = new Account();
				System.out.println(a1.uname+"..."+a1.pwd);// Durga...Anushak
				
				FileOutputStream fos = new FileOutputStream("abc.ser");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(a1);
				
				
				FileInputStream fis = new FileInputStream("abc.ser");
				ObjectInputStream ois = new ObjectInputStream(fis);
				Account a2 = (Account)ois.readObject();
				System.out.println(a2.uname+"..."+a2.pwd); // Durga...null 
									
			}
		}
		
		
				---------------
        		|	   		   |
         a1---->|uname="Durga" |-------
        		|pwd="Anushak" |	  |	
        		|	    	   |	  |// Serialization 
                ----------------	  |
        							  |				------------------
                                      |             | --------------- |
                                      |-------------->|		        | |
                                                    | |uname="Durga"| |
                                                    | |pwd=null     | |
                                      |---------------|             | |
                                      |             | --------------- |
                                      |             -------------------
                                      |                   abc.ser 
        		---------------		  |						  		
        		|	          |		  | // Deserialization			
         a2---->|uname="Durga"|		  |						  		
        		|pwd=null     |		  |						  		
        		|	          |<------|								
        		---------------
				
        - In the above example before serialization account object can provide proper username and password but after 
		  Deserialization account object can provide only username but not password these is due declaring password
		  variable as transient.
		  
		- Hence a during default serialization there may be chance of loss of information because transient keyword to 
		  recover these loss of information we should go for customized serialization.


  Customized Seriliazation 
 ==========================

  -> We can implement customized serialization by using the following two methods.
  
		---------------------------------------------------------------------
		|1. private void writeObject(ObjectOutputStream os) throws Exception|
		--------------------------------------------------------------------- 	
		
	- These method will be executed automatically at the time serialization hence a at the time of serialization if we 
	  want to perform any activity we have to define that in this method only.
  
		---------------------------------------------------------------------
		|2. private void readObject(ObjectInputtStream is) throws Exception|
        ---------------------------------------------------------------------
	
	- This methos will be executed automatically at the time of Deserialization hence a at the ime of Deserialization if 
	  we want perform any activity we have to define that in this method only.
	  

 Note: 

	- The methods are callback methods because these are executed automatically by JVM.
	
	- While performing which object serialization we have to do extra work in the currosponding class we have to define 
	  above methods for example while performing Account object serialization if we required to do extra in  the account
	  class we have to define above methods.
	  
	  
		
		
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
	
	
												 
												--------------------------		
									   	        |encrypted ="123"+Anushka|	  
			---------------						--------------------------	  			
			|	   		   |						  			|	
	 a1---->|uname="Durga" |-------					  			|	
			|pwd="Anushak" |	  |	//os.defaultWriteObject()	|		
			|	    	   |	  |// Serialization	  			|						
	        ----------------	  |				----------------|---------------				
								  |				| 				|			   |									
	                              |             |   -------------------------- |										
	                              |-------------||->|encrypted = "123Anushka"| |									
	--------------------------                  ||  -------------------------- |                          									
	|Decrypted = "123Anushka"|<-------------------                             |                          									
	--------------------------    |-------------|   ---------------            |									
	    |                         |             |   |  	          |            |									
	    |                         |             |   |uname="Durga"|            |								    
	----|                         |             |   |pwd=null     |            |								    
	|		---------------		  |				|   |             |            |								    
	|		|	          |		  | 			|   ---------------            |								    
	|a2---->|uname="Durga"|		  |				|                              |								    
	|		|pwd=null     |		  |				--------------------------------								    
	|		|	   ^      |<------|//Deserialization // is.defaultReadObject();													-
	|		-------|-------						
	|			   |												
	|--------------|								
									
							
	- In the above program before serialization and after serialization account object can provide proper username 
	  password.
	  

 Note: 
 
	- Programmer can't call private methods directly from outside of the class but JVM can call private method directly
	  from outside of the class.
	
	
	
	
	
		  
		  
		  
		  
		  
		  
    
    
    
    
    
    
    
    