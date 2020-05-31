package utils;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

public class Util {
    
    private static final int UNIT = 1024;
    private static final int MIN = 60000;
    private static final int SEC = 1000;

    public static String formatFileSize(long size){
        DecimalFormat df = new DecimalFormat("###,###.##");
        String result;
        
        int byteZone = 750,
                kbZone = 750 * UNIT,
                gbZone = 1000 * 1000 * 1000;
        
        if (size < byteZone) {
            result = df.format(size) + " bytes";
        } else if (size > byteZone && size <= kbZone) {
            result = df.format(size / UNIT) + " kb";
        } else if (size > kbZone  && size <= gbZone) {
            result = df.format(size / UNIT / UNIT) + " mb";
        } else {
            result = df.format(size / UNIT / UNIT / UNIT) + " gb";
        }
        
        return result;
    }
    
    public static String formatTime(long time) {
        String result;
        
        if (time < SEC) {
            result = time + " ms";
        }
        else if (time < MIN && time >= SEC) {
            result = String.format("%d sec", 
                TimeUnit.MILLISECONDS.toSeconds(time) - 
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(time)));
        } else {
            result = String.format("%d min, %d sec", 
                TimeUnit.MILLISECONDS.toMinutes(time), 
                TimeUnit.MILLISECONDS.toSeconds(time) - 
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(time)));
        }
        
        return result;
    }
    
//    public static String getMbPerSecond(long bytes, long millis) {
//        bytes = bytes / UNIT / UNIT;
//        millis = millis / SEC;
//        return bytes + "mb/" + millis + "s";
//    }
}
