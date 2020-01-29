package org.library.controller.model.entity;

import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter


public class Order {

    private Long id;
    private Long usrId;
    private Long bookId;
    private String userName;
    private String bookName;
    private LocalDate dateTo;
}
