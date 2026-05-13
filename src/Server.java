import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.concurrent.Executors;

public class Server {

    public void startServer(final int portNumber){

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
                            while((inputLine = clientInput.readLine()) != null){
                                System.out.println(clientIP + ": " + clientPort + " Received from client..." + inputLine);
                                writer.println(new StringBuilder(inputLine).reverse());
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
