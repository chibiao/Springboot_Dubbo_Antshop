package com.itlike.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter@Setter@ToString
public class Admin implements Serializable {
    private Long id;

    private String anum;

    private String name;

    private String password;

    private List<Role> roles =new ArrayList<>();

    private List<Permission> permissions = new ArrayList<>();
}