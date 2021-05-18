package com.caozei.player.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caozei.player.entity.Video;
import com.caozei.player.mapper.VideoMapper;
import com.caozei.player.service.IVideoService;
import org.springframework.stereotype.Service;

/**
 * Video 服务实现类
 *
 * @author Mr.Cao
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements IVideoService {

}
