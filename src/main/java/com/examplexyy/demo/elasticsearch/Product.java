package com.examplexyy.demo.elasticsearch;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author: xiongyayun
 * @date: 2022/2/19 12:21
 */
@Data
public class Product {

    private Integer id;
    private String name;
    private String desc;
    private double price;
    private String tags;
    private Timestamp date;
}
