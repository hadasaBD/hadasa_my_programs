package com.company;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;

public class Main {

    public static final String SERVER_IP = "127.0.0.1";
    public static final int PORT = 3000;

    public static final int UPLOAD = 100;
    public static final int DOWNLOAD = 101;
    public static final int OKAY = 90;
    public static final int FAILURE = 91;


    static int existingVersion = 0;

    public static void main(String[] args) {
	    File downloadPath = getDownloadPath();

	    if(downloadPath == null){//user typed "exit" so we break out of the program
            exit();
        }
        while (true) {
            MenuChoice choice = menu();
            switch (choice) {
                case UPLOAD:
                    uploadChoice();
                    break;
                case DOWNLOAD:
                    downloadChoice(downloadPath);
                    break;
            }
        }

	    /*InputStream inputStream1 = null;
	    OutputStream outputStream1 = null;
	    InputStream inputStream2 = null;

	    Closeable[] closeables = new Closeable[3];
	    closeables[0] = inputStream1;
	    closeables[1] = inputStream2;
	    closeables[2] = outputStream1;

	    close(inputStream1, inputStream2, outputStream1);*/
    }

    static void uploadChoice(){
        while(true) {
            System.out.println("please enter path to file to upload:");
            String input = getUserInput();
            File fileToUpload = new File(input);
            if(fileToUpload.exists() && fileToUpload.isFile()){
                if(fileToUpload.length() > 1000000L){
                    System.out.println("file is too large");
                    break;
                }
                if(uploadToServer(fileToUpload,progress -> System.out.println("progress: " + progress))) {
                    System.out.println("uploaded successfully");
                    break;
                }
                else
                    System.out.println("error uploading file");
            }else{
                System.out.println("file doesn't exist or is a directory");
            }
        }
    }

    static void downloadChoice(File downloadPath){
        System.out.println("downloading...");
        DownloadResult downloadResult = downloadFromServer(downloadPath,progress -> System.out.println("progress: " + progress));
        if(downloadResult.getErrorCode() == DownloadResult.SUCCESS){
            System.out.println("downloaded successfully to " +
                    downloadResult.getFileName());
        }else if(downloadResult.getErrorCode() == DownloadResult.NETWORK_ERROR){
            System.out.println("error downloading");
        }else if(downloadResult.getErrorCode() == DownloadResult.ALREADY_HAVE_LATEST_VERSION){
            System.out.println("you already have the latest version");
        }else{
            System.out.println("unknown error");
        }
    }

    static MenuChoice menu(){
        while(true) {
            System.out.println("please choose:");
            System.out.println("1. upload file");
            System.out.println("2. download file");
            System.out.println("type 'exit' at any time to exit this program.");
            String choiceString = getUserInput();
            switch (choiceString){
                case "1":
                    return MenuChoice.UPLOAD;
                case "2":
                    return MenuChoice.DOWNLOAD;
                case "exit":
                    exit();
                    break;

            }
        }
    }

    static File getDownloadPath(){
        while(true) {
            System.out.println("please enter a download directory:");
            String pathString = getUserInput();
            if(pathString.equals("exit"))
                return null;
            File file = new File(pathString);
            if (file.exists() && file.isDirectory())
                return file;
            System.out.println("invalid path");
        }
    }

    static String getUserInput(){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }


    static void exit(){
        System.out.println("bye bye");
        System.exit(0);
    }


    static boolean uploadToServer(File fileToUpload, ProgressListener progressListener){
        Socket socket = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        InputStream fileInputStream = null;
        try {
            socket = new Socket(SERVER_IP, PORT);
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            outputStream.write(UPLOAD);
            int response = inputStream.read();
            if(response != OKAY)
                return false;
            byte[] fileNameBytes = fileToUpload.getName().getBytes();
            outputStream.write(fileNameBytes.length);
            outputStream.write(fileNameBytes);
            fileInputStream = new FileInputStream(fileToUpload);
            int actuallyRead;
            byte[] buffer = new byte[1024];
            long fileSize = fileToUpload.length();
            long totalUploaded = 0;
            while((actuallyRead = fileInputStream.read(buffer)) != -1){
                outputStream.write(buffer, 0, actuallyRead);
                totalUploaded += actuallyRead;
                int percentCompleted =
                        (int)(100L * totalUploaded / fileSize);
                if(progressListener != null)
                    progressListener.onProgress(percentCompleted);
            }
            fileInputStream.close();
            fileInputStream = null;

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            close(inputStream, outputStream, socket, fileInputStream);
        }
        return true;
    }

    static DownloadResult downloadFromServer(File downloadPath, ProgressListener progressListener){
        Socket socket = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        OutputStream fileOutputStream = null;
        DownloadResult downloadResult = new DownloadResult();
        try {
            socket = new Socket(SERVER_IP, PORT);
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            outputStream.write(DOWNLOAD);
            int response = inputStream.read();
            if(response != OKAY) {
                downloadResult.setErrorCode(DownloadResult.NETWORK_ERROR);
                return downloadResult;
            }
            byte[] versionBytes = new byte[4];
            int actuallyRead;
            actuallyRead = inputStream.read(versionBytes);
            if(actuallyRead != 4){
                downloadResult.setErrorCode(DownloadResult.NETWORK_ERROR);
                return downloadResult;
            }
            int version = ByteBuffer.wrap(versionBytes).getInt();
            if(version == existingVersion){
                outputStream.write(FAILURE);
                outputStream.close();
                outputStream = null;
                downloadResult.setErrorCode(DownloadResult.ALREADY_HAVE_LATEST_VERSION);
                return downloadResult;
            }else{
                outputStream.write(OKAY);
            }

            int fileNameLength = inputStream.read();
            if(fileNameLength == -1){
                downloadResult.setErrorCode(DownloadResult.NETWORK_ERROR);
                return downloadResult;
            }
            byte[] fileNameBytes = new byte[fileNameLength];
            actuallyRead = inputStream.read(fileNameBytes);
            if(actuallyRead != fileNameLength){
                downloadResult.setErrorCode(DownloadResult.NETWORK_ERROR);
                return downloadResult;
            }
            String fileName = new String(fileNameBytes);
            File downloadedFile = new File(downloadPath, fileName);
            downloadResult.setFileName(fileName);

            byte[] fileSizeBytes = new byte[Long.BYTES];
            actuallyRead = inputStream.read(fileNameBytes);
            if(actuallyRead != Long.BYTES){
                downloadResult.setErrorCode(DownloadResult.NETWORK_ERROR);
                return downloadResult;
            }
            long fileSize = ByteBuffer.wrap(fileSizeBytes).getLong();
            long totalDownload = 0;
            fileOutputStream = new FileOutputStream(downloadedFile);
            byte[] buffer = new byte[1024];
            while((actuallyRead = inputStream.read(buffer)) != -1){
                fileOutputStream.write(buffer, 0, actuallyRead);
                totalDownload += actuallyRead;
                int percent = (int)(totalDownload / fileSize);
                if(progressListener != null)
                    progressListener.onProgress(percent);
            }
            fileOutputStream.close();
            fileOutputStream = null;
            downloadResult.setErrorCode(DownloadResult.SUCCESS);
            existingVersion = version;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            close(inputStream, outputStream, socket, fileOutputStream);
        }

        return downloadResult;
    }


    static void close(Closeable... closeables){
        for(Closeable closeable : closeables){
            if(closeable != null)
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

    }


}
enum MenuChoice{
    UPLOAD, DOWNLOAD
}

class DownloadResult {

    public static final int SUCCESS = 0;
    public static final int ALREADY_HAVE_LATEST_VERSION = 1;
    public static final int NETWORK_ERROR = 2;

    private int errorCode;
    private String fileName;

    public DownloadResult(String fileName) {
        this.fileName = fileName;
    }

    public DownloadResult() {
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}