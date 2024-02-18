package com.bdd.pet.data.factory;

import com.bdd.pet.api.PetEnums;
import com.bdd.pet.data.pojo.Category;
import com.bdd.pet.data.pojo.Pet;
import com.bdd.pet.data.pojo.PetBuilder;
import com.bdd.pet.data.pojo.Tags;
import com.github.javafaker.Faker;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PetFactory {

    private Pet pet;
    Faker faker = new Faker();


    public Pet newPet() {

        Tags tags = new Tags(faker.random().nextInt(1000, 1500),
                faker.dog().name());
        pet = new PetBuilder()
                .id(faker.random().nextLong(1000))
                .category(new
                        Category(faker.random().nextLong(3000),
                        faker.dog().breed())
                )
                .name(faker.dog().name())
                .photoUrls(Arrays.asList(new String[]{"Url-1", "Url-2"}))
                  .tags(Collections.singletonList(tags))
                .status(PetEnums.PetStatus.Available.name().toLowerCase())
                .build();
        return pet;

    }

    public Pet petWithoutMandatoryFields() {
        Pet pet = newPet();
        pet.setName(StringUtils.EMPTY);
        pet.setPhotoUrls(Collections.<String>emptyList());
        return pet;
    }

    public Pet petWithMultipleTags() {
        List<Tags> tags = new ArrayList<>();
        tags.add(new Tags(faker.random().nextInt(1000, 1500),
                faker.dog().name()));
        tags.add(new Tags(faker.random().nextInt(1000, 1500),
                faker.dog().name()));
        Pet pet = newPet();
        pet.setTags(tags);
        return pet;
    }

    public String petWithInvalidId() {
        return "{ \"id\": " + faker.dog().name() + "}";
    }

    public Pet petWithNonExistingId() {
        Pet pet = new Pet();
        pet.setId(faker.random().nextLong(100000));
        return pet;
    }


}

