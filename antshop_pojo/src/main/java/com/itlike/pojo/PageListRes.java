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
/*
* 用于返回查询出来的数据条数和总数据数
* */
public class PageListRes implements Serializable {
    private Long total;
    private List<?> rows=new ArrayList<>();
}
