package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * author : 张京斗
 * create_date : 2019/12/6 11:20
 * version : 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "e_dept")
public class Deplement implements Serializable {
    @Id
    private String id;
    private String number;
    private String name;
    private Integer count;
}
