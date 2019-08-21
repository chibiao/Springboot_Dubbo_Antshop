package com.itlike.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class Cart implements Serializable {
    private long uid;  //用户di
    private int id;    //商品di
    private int count; //数量
}
