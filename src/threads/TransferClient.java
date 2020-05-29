package threads;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JList;
import static main.JFrameMain.activatePanelConnection;
import static main.JFrameMain.activatePanelRole;
import static main.JFrameMain.cm;
import static main.JFrameMain.setForNewConnection;
import static main.JFrameMain.strResult;
import static main.JFrameMain.txtArea;

public class TransferClient extends Thread implements Runnable {

    private final int numSelected;
    private final ArrayList<String> filenames;
    private String path;


    public TransferClient(int numSelected, ArrayList<String> filenames, String path) {
        this.numSelected = numSelected;
        this.filenames = filenames;
        this.path = path;
    }
    
    @Override
    public void run() {
        try {
            cm.sendNumFiles(numSelected);
            cm.receiveOk();
            if (!path.endsWith(File.separator)) {
                path += File.separator;
            }

            boolean needRecreate = false;
            for (String filename : filenames) {
                File file2 = new File(path + filename);
                if (needRecreate) {
                    cm.connect();
                }
                cm.sendFilename(filename);
                
                cm.sendFileSize(file2);
                
                cm.sendFile(file2);
                cm.close();
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
