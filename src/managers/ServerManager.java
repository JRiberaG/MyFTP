package managers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import static main.JFrameMain.strResult;
import static main.JFrameMain.txtArea;
import exceptions.CorruptedFileException;

public class ServerManager {
    
    private final ServerSocket serverSocket;
    private Socket socket;
    private String path;
    private InputStream is;

    public ServerManager(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        path = "";
        strResult += "Status: Server opened.\n" 
                + "Status: Waiting for the client connection...\n";
        txtArea.setText(strResult);
    }
    
    public void setPath(String path){
        this.path = path;
    }
    
    public int receiveNumFiles() throws Exception {
        strResult += "Status: Receiving # of files...\n";
        txtArea.setText(strResult);
        
        int num = receiveInt();
        
        strResult += "Status: # of files received: " + num + ".\n";
        txtArea.setText(strResult);
        
        return num;
    }
    
    public void receive(int numOfFiles) throws IOException, ClassNotFoundException, Exception {
        // Loop to get all the files
        for (int i = 0; i < numOfFiles; i++) {
            // Accepts a new connection if needed
            if (i != 0) {
                socket = serverSocket.accept();
            }
            // Receives the name of the file
            String fileName = receiveFileName();
            
            // Receives the size of the file
            long bytes = receiveFileSize();
            
            System.out.println("File: " + fileName + ", " + bytes + " bytes");
            
            // Receives the file itself
            receiveFile(fileName, bytes);
            
            // Ends the connection if needed
            if (i != numOfFiles - 1) {
                socket.close();
            }
        }
        strResult += "Status: All files have been received.\n";
        txtArea.setText(strResult);
    }
    
    private String receiveFileName() throws IOException, ClassNotFoundException {
        // Get the bytes one by one and add them into an ArrayList
        ArrayList<Integer> nums = new ArrayList<>();
        int n;
        while((n = receiveInt()) != 0) {
            if (n != 0){
                nums.add(n);
            }
        }
        
        // Transform that ArrayList into an Array of ints
        int values[] = new int[nums.size()];
        for (int i = 0; i < values.length; i++) {
            values[i] = nums.get(i);
        }
        
        // Transform that Array of ints into an Array of Chars
        char charValues[] = new char[values.length];
        for (int i = 0; i < charValues.length; i++) {
            charValues[i] = (char) values[i];
        }
        
        // Returns a new String made from the Array of Chars
        return new String(charValues);
    }
    
    private long receiveFileSize() throws IOException {
        // Get the bytes one by one and add them into an ArrayList
        ArrayList<Integer> nums = new ArrayList<>();
        int n;
        while((n = receiveInt()) != 0) {
            if (n != 0){
                nums.add(n);
            }
        }
        
        // Transform that ArrayList into an array of ints
        int values[] = new int[nums.size()];
        for (int i = 0; i < values.length; i++) {
            values[i] = nums.get(i);
        }
        
        // Transform that array of ints into an array of Chars
        char charValues[] = new char[values.length];
        for (int i = 0; i < charValues.length; i++) {
            charValues[i] = (char) values[i];
        }
        
        // Convert that Array of chars into a String
        String str = new String(charValues);
        
        // Converts that String into a long
        long bytes = Long.parseLong(str);
        
        // Returns the size of the file
        return bytes;
    }    
    
    private void receiveFile(String fileName, long fileSize) throws FileNotFoundException, Exception {
        FileOutputStream fos = new FileOutputStream(path + fileName);

        strResult += "Status: Receiving file '" + fileName + "'...\n";
        txtArea.setText(strResult);

        byte[] buffer = new byte[65536];
        int read;
        // Reads the file
        while((read = is.read(buffer)) != -1){
            // Writes the file
            fos.write(buffer, 0, read);
        }
        
        // Closes the writer
        fos.close();
        
        // Deletes the InputStream
        is = null;
        
        // Checks whether the file is corrupted or not
        File f = new File(path + fileName);
        // The new file has the same size as the value sent by the client
        if (f.length() == fileSize) {
            strResult += "Status: File received.\n";
            txtArea.setText(strResult);
        }
        // File is corrupted and we delete it
        else {
            strResult += "Status: There has been a problem receiving the file: try again.\n";
            txtArea.setText(strResult);
            f.delete();
            throw new CorruptedFileException();
        }
    }
    
    private int receiveInt() throws IOException {
        if (is == null){
            is = socket.getInputStream();
        }
        return is.read();
    }

    public void sendOk() throws IOException {
        OutputStream os = socket.getOutputStream();
        os.write(1);
    }

    public void accept() throws IOException {
        socket = serverSocket.accept();
        
        strResult += "Status: Connection stablished.\n";
        txtArea.setText(strResult);
    }
    
    public void closeSocket() throws IOException {
        socket.close();
        strResult += "Status: Client connection closed.\n";
        txtArea.setText(strResult);
    }
    
    public void close() throws IOException {
        serverSocket.close();
        
        strResult += "Status: Server closed.\n";
        txtArea.setText(strResult);
    }
}
