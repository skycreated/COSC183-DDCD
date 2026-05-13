import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.concurrent.Executors;

public class Server {

    public void start(final int portNumber){

        try(var serverSocket = new ServerSocket(portNumber)){
            System.out.println("Server started. Waiting for clients on port " + portNumber);
            try(var executor = Executors.newVirtualThreadPerTaskExecutor()){
                while(true){
                    var clientSocket = serverSocket.accept();
                    executor.submit(()->{
                        System.out.println("Client connected!");
                        var clientIP = clientSocket.getInetAddress().getHostAddress();
                        var clientPort = clientSocket.getPort();
                        try(var clientInput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        var writer = new PrintWriter(clientSocket.getOutputStream(), true);){
                            String inputLine;
                            while((inputLine = clientInput.readLine()) != null) {
                                System.out.println(clientIP + ": " + clientPort + " Received from client..." + inputLine);
                                //we want the menu options response here
                                //if A{}
                                //if B{}
                                //if C{}
                                //if D{}
                                if (inputLine.equalsIgnoreCase("A")){
                                    //System.out.println("The client wants option A...");
                                    //writer.println(new StringBuilder(inputLine).reverse());
                                    // this reverses the text the user entered but we dont want that
                                    writer.println("Option A - Create New Random Character");

                                } else if (inputLine.equalsIgnoreCase("B")) {
                                    writer.println("Option B - View Character");
                                } else if (inputLine.equalsIgnoreCase("C")) {
                                    writer.println("Option C - Update Character");
                                } else  if (inputLine.equalsIgnoreCase("D")) {
                                    writer.println("Option D - Delete Character");
                                }


                            }
                        } catch (IOException e){
                            throw new RuntimeException(e);
                        }
                    });
                }
            } //not catch here but that is OK
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

}
