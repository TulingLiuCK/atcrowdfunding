package com.lck.crowd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Auth {
    private Integer id;

    private String name;

    private String title;

    private Integer categoryId;

}