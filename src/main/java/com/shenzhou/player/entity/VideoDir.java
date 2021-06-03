package com.shenzhou.player.entity;

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
@TableName("video_dir")
public class VideoDir implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "uuid")
    private String uuid;

    @TableField("name")
    private String name;

    @TableField("path")
    private String path;

}
