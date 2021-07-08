package com.shenzhou.player.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shenzhou.player.entity.VideoLabel;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * VideoLabel Mapper 接口
 *
 * @author Mr.Cao
 **/
public interface VideoLabelMapper extends BaseMapper<VideoLabel> {

    @Select("SELECT labelInfo.name 'name', labelCount.count 'value'" +
            " FROM label 'labelInfo'" +
            " JOIN ( SELECT label_id, COUNT( id ) AS 'count' FROM video_label GROUP BY label_id ) 'labelCount'" +
            " WHERE labelInfo.id = labelCount.label_id" +
            " ORDER BY count DESC")
    List<Map<String, Object>> countVideoLabel();

}
