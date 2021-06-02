package com.shenzhou.player.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shenzhou.player.entity.Video;
import com.shenzhou.player.mapper.VideoMapper;
import com.shenzhou.player.service.IVideoService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.HashSet;

/**
 * Video 服务实现类
 *
 * @author Mr.Cao
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements IVideoService {

}
