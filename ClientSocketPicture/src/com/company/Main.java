package com.company;
import java.io.*;
import java.io.ByteArrayOutputStream;
import java.net.*;

public class Main {

    public static final int PORT = 3248;

    public static void main(String[] args) {
        byte[] aByte = new byte[1];
        int bytesRead;

        Socket clientSocket = null;
        InputStream is = null;

        try {
            clientSocket = new Socket("127.0.0.1", PORT);
            is = clientSocket.getInputStream();
        } catch (IOException ex) {
            // Do exception handling
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        if (is != null) {

            FileOutputStream fos = null;
            BufferedOutputStream bos = null;
            try {
                fos = new FileOutputStream("C:\\Users\\hackeru.HACKERU3\\Documents\\GitHub\\hadasa\\images.jpg");
                bos = new BufferedOutputStream(fos);
                bytesRead = is.read(aByte, 0, aByte.length);

                do {
                    baos.write(aByte);
                    bytesRead = is.read(aByte);
                } while (bytesRead != -1);

                bos.write(baos.toByteArray());
                bos.flush();
                bos.close();
                clientSocket.close();
            } catch (IOException ex) {
                // Do exception handling
            }
        }
    }
}
