package com.itlike.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter@Setter@ToString
public class Role implements Serializable {
    private Long rid;

    private String rnum;

    private String rname;

    private List<Permission> permissions = new ArrayList<>();
}