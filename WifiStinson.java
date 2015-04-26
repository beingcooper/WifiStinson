
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.regex.*;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.net.NetworkInterface;


public class WifiStinson
{

   
    public static String runScanner(String command1, String address) 
    {

        String temp = "";
        String pattern;

        try 
        {
            String operation = command1 + address;
            Process pro = Runtime.getRuntime().exec(operation);
            BufferedReader inputStream = new BufferedReader(
            new InputStreamReader(pro.getInputStream()));
            StringBuilder builder = new StringBuilder();

            for (int i = 0; i < 6; i++)
            {
                temp = inputStream.readLine();
                builder.append(temp);
            }
            
            String text = builder.toString();
            
            if(text.contains("TTL"))
            {
                    
                    Process pro1 = Runtime.getRuntime().exec("arp -a "+address);
                    BufferedReader inputStream1 = new BufferedReader(
                    new InputStreamReader(pro1.getInputStream()));
                    StringBuilder builder1 = new StringBuilder();

                    for (int i = 0; i < 4; i++)
                    {
                        temp = inputStream1.readLine();
                        builder1.append(temp);
                    }
            
                    String text1 = builder1.toString();
                    
                    return text1.substring(105,123);
            }
            
            else 
            {   
                return "\0";
            }

        } 

        catch (IOException e) 
        {
            System.out.println("Error.....Exiting Gracefully.");
            return "\0";
        }
    }

    public static void IPtoMAC(String[][] pass_arr, int index, String inp_ip )
    {

        for(int i=0;i<index;i++)
        {
            System.out.println(pass_arr[i][0]);
            System.out.println(inp_ip);
            if(pass_arr[i][0].equals(inp_ip))
            {
                /* IP Found*/
                System.out.println(pass_arr[i][1]);
            }
        }
    }

    public static void MACtoIP(String[][] pass_arr, int index, String inp_mac )
    {

        for(int i=0;i<index;i++)
        {
            if(pass_arr[i][1].equals(inp_mac))
            {
                /* MAC Found*/
                System.out.println(pass_arr[i][0]);
            }
        }
    }
    
    public static void main(String[] args) throws UnknownHostException 
    {

        int no_of_dev;
        int count=0;
        int index=0;
        String ret_mac="\0";
        String arr[][] = new String [25][2];
        
        try
        {
            InetAddress myhost=InetAddress.getLocalHost(); 
            NetworkInterface network = NetworkInterface.getByInetAddress(myhost);
               
            byte[] mac = network.getHardwareAddress();
 
            System.out.print("\nYour MAC address : ");
 
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) 
            {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));        
            }
           
            System.out.println(sb.toString()+"\n");
            
            String ip = myhost.getHostAddress();
            
            System.out.print("Your IP address : ");
            System.out.println(ip+"\n\n");
            
            int fixed_add = ip.lastIndexOf(".");
            String net_add = ip.substring(0, fixed_add+1);
            
            System.out.println("Info about devices on your network\n");
            System.out.println("-----------------------------------\n\n"); 
        
            String new_ip;
        
            for (int j = 1; j < 255; j++) 
            {
                
                new_ip = net_add.concat(String.valueOf(j));
                
                if(!new_ip.equals(ip))
                {   
                    ret_mac = runScanner("ping -l 1 -n 1 -w 50 ", new_ip);                    

                    if(ret_mac != "\0")
                    {
                        count++;
                        
                        System.out.println("Device No : "+count);
                        System.out.println("-------------\n");
                        System.out.println("IP Address : "+new_ip);
                        System.out.print("\n");
                        System.out.println("MAC Address : "+ret_mac);
                        System.out.print("\n");
                        
                        arr[index][0]=new_ip;
                        arr[index++][1]=ret_mac;           
                    }
                }

            }
            
            System.out.println("\nScan completed.\nTotal number of Devices Found : "+ count);
        

        /*Method for Finding MAC using IP address     
            String inp_ip = to be entered by user;
            IPtoMAC(arr,index,inp_ip);

         Method for Finding IP using MAC address     
            String inp_mac = to be entered by user
            MACtoIP(arr,index,inp_mac); */
        
        }
        
        catch (IOException e) 
        {
            System.out.println("Error.....Exiting Gracefully.");
        }

    }   

}
