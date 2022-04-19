package com.krishnakottayada.ftpdownload;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;

public class FTPDownload {

    public String echo(String value) {
        Log.i("Echo", value);
        return value;
    }

    public String downloadFTP(String ftpUrl, String ftpUserName, String ftpPassword, String filePath, String fileName, String storageFolderName) {
        Log.i("Echo", ftpUrl);


        FtpDownload ftpDownload = new FtpDownload(ftpUrl, ftpUserName, ftpPassword, filePath, fileName, storageFolderName);
        ftpDownload.execute();
        try {
            String data = ftpDownload.get();
            Log.i("Echo", data);
            return data;
        } catch (ExecutionException e) {
            return "FAILED";
        } catch (InterruptedException e) {
            return "FAILED";
        }
    }

    public String processValue(String myValue) {
        return myValue;
    }


    public class FtpDownload extends AsyncTask<Object, Void, String> {

        private String ftpUrl;
        private String ftpUserName;
        private String ftpPassword;
        private String filePath;
        private String fileName;
        private String storageFolderName;
        File mypath;

        public FtpDownload(String ftpUrl, String ftpUserName, String ftpPassword, String filePath, String fileName, String storageFolderName) {
            this.ftpUrl = ftpUrl;
            this.ftpUserName = ftpUserName;
            this.ftpPassword = ftpPassword;
            this.filePath = filePath;
            this.fileName = fileName;
            this.storageFolderName = storageFolderName;
        }

        @Override
        protected String doInBackground(Object[] params) {
            boolean status = false;
            FTPClient mFtpClient = null;
            File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), storageFolderName);
            mypath = new File(mediaStorageDir, fileName);
            Log.e("path", mypath.toString());
            Bitmap b = null;
            if (!mypath.exists()) {

                try {
                    mFtpClient = new FTPClient();
                    mFtpClient.setConnectTimeout(10 * 1000000);

                    mFtpClient.connect(InetAddress.getByName(ftpUrl));
                    status = mFtpClient.login(ftpUserName, ftpPassword);
                    Log.e("isFTPConnected", String.valueOf(status));
                    if (FTPReply.isPositiveCompletion(mFtpClient.getReplyCode())) {
                        mFtpClient.setFileType(FTP.ASCII_FILE_TYPE);
                        mFtpClient.enterLocalPassiveMode();
                        FTPFile[] mFileArray = mFtpClient.listFiles();

                        File parentDir = mypath.getParentFile();
                        if (!parentDir.exists())
                            parentDir.mkdirs();
                        OutputStream outputStream = null;


                        try {
                            outputStream = new BufferedOutputStream(new FileOutputStream(
                                    mypath));
                            mFtpClient.setFileType(FTP.BINARY_FILE_TYPE);
                            Log.e("NAME", filePath);
                            mFtpClient.retrieveFile(filePath, outputStream);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        } finally {
                            if (outputStream != null) {
                                try {
                                    outputStream.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }


                    }
                } catch (SocketException e) {
                    e.printStackTrace();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {
                b = BitmapFactory.decodeStream(new FileInputStream(mypath));
                return "Done";
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String bitmap) {
            Log.e("path", "-----DONE");
            processValue("done");
        }

    }
}
