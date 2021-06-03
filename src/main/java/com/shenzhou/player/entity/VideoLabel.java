package com.shenzhou.player.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Mr.Cao
 **/
@Data
@TableName("video_label")
public class VideoLabel implements Serializable {

    @TableField("id")
    private String id;

    @TableField("video_id")
    private String videoId;

    @TableField("label_id")
    private String labelId;

}
