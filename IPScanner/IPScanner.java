
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IPScanner {

    public static void runScanner(String command, String   address) {

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

            if (text.contains("unreachable")) {
                System.out.println(address + " : Not Connected");
            } else {
                System.out.println(address + " : Connected");
                    
            }

        } catch (IOException e) {
            System.out.println("Error.....Exiting Gracefully.");
        }
    }

    public static void main(String[] args) {

        String ip = "192.168.1.";   // For trial on my local network
        String new_ip;
        for (int i = 0; i < 11; i++) {
            new_ip = ip.concat(String.valueOf(i));
            runScanner("ping ", new_ip);
        }
                

    }

}
