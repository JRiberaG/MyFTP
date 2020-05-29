package threads;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static main.JFrameMain.activatePanelConnection;
import static main.JFrameMain.activatePanelRole;
import static main.JFrameMain.deactivatePanelTransfer;
import static main.JFrameMain.setForNewConnection;
import static main.JFrameMain.sm;
import static main.JFrameMain.strResult;
import static main.JFrameMain.txtArea;
import managers.ServerManager;

public class TransferServer extends Thread implements Runnable {

    private int numFiles;
    private int port;
    private boolean receive;

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
            sm = new ServerManager(port);
            sm.accept();
            while(!receive) {
                Thread.sleep(100);
            }
            numFiles = sm.receiveNumFiles();
            if (numFiles != -1) {
                sm.sendOk();
                sm.receive(numFiles);
                sm.closeSocket();
            } else {
                strResult += "Status: An error ocurred with the client connection.\n";
                txtArea.setText(strResult);
            }
            sm.close();
            
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
                    sm.closeSocket();
                    sm.close();
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
