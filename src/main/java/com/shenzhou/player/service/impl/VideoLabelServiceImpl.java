package com.shenzhou.player.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shenzhou.player.entity.VideoLabel;
import com.shenzhou.player.mapper.VideoLabelMapper;
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
    public boolean add(VideoLabel videoLabel) {
        while (true) {
            String id = RandomStringUtils.randomAlphanumeric(10);
            if (null == this.getBaseMapper().selectById(id)) {
                videoLabel.setId(id);
                return this.save(videoLabel);
            }
        }
    }

}
