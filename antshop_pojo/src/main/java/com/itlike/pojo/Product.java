package com.itlike.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
public class Product implements Serializable {
    private Long id;

    private String name;

    private Boolean ishot;

    private Secondcategory secondCategory;/*所属二级菜单*/

    private BigDecimal marketprice;

    private BigDecimal shopprice;

    private String image;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date onlinetime;

    private Boolean state;

    private String description;


}