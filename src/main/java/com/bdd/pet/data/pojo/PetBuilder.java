package com.bdd.pet.data.pojo;

import com.bdd.pet.api.PetEnums;

import java.util.List;

public class PetBuilder {

    private List<String> photoUrls;
    private String name;
    private Long id;
    private Category category;
    private List<Tags> tags;
    private String status;

    public PetBuilder id(Long id) {
        this.id = id;

        return this;
    }

    public PetBuilder name(String name) {
        this.name = name;

        return this;
    }

    public PetBuilder status(String status) {
        this.status = status;

        return this;
    }

    public PetBuilder photoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;

        return this;
    }

    public PetBuilder tags(List<Tags> tags) {
        this.tags = tags;

        return this;
    }

    public PetBuilder category(Category category) {
        this.category = category;

        return this;
    }

    public Pet build() {
        return new Pet(id, category,name,photoUrls ,tags,status);
    }


}
