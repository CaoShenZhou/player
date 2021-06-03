package com.shenzhou.player.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shenzhou.player.entity.Video;

/**
 * Video 服务类
 *
 * @author Mr.Cao
 */
public interface IVideoService extends IService<Video> {

    boolean add(Video video);

}
