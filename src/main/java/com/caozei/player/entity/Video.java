package com.caozei.player.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Mr.Cao
 **/
@Data
@TableName("video")
public class Video implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "uuid", type = IdType.ASSIGN_UUID)
    private String uuid;

    @TableField("name")
    private String name;

    @TableField("md5")
    private String md5;

    @TableField("format")
    private String format;

    @TableField("duration")
    private String duration;

    @TableField("resolution")
    private String resolution;

}
