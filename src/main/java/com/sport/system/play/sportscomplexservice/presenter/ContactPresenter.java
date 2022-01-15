package com.sport.system.play.sportscomplexservice.presenter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactPresenter {

        private String name;

        private String phone;

        private String cellPhone;

        private String email;
}
