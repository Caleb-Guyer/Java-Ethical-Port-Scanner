import java.util.Scanner;
import java.net.Socket;
import java.net.InetSocketAddress;
import java.io.IOException;

public class PortScanner
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);

        String host = "localhost";

        System.out.println("WARNING: Only run this on your own machine!\n" +
                "This program is for educational purposes only.\n" +
                "Do not use this on other websites or systems without permission.");

        System.out.println("Enter the target host (Defaults are localhost or 127.0.0.1): ");
        String hostInput = scan.nextLine();

        if (hostInput.isEmpty())
        {
            hostInput = "localhost";
        }
        else
        {
            host = hostInput;
        }

        System.out.println("Enter a start port (default 1): ");
        String startPortInput = scan.nextLine();

        int startPort = 1;  // default value for startPort
        if (!startPortInput.isEmpty())
        {
            try
            {
                startPort = Integer.parseInt(startPortInput);
            }
            catch (NumberFormatException e)
            {
                startPort = 1;  // in case of bad input
            }
        }

        System.out.println("Enter an end port (default 1024): ");
        String endPortInput = scan.nextLine();

        int endPort = 1024;  // default value for endPort
        if (!endPortInput.isEmpty()) {
            try {
                endPort = Integer.parseInt(endPortInput);
            } catch (NumberFormatException e) {
                endPort = 1024;  // in case of bad input
            }
        }

        long startTime = System.currentTimeMillis();
        int openCount = 0;

        for (int i = startPort; i <= endPort; i++)
        {
            try (Socket socket = new Socket())
            {
                InetSocketAddress address = new InetSocketAddress(host, i);

                socket.connect(address, 800);   // 800 ms timeout

                System.out.println("Port " + i + " is OPEN");
                openCount++;

            }
            catch (IOException e)
            {
                // Port closed
            }

            // Uncomment this if testing and you don't want it to take forever
            /* if (endPort > startPort + 300)
            {
                endPort = startPort + 300;
            } */
        }
        long duration = System.currentTimeMillis() - startTime;
        System.out.println("\nScan complete!");
        System.out.println("Found " + openCount + " open ports.");
        System.out.println("Finished in " + (duration / 1000.0) + " seconds.");
    }
}