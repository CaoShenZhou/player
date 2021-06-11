package com.shenzhou.player.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Mr.Cao
 **/
@Data
@TableName("video_label")
public class VideoLabel implements Serializable {

    @TableId("id")
    private String id;

    @TableField("video_id")
    private String videoId;

    @TableField("label_id")
    private String labelId;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
