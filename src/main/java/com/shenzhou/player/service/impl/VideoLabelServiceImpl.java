package com.shenzhou.player.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shenzhou.player.entity.VideoDir;
import com.shenzhou.player.entity.VideoLabel;
import com.shenzhou.player.mapper.VideoDirMapper;
import com.shenzhou.player.mapper.VideoLabelMapper;
import com.shenzhou.player.service.IVideoDirService;
import com.shenzhou.player.service.IVideoLabelService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

/**
 * VideoLabel 服务实现类
 *
 * @author Mr.Cao
 */
@Service
public class VideoLabelServiceImpl extends ServiceImpl<VideoLabelMapper, VideoLabel> implements IVideoLabelService {

    @Override
    public boolean add(VideoLabel videoLabel){
        while (true) {
            LambdaQueryWrapper<VideoLabel> labelLambdaQueryWrapper = new LambdaQueryWrapper<>();
            String id = RandomStringUtils.randomAlphanumeric(10);
            labelLambdaQueryWrapper.eq(VideoLabel::getId, id);
            if (this.getBaseMapper().selectCount(labelLambdaQueryWrapper) == 0) {
                videoLabel.setId(id);
                return this.save(videoLabel);
            }
        }
    }

}
