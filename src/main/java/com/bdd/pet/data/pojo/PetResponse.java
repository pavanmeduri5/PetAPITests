package com.bdd.pet.data.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class PetResponse {

    private Long id;
    private String name;
    private String status;
    private Category category;
    private List<Tags> tags;
    private List<String> photoUrls;

}
