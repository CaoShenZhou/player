package com.shenzhou.player.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Mr.Cao
 **/
@Data
@TableName("video")
public class Video implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("id")
    private String id;

    @TableField("name")
    private String name;

    @TableField("md5")
    private String md5;

    @TableField("format")
    private String format;

    @TableField("size")
    private Long size;

    @TableField("duration")
    private Long duration;

    @TableField("resolution")
    private String resolution;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
