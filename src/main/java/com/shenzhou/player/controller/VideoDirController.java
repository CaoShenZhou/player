package com.shenzhou.player.controller;


import com.shenzhou.player.entity.Video;
import com.shenzhou.player.entity.VideoDir;
import com.shenzhou.player.service.IVideoDirService;
import com.shenzhou.player.service.IVideoService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * SQL主机 前端控制器
 *
 * @author Mr.Cao
 */
@Validated
@RestController
@RequestMapping("/video-dir")
public class VideoDirController {

    @Resource
    private IVideoDirService iVideoDirService;

    @GetMapping("/list")
    public List<VideoDir> getList() {
        return iVideoDirService.list();
    }

}
