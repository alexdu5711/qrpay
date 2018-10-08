package com.mediasoft.qrpay.systeme;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Base64;
import java.util.UUID;

public class Sysdoc {

    private int user;



    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public void Sysdoc(int user)
    {
        this.setUser(user);
    }




    private String userPieceDir;
    private String userPhotoDir ;
    private String userVehiculeDir;


    public String getUserPieceDir() {
        return userPieceDir;
    }

    public void setUserPieceDir() {
        this.userPieceDir  = "docs/"+this.getUser()+"/pieces/";
    }

    public String getUserPhotoDir() {
        return userPhotoDir;
    }

    public void setUserPhotoDir() {
        this.userPhotoDir = "docs/"+this.getUser()+"/photos/";
    }

    public String getUserVehiculeDir() {
        return userVehiculeDir;
    }

    public void setUserVehiculeDir() {
        this.userVehiculeDir = "docs/"+this.getUser()+"/vehicules/";
    }




    public void CreatUserDir(int user)
    {


        new File(this.userPhotoDir).mkdirs();
        new File(this.userPieceDir).mkdirs();
        new File(this.userVehiculeDir).mkdirs();

    }



    public  String writingFile(String data,String dir,String ext) {
        String filename = null;
        try {

            byte[] imageByte = Base64.getDecoder().decode(data);

            filename = dir + UUID.randomUUID().toString() + ext;
            //filename = UUID.randomUUID().toString() + ".jpg";

            new FileOutputStream(filename).write(imageByte);

        } catch (Exception e) {
            System.err.println("IOException: " + e.getMessage());
        }
        return filename;
    }


}
