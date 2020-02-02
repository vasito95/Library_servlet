package org.library.controller.model.entity;


import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
@EqualsAndHashCode

public class Book {
    private Long id;
    private LocalDate inUseBy;
    private Boolean isInUse;
    private String name;

    private List<String> authors;
    private String attribute;

    private User user;

}
