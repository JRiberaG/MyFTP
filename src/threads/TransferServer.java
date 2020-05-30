package threads;

import java.io.IOException;
import static main.JFrameMain.activatePanelConnection;
import static main.JFrameMain.activatePanelRole;
import static main.JFrameMain.deactivatePanelTransfer;
import static main.JFrameMain.setForNewConnection;
import static main.JFrameMain.strResult;
import static main.JFrameMain.txtArea;
import managers.ServerManager;
import static main.JFrameMain.serverManager;

public class TransferServer extends Thread implements Runnable {

    private int numFiles;
    private final int port;
    private boolean receive;

    // Constructor
    public TransferServer(int port) {
        this.port = port;
        this.numFiles = -1;
        receive = false;
    }

    public int getNumFiles() {
        return numFiles;
    }
    
    public void setReceive(boolean receive) {
        this.receive = receive;
    }

    @Override
    public void run() {        
        try {
            // Creates the server
            serverManager = new ServerManager(port);
            // Accepts the client
            serverManager.accept();
            // Waits until the user presses 'Receive'
            while(!receive) {
                Thread.sleep(100);
            }
            // Gets the number of files that will be transfered
            numFiles = serverManager.receiveNumFiles();
            if (numFiles != -1) {
                // Sends confirmation to the client
                serverManager.sendOk();
                // Receives all the files
                serverManager.receive(numFiles);
                // Closes the client socket
                serverManager.closeSocket();
            }
            // Problem while trying to receive the # of files
            else {
                strResult += "Status: An error ocurred with the client connection.\n";
                txtArea.setText(strResult);
            }
            // Closes the server conection
            serverManager.close();
            
            activatePanelConnection();
            setForNewConnection(true);
            activatePanelRole();
        } catch (IOException ex) {
            if (ex.getMessage().contains("Address already")) {
                strResult += "Status: Couldn't create the connection: this port is already in use.\n";
                txtArea.setText(strResult);
                
                deactivatePanelTransfer();
                activatePanelConnection();
                setForNewConnection(true);
                activatePanelRole();
            } else {
                ex.printStackTrace();
            }  
        } catch (Exception ex) {
            if (ex.toString().contains("CorruptedFile")) {
                try {
                    serverManager.closeSocket();
                    serverManager.close();
                } catch (IOException ex1) {}
                deactivatePanelTransfer();
                activatePanelConnection();
                setForNewConnection(true);
                activatePanelRole();
            } else {
                ex.printStackTrace();
            }
        }
    }
}
