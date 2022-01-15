package com.sport.system.play.sportscomplexservice.presenter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressPresenter {

    private String address;

    private String latitud;

    private String longitud;
}
