package com.bdd.pet.data.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Category {
    private long id;
    private String name;

}
