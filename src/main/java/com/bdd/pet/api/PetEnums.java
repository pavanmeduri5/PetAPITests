package com.bdd.pet.api;

public class PetEnums {

   public enum APICodes{
    CODE_200(200,""),
    CODE_201(201,""),
    CODE_400(400,"Missing required field:"),
    CODE_400_NFE(400,"NumberFormatException"),
    CODE_404(404,"not found"),
    CODE_405(405,"");

    public int code;
    public String msg;

    APICodes(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

     }
    public enum PetStatus {
        Available,
        Pending ,
        Sold
    }


}

