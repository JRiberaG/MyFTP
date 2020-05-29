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
    
    public void receive(int num) throws IOException, ClassNotFoundException, Exception {
        for (int i = 0; i < num; i++) {
            if (i != 0) {
                socket = serverSocket.accept();
            }
            String fileName = receiveFileName();
            long bytes = receiveFileSize();
            System.out.println("File: " + fileName + ", " + bytes + " bytes");
            receiveFile(fileName, bytes);
            
            if (i != num - 1) {
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
        
        String str = new String(charValues);
        long bytes = Long.parseLong(str);
        
        // Returns a new String made from the Array of Chars
        return bytes;
    }    
    
    private void receiveFile(String fileName, long fileSize) throws FileNotFoundException, Exception {
        FileOutputStream fos = new FileOutputStream(path + fileName);

        strResult += "Status: Receiving file '" + fileName + "'...\n";
        txtArea.setText(strResult);

        int c = 0;
        while (c != -1) {
            // Receives one byte
            c = receiveInt();
//            System.out.println("received: " +c);
            if (c != -1) {
                // Writes the file
                fos.write(c);
//                System.out.println("written: "+c);
            }
        }
        // Closes the writer
        fos.close();
        
        File f = new File(path + fileName);
        if (f.length() == fileSize) {
            strResult += "Status: File received.\n";
            txtArea.setText(strResult);
        } else {
            strResult += "Status: There has been a problem receiving the file: try again.\n";
            txtArea.setText(strResult);
            f.delete();
            throw new CorruptedFileException();
        }
    }
    
    private int receiveInt() throws IOException {
        InputStream is = socket.getInputStream();
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
