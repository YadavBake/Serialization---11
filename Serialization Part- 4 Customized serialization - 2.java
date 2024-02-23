 
										Serialization Part- 4||Customized serialization - 2
									  =======================================================


	import java.io.*;
	class Account implements Serializable
	{
		String uname= "Durga";
		transient String pwd="Anushka";
		transient int pin = 1234;
		
		private void writeObject(ObjectOutputStream os) throws Exception 
		{
			os.defaultWriteObject();
			String epwd = "123"+password;
			os.writeObject(epwd);
			int epin = "4444"+pin;
			os.writeInt(epin);
		}
		private void readObject(ObjectInputStream io) throws Exception
		{
			is.defaultReadObject();
			String epwd = (String)is.readObject();
			pwd = epwd.substring(3);
			int epin = is.reaInt();
			pin = epin-4444;
		}
			
	}
	
	class CustomizeSerializeDemo2
	{
		public static void main(String args[])throws Exception 
		{
			Account a1 = new Account();
			System.out.println(a1.uname+"..."+a1.password+".."+a1.pin);//Durga...Anushak...1234
			FileOutputStream fos = new FileOutputStream("abce.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(a1);
			
			FileInputStream fis = new FileInputStream("abce.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			Account a2 = (Account)ois.readObject();
			
			System.out.println(a2.uname+"..."+a2.pwd+"..."+a2.pin);
		}
	}
			
			
			
			
														-----------------------------	4444  
            											|encrypted = 4444+1234 =5678|  	1234	
            								   	        |encrypted ="123"+Anushka   | 	----  
            		---------------						----------------------------- 	5678  		
            		|	   		   |						  			|		   
             a1---->|uname="Durga" |-------					  			|	
            		|pwd="Anushak" |	  |	//os.defaultWriteObject()	|		
            		|pin=1234  	   |	  |// Serialization	  			|					
            5678    ----------------	  |				----------------|---------------	
			4444  						  |				| 				|			   |	
            ----                          |             |   -------------------------- |	
        --->1234                          |-------------||->|encrypted = "123Anushka"| |	
        |   --------------------------                  ||  |encrypted pin = 5678    | |    
        |   |Decrypted = "123Anushka"|<-------------------  -------------------------- |    
        |   |Decrypted epin = 5678   |    |-------------|   ---------------            |	
        |   --------------------------    |             |   |  	          |            |	
        |       |                         |             |   |uname="Durga"|            |	
        |   ----|                         |             |   |pwd=null     |            |	
        |   |		---------------		  |				|   |pin=0        |            |	
        |   |		|	          |		  | 			|   ---------------            |	
        |   |a2---->|uname="Durga"|		  |				|                              |	
        |   |		|pwd=null     |		  |				--------------------------------	
        |   |		|pin=0 ^      |<------|//Deserialization // is.defaultReadObject();		
        |   |		-----^-|-------						
        |   |			 | |												
        |   |------------|-|								
        |   			 |					
        |----------------|
                         
        
        
        
        