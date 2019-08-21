package com.itlike.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class Secondcategory implements Serializable {
    private Long id;

    private String name;

    private Category parent;
}