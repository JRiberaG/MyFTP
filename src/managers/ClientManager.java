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
        char[] arrayStr = filename.toCharArray();
        for (int i = 0; i < arrayStr.length; i++) {
            sendInt(arrayStr[i]);
        }
        sendInt(0);
    }

   
    public void sendFile(File file) throws Exception {
        FileInputStream fis = new FileInputStream(file);

        strResult += "Status: Sending file '" + file.getName() + "'...\n";
        txtArea.setText(strResult);
        
        int c = 0;
        // Reads the whole file
        while (c != -1) {
            // Reads the following char of the file
            c = fis.read();
            
            if (c != -1) {
                // Sends it to the server
                sendInt(c);
            }
        }
        // Closes the readed
        fis.close();

        strResult += "Status: File sent.\n";
        txtArea.setText(strResult);
    }
    
    private void sendInt(int num) throws Exception {
        OutputStream os = socket.getOutputStream();
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

    public void sendFileSize(File file) throws Exception {
        long bytes = file.length();
        String bytesStr = String.valueOf(bytes);
        char []bytesChar = bytesStr.toCharArray();
        for (int i = 0; i < bytesChar.length; i++) {
            sendInt(bytesChar[i]);
        }
        sendInt(0);
    }
}
