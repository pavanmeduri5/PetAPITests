package com.bdd.pet.data.pojo;
import lombok.Data;

@Data
public class PetNotFound {

    private String message;
    private String type;
    private int code;
}
