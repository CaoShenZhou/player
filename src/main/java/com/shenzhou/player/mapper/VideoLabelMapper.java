package com.shenzhou.player.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shenzhou.player.entity.VideoLabel;
import com.shenzhou.player.vo.videolabel.VideoLabelCount;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * VideoLabel Mapper 接口
 *
 * @author Mr.Cao
 **/
public interface VideoLabelMapper extends BaseMapper<VideoLabel> {

    @Select("SELECT labelInfo.id, labelInfo.name 'name', IFNULL(labelCount.count,0) 'count'" +
            " FROM label 'labelInfo'" +
            " LEFT JOIN ( SELECT label_id, COUNT( id ) AS 'count' FROM video_label GROUP BY label_id ) 'labelCount'\n" +
            " ON labelInfo.id = labelCount.label_id" +
            " ORDER BY count DESC")
    List<VideoLabelCount> countVideoLabel();

}
