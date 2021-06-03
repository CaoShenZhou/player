package com.shenzhou.player.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shenzhou.player.entity.VideoLabel;

/**
 * VideoLabel 服务类
 *
 * @author Mr.Cao
 */
public interface IVideoLabelService extends IService<VideoLabel> {

    boolean add(VideoLabel videoLabel);

}
