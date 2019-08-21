package com.itlike.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class PageBean implements Serializable {
    // 当前是那一页
    private Integer pageNo;
    // 共多少页
    private Integer totalPage;
    // 多少条记录
    private Integer totalSize;
    // 当前商品
    private List<Product> products = new ArrayList<>();
}
