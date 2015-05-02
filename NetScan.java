import java.net.*;
import java.io.*;
import javax.swing.*;


public class NetScan
{
    String net_add="";
    String ip="";
    JTextArea incoming;
    BufferedReader macreader;
    JButton button = new JButton("START");
    String str,mymac,myip;
    String ret_mac="\0";                         
    int index = 0,k=0,l;
    public static String arr[][] = new String [255][2];
    public static int count=0;
    static GUI gui;
   
    public static void main(String[] args)
    {
        gui = new GUI();
        gui.frame1();           
    }

    public void action()
    {
        try
            {
                InetAddress myhost=InetAddress.getLocalHost(); 
                NetworkInterface network = NetworkInterface.getByInetAddress(myhost);
                byte[] mac = network.getHardwareAddress();
                gui.own("My MAC address : ");
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < mac.length; i++) 
                {
                    sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));        
                }
                str= sb.toString();
                arr[0][0]=str;
                gui.own(str);
                ip = myhost.getHostAddress();
                gui.own("\nMy IP address : ");
                arr[0][1]=ip;
                gui.own(ip);
                int fixed_add = ip.lastIndexOf(".");
                net_add = ip.substring(0, fixed_add+1); 
                gui.own("\n\nInformation about other devices on my network\n");                
                gui.own("-------------------------------------------------------------------------\n\n");
                Thread readerThread = new Thread(new IncomingReader());
                readerThread.start();
            }
            catch(UnknownHostException | SocketException ex)
            {
                
                System.out.println("ex");
            }  
    }
    
    public class IncomingReader implements Runnable 
    {
        @Override
        public void run()
        {
            String new_ip;
            try
            {
                for (int j = 1; j <10; j++) 
                {
                    new_ip = net_add.concat(String.valueOf(j));
                    if(!new_ip.equals(ip))
                    {   //count++;
                        runScanner("ping -l 1 -n 1 -w 50 ", new_ip);                         
                    }      
                }
                gui.own("\nScan completed.\nTotal number of Devices Found : "); 
                //System.out.println(k);
                str=String.valueOf(count);
                gui.own(str);
                //IPtoMAC();
                //gui.ipmac(count,arr);
            }
            catch(Exception ex)
            {
                System.out.println("exi");
            }              
        }
    }
    
    public String runScanner(String command1, String address) 
    {
        String temp = "";
        try 
        {
            String operation = command1 +" "+ address;
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
                count++;k++;
                gui.own("Device No : ");
                str=String.valueOf(count);
                gui.own(str);
                gui.own("\n--------------------\n");
                gui.own("\nIP Address : ");
                arr[k][1]=address;
                gui.own(address);
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
                gui.own("\n MAC Address : ");
                str=text1.substring(101,123).toUpperCase().trim();
                arr[k][0]=str;  
//                for(int i=0;i<count;i++)
//                    for(int j=0;j<2;j++)
//                        System.out.println(arr[i][j]);
                gui.own(str);
                gui.own("\n\n");
            }
        } 
        catch (IOException e) 
        {
            gui.own("Error.....Exiting Gracefully.");
        }
        return null;
    }  
    
    public void IPtoMAC(Object sti)
    {
        action();
        //System.out.println(sti);
        for(k=0;k<=count;k++)
        {            
                if(sti.equals(arr[k][1]))
                {
                    mymac=arr[k][0];
                    gui.ipmac(mymac);
                } 
                //System.out.println(mymac);
        }
    }
    
    public void MACtoIP(String sti)
    {
        action();
        String abc=sti.toUpperCase().trim();
        for(k=0;k<=count;k++)
        {            
                if(abc.equals(arr[k][0]))
                {
                    mymac=arr[k][1];
                    gui.macip(mymac);
                }            
        }
    }
    
    public void ipdropdown()
    {
        for(k=0;k<=count;k++)
        {            
            gui.settinglist(arr[k][1]);
            
        }
    }
    
    public void macdropdown()
    {
        for(k=0;k<=count;k++)
        {            
            gui.settinglist2(arr[k][0]);
            
        }
    }
}  