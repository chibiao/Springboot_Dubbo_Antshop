package com.itlike.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter@Setter@ToString
public class Category implements Serializable {
    private Long id;

    private String name;

    List<Secondcategory> secondCategorylist = new ArrayList<>();
}