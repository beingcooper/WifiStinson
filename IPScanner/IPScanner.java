
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPScanner {

    public static void runScanner(String command, String address) {

        String temp = "";
        try {
            String operation = command + address;
            Process pro = Runtime.getRuntime().exec(operation);
            BufferedReader inputStream = new BufferedReader(
                    new InputStreamReader(pro.getInputStream()));

            StringBuilder builder = new StringBuilder();

            for (int i = 0; i < 3; i++) {
                temp = inputStream.readLine();
                builder.append(temp);
            }
            String text = builder.toString();
            //System.out.println(text);
            

            if ((text.toLowerCase().contains("unreachable"))||(text.contains("timed"))) {
                System.out.println(address + " : Not Connected");
            } else {
                System.out.println(address + " : Connected");
                    
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
            System.out.println(text1);*/
            

        } catch (IOException e) {
            System.out.println("Error.....Exiting Gracefully.");
        }
    }

    public static void main(String[] args) throws UnknownHostException {

        InetAddress myhost=InetAddress.getLocalHost(); 
        String ip = myhost.getHostAddress();
        
        int fixed_add = ip.lastIndexOf(".");
        ip = ip.substring(0, fixed_add+1);
                
        String new_ip;
        for (int i = 1; i < 7; i++) {
            new_ip = ip.concat(String.valueOf(i));
            runScanner("ping ", new_ip);
        }
                

    }

}
