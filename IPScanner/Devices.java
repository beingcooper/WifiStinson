import java.io.*;
import java.net.InetAddress;


public class Devices
{
	public static void abc(String subnet)
	{
   		int timeout=1000;
   		try
   		{
   			for (int i=1;i<254;i++)
   			{
       			String host=subnet + "." + i;
       			if (InetAddress.getByName(host).isReachable(timeout))
       			{
           			System.out.println(host + " :  Connected");
       			}
    		}
	    }

	    catch (IOException e) 
        {
            System.out.println("Error!!!");
        }
    }

    public static void main(String[] args) 
    {
    	abc("192.168.1");	
    }
}