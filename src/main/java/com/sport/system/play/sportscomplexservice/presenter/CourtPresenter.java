package com.sport.system.play.sportscomplexservice.presenter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourtPresenter {
    private String codeCourt;
    private String name;
    private String status;
}
