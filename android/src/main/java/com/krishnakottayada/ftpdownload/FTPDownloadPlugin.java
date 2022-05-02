package com.krishnakottayada.ftpdownload;

import android.Manifest;

import com.getcapacitor.JSObject;
import com.getcapacitor.PermissionState;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.annotation.Permission;
import com.getcapacitor.annotation.PermissionCallback;

@CapacitorPlugin(
        name = "FTPDownload",
        permissions = {
                @Permission(
                        alias = "storage",
                        strings = {
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE
                        }
                )
        }
)

public class FTPDownloadPlugin extends Plugin {

    private FTPDownload implementation = new FTPDownload();

    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", implementation.echo(value));
        call.resolve(ret);
    }

    public void callDownload(PluginCall call) {
        String ftpURL = call.getString("ftpURL");
        String ftpUserName = call.getString("ftpUserName");
        String ftpPassword = call.getString("ftpPassword");
        String filePath = call.getString("filePath");
        String fileName = call.getString("fileName");
        String storageFolderName = call.getString("storageFolderName");

        JSObject ret = new JSObject();
        ret.put("ftpURL", implementation.downloadFTP(ftpURL, ftpUserName, ftpPassword, filePath, fileName, storageFolderName));
        call.resolve(ret);

        call.resolve();
    }

    @PluginMethod()
    public void doanloadFTPFile(PluginCall call) {
        if (getPermissionState("storage") != PermissionState.GRANTED) {
            requestPermissionForAlias("storage", call, "filePermsCallback");
        } else {
            callDownload(call);
        }
    }

    @PluginMethod()
    public void requestPermissions(PluginCall call) {
        if (getPermissionState("storage") != PermissionState.GRANTED) {
            requestPermissionForAlias("storage", call, "filePermsCallback");
        } 
    }


    @PermissionCallback
    private void filePermsCallback(PluginCall call) {
        if (getPermissionState("storage") == PermissionState.GRANTED) {
            callDownload(call);
        } else {
            call.reject("Permission is required to save the file");
        }
    }
}
