package com.sport.system.play.sportscomplexservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contact {

        private String name;

        private String phone;

        private String cellPhone;

        private String email;
}
