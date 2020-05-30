package threads;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JList;
import static main.JFrameMain.activatePanelConnection;
import static main.JFrameMain.activatePanelRole;
import static main.JFrameMain.setForNewConnection;
import static main.JFrameMain.strResult;
import static main.JFrameMain.txtArea;
import static main.JFrameMain.clientManager;

public class TransferClient extends Thread implements Runnable {

    private final int numSelected;
    private final ArrayList<String> filenames;
    private String path;

    // Constructor
    public TransferClient(int numSelected, ArrayList<String> filenames, String path) {
        this.numSelected = numSelected;
        this.filenames = filenames;
        this.path = path;
    }
    
    @Override
    public void run() {
        try {
            // Sends the number of files
            clientManager.sendNumFiles(numSelected);
            // Receives confirmation from the server
            clientManager.receiveOk();
            // Adds file separation if needed to the path
            if (!path.endsWith(File.separator)) {
                path += File.separator;
            }

            boolean needRecreate = false; // flag to create a new connection
            // Iterates the files to send
            for (String filename : filenames) {
                File file = new File(path + filename);
                // Creates a new connection if needed
                if (needRecreate) {
                    clientManager.connect();
                }
                clientManager.sendFilename(filename);
                clientManager.sendFileSize(file);
                clientManager.sendFile(file);
                clientManager.close();
                
                // Indicates that a new connection needs to be made
                needRecreate = true;
            }
            
            strResult += "Status: Connection closed.\n";
            txtArea.setText(strResult);

            activatePanelConnection();
            setForNewConnection(false);
            activatePanelRole();
        } catch (Exception ex) {
            Logger.getLogger(TransferClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
