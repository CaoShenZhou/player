package com.shenzhou.player.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shenzhou.player.entity.VideoLabel;
import com.shenzhou.player.vo.videolabel.VideoLabelCount;

import java.util.List;
import java.util.Map;

/**
 * VideoLabel 服务类
 *
 * @author Mr.Cao
 */
public interface IVideoLabelService extends IService<VideoLabel> {

    boolean add(VideoLabel videoLabel);

    List<VideoLabelCount> countVideoLabel();

}
