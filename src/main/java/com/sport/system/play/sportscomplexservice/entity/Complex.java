package com.sport.system.play.sportscomplexservice.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Document("sport_complex")
@Data
@Builder
public class Complex {
    @Id
    private String id;

    @NotNull
    private String name;

    @NotNull
    private String descriptionSmall;

    @NotNull
    private String descriptionLarge;

    @Builder.Default
    private List<Contact> contacts = new ArrayList<>();

    @Builder.Default
    private List<Address> addresses = new ArrayList<>();

    @Builder.Default
    private List<Court> courts = new ArrayList<>();

    private String logo;

    private String primaryColor;

    private String secondaryColor;

    @NotNull
    private String idCompany;

}
