import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.concurrent.Executors;

public class Server {

    boolean createCharacter = false;
    boolean updateCharacter = false;
    boolean deleteCharacter = false;
    boolean viewCharacter = false;


    public void start(final int portNumber){

        try(var serverSocket = new ServerSocket(portNumber)){
            System.out.println("Server started. Waiting for clients on port " + portNumber);
            try(var executor = Executors.newVirtualThreadPerTaskExecutor()){
                while(true) {
                    var clientSocket = serverSocket.accept();
                    executor.submit(() -> {
                        System.out.println("Client connected!");
                        var clientIP = clientSocket.getInetAddress().getHostAddress();
                        var clientPort = clientSocket.getPort();
                        try (var clientInput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                             var writer = new PrintWriter(clientSocket.getOutputStream(), true)) {
                            String inputLine;
                            while ((inputLine = clientInput.readLine()) != null) {
                                System.out.println(clientIP + ": " + clientPort + " Received from client..." + inputLine);
                                //we want the menu options response here

                                if (inputLine.equalsIgnoreCase("A")) {
                                    createCharacter = true;
                                    viewCharacter = false;
                                    updateCharacter = false;
                                    deleteCharacter = false;
                                    writer.println("Option A - Create New Random Character. Generating Random Character...");
                                } else if (inputLine.equalsIgnoreCase("B")) {
                                    viewCharacter = true;
                                    createCharacter = false;
                                    updateCharacter = false;
                                    deleteCharacter = false;
                                    writer.println("Option B - View Character. Enter Character ID to view that Character: ");
                                } else if (inputLine.equalsIgnoreCase("C")) {
                                    updateCharacter = true;
                                    createCharacter = false;
                                    viewCharacter = false;
                                    deleteCharacter = false;
                                    writer.println("Option C - Update Character.");
                                } else if (inputLine.equalsIgnoreCase("D")) {
                                    deleteCharacter = true;
                                    createCharacter = false;
                                    viewCharacter = false;
                                    updateCharacter = false;
                                    writer.println("Option D - Delete Character.");
                                } else {
                                    writer.println("That is not a valid option. Try again. Select A for Create New Random Character | Select B for View Character | Select C for Update Character | Select D for Delete Character");
                                }

                            }
                        } catch (IOException e) {
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
