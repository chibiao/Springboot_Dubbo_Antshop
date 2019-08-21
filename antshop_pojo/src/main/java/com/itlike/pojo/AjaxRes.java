package com.itlike.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
/**
 * 用于返回ajax请求返回的回调数据
 */
public class AjaxRes implements Serializable {
    private String msg;
    private boolean success;
}
