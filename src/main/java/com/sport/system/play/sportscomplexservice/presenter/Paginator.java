package com.sport.system.play.sportscomplexservice.presenter;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Paginator {
    private Integer totalPages;
    private Long totalElements;
    private Set data;
}
