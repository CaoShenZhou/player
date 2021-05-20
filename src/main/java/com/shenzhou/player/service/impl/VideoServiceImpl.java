package com.shenzhou.player.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shenzhou.player.entity.Video;
import com.shenzhou.player.mapper.VideoMapper;
import com.shenzhou.player.service.IVideoService;
import org.springframework.stereotype.Service;

/**
 * Video 服务实现类
 *
 * @author Mr.Cao
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements IVideoService {

}
