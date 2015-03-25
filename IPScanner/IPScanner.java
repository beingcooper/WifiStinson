
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.regex.*;
import java.net.UnknownHostException;

public class IPScanner {

    public static void runScanner(String command1, String address) {

        String temp = "";
        String pattern;
        String command2= "arp -a ";
        try {
            String operation = command1 + address;
            Process pro = Runtime.getRuntime().exec(operation);
            BufferedReader inputStream = new BufferedReader(
                    new InputStreamReader(pro.getInputStream()));

            StringBuilder builder = new StringBuilder();

            for (int i = 0; i < 6; i++) {
                temp = inputStream.readLine();
                builder.append(temp);
            }
            String text = builder.toString();
            //System.out.println(text);
            

            if ((text.toLowerCase().contains("unreachable"))||(text.contains("timed"))) {
                System.out.println(address + " : Not Connected");
                
            } 
            else {
                System.out.println(address + " : Connected");
                Process mac = Runtime.getRuntime().exec("arp");
                BufferedReader input_mac = new BufferedReader(
                    new InputStreamReader(mac.getInputStream()));
                StringBuilder get_mac = new StringBuilder();

            for (int i = 0; i < 4; i++) {
                temp = inputStream.readLine();
                get_mac.append(temp+"\n");
            }
            String mtext = get_mac.toString();     
            System.out.println(mtext);
                    
            }

            /*Process pro1 = Runtime.getRuntime().exec("arp -a "+address);
            BufferedReader inputStream1 = new BufferedReader(
                    new InputStreamReader(pro.getInputStream()));

            StringBuilder builder1 = new StringBuilder();

            for (int i = 0; i < 3; i++) {
                temp = inputStream1.readLine();
                builder1.append(temp);
            }
            String text1 = builder1.toString();
            System.out.println(text1);
            */

        } catch (IOException e) {
            System.out.println("Error.....Exiting Gracefully.");
        }
    }

    public static void main(String[] args) throws UnknownHostException {

        InetAddress myhost=InetAddress.getLocalHost(); 
        String ip = myhost.getHostAddress();
        System.out.println(ip);
        int fixed_add = ip.lastIndexOf(".");
        ip = ip.substring(0, fixed_add+1);
        //System.out.println(ip);
                
        String new_ip;
        for (int j = 3; j < 4; j++) {
            new_ip = ip.concat(String.valueOf(j));
            runScanner("ping -l 1 -n 1 ", new_ip);
        }
                

    }

}
