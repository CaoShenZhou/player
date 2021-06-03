package com.shenzhou.player.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shenzhou.player.entity.Video;
import com.shenzhou.player.entity.VideoLabel;
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

    @Override
    public boolean add(Video video){
        while (true) {
            LambdaQueryWrapper<Video> labelLambdaQueryWrapper = new LambdaQueryWrapper<>();
            String id = RandomStringUtils.randomAlphanumeric(10);
            labelLambdaQueryWrapper.eq(Video::getId, id);
            if (this.getBaseMapper().selectCount(labelLambdaQueryWrapper) == 0) {
                video.setId(id);
                return this.save(video);
            }
        }
    }

}
