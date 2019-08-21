package com.itlike.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter@Setter@ToString
public class User implements Serializable {
    private Long id;

    private String username;

    private String password;

    private String name;

    private String email;

    private String phone;

    private String addr;

    private Boolean state;

}