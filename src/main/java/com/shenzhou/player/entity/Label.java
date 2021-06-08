package com.shenzhou.player.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Mr.Cao
 **/
@Data
@TableName("label")
public class Label implements Serializable {

    @TableField("id")
    private String id;

    @TableField("name")
    private String name;
}
