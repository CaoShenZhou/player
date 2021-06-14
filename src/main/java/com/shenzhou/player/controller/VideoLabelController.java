package com.shenzhou.player.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.shenzhou.player.dto.video.UpdateVideoLikeDTO;
import com.shenzhou.player.dto.videolabel.UpdateVideoLabelDTO;
import com.shenzhou.player.entity.Video;
import com.shenzhou.player.entity.VideoLabel;
import com.shenzhou.player.service.IVideoLabelService;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Mr.Cao
 **/
@Validated
@RestController
@RequestMapping("/video-label")
public class VideoLabelController {

    @Resource
    private IVideoLabelService iVideoLabelService;

    @PostMapping("/add")
    public boolean addVideoLabel(@Validated @RequestBody UpdateVideoLabelDTO dto) {
        VideoLabel videoLabel = new VideoLabel();
        BeanUtils.copyProperties(dto, videoLabel);
        // 查询该标签是否已经存着
        LambdaQueryWrapper<VideoLabel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(VideoLabel::getVideoId, videoLabel.getVideoId())
                .eq(VideoLabel::getLabelId, videoLabel.getLabelId());
        if (iVideoLabelService.list(queryWrapper).size() == 0) {
            return iVideoLabelService.add(videoLabel);
        }
        return false;
    }

    @PostMapping("/del")
    public boolean delVideoLabel(@Validated @RequestBody UpdateVideoLabelDTO dto) {
        VideoLabel videoLabel = new VideoLabel();
        BeanUtils.copyProperties(dto, videoLabel);
        LambdaUpdateWrapper<VideoLabel> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(VideoLabel::getVideoId, videoLabel.getVideoId())
                .eq(VideoLabel::getLabelId, videoLabel.getLabelId());
        return iVideoLabelService.remove(updateWrapper);
    }

}
