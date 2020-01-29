package org.library.controller.model.entity;


import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString



public class User {

    private Long id;
    private String username;
    private String email;
    private String phoneNumber;
    private String password;

    private Boolean isActive;
    private Set<Role> roles;
    private Set<Book> books;




}
