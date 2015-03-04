
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
            System.out.println(text);
            if ((text.contains("Unreachable"))||(text.contains("timed"))) {
                System.out.println(address + " : Not Connected");
            } else {
                System.out.println(address + " : Connected");
                    
            }

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
                
        String new_ip;
        for (int i = 0; i < 4; i++) {
            new_ip = ip.concat(String.valueOf(i));
            runScanner("ping ", new_ip);
        }
                

    }

}
