package com.sport.system.play.sportscomplexservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Court {
    private String codeCourt;
    private String name;
    private String status;
}
