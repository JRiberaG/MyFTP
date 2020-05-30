package managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import static main.JFrameMain.strResult;
import static main.JFrameMain.txtArea;

public class ClientManager {
    
    private Socket socket;
    private final String ip;
    private final int port;
    private OutputStream os;

    public ClientManager(String ip, int port) throws IOException{
        this.ip = ip;
        this.port = port;
        connect();
        
        strResult += "Status: Connection stablished.\n";
        txtArea.setText(strResult);
    }
    
    public void sendNumFiles(int numSelected) throws Exception {
        sendInt(numSelected);
    }
    
    public void sendFilename(String filename) throws IOException, Exception {
        // Transforms the string into an array of chars to send it byte by byte
        char[] arrayStr = filename.toCharArray();
        for (int i = 0; i < arrayStr.length; i++) {
            sendInt(arrayStr[i]);
        }
        // Sends a 0 to indicate that the server should end the transfer
        sendInt(0);
    }

    public void sendFileSize(File file) throws Exception {
        // Gets the file size in bytes
        long bytes = file.length();
        
        // Converts it into a String
        String bytesStr = String.valueOf(bytes);
        
        // Converts it into an array of chars
        char []bytesChar = bytesStr.toCharArray();
        
        // Sends it byte by byte
        for (int i = 0; i < bytesChar.length; i++) {
            sendInt(bytesChar[i]);
        }
        
        // Sends a 0 to indicate that the server should end the transfer
        sendInt(0);
    }
   
    public void sendFile(File file) throws Exception {
        FileInputStream fis = new FileInputStream(file);

        strResult += "Status: Sending file '" + file.getName() + "'...\n";
        txtArea.setText(strResult);
        
        byte[] buffer = new byte[65536];
        
        int read = 0;
        while((read = fis.read(buffer)) != -1){
            os.write(buffer, 0, read);
        }
        
        // Closes the reader
        fis.close();
        
        // Deletes the OutputStream
        os = null;

        strResult += "Status: File sent.\n";
        txtArea.setText(strResult);
    }
    
    private void sendInt(int num) throws Exception {
        if (os == null){
            os = socket.getOutputStream();
        }
        os.write(num);
    }
    
    public void connect() throws IOException {
        socket = new Socket(ip, port);
    }
    
    public void close() throws IOException {
        if (socket != null) {
            socket.close();
        }
    }

    public void receiveOk() throws IOException {
        InputStream is = socket.getInputStream();
        is.read();
    }
}
