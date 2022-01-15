package com.sport.system.play.sportscomplexservice.presenter;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class ComplexPresenter {
    @Id
    private String id;

    @NotNull
    private String name;

    @NotNull
    private String descriptionSmall;

    @NotNull
    private String descriptionLarge;

    @Builder.Default
    private List<ContactPresenter> contacts = new ArrayList<>();

    @Builder.Default
    private List<AddressPresenter> addresses = new ArrayList<>();

    @Builder.Default
    private List<CourtPresenter> courts = new ArrayList<>();

    private String logo;

    private String primaryColor;

    private String secondaryColor;

    @NotNull
    private String idCompany;

}
