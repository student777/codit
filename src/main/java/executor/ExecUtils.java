package executor;

import java.io.IOException;
import java.io.InputStream;

public class ExecUtils {
    public final static String WORKSPACE_PATH = "/home/yee/workspace/www/";


    public static String getStringFromProcess(Process process) {
        //get exec output
        InputStream errorStream = process.getErrorStream();
        InputStream inputStream = process.getInputStream();
        String errorOutput = getStringFromStream(errorStream);
        String output = getStringFromStream(inputStream);
        if (!errorOutput.equals("")) {
            return errorOutput;
        } else {
            return output;
        }
    }

    private static String getStringFromStream(InputStream is) {
        int i;
        byte[] b = new byte[4096];
        StringBuilder sb = new StringBuilder();
        try {
            while ((i = is.read(b)) != -1) {
                sb.append(new String(b, 0, i));
            }
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }


}
