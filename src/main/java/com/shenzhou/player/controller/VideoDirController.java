package com.shenzhou.player.controller;


import com.shenzhou.player.dto.videodir.AddVideoDirDTO;
import com.shenzhou.player.entity.VideoDir;
import com.shenzhou.player.service.IVideoDirService;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/add")
    public Boolean add(
            @Validated @RequestBody AddVideoDirDTO dto) {
        VideoDir videoDir = new VideoDir();
        BeanUtils.copyProperties(dto, videoDir);
        return iVideoDirService.save(videoDir);
    }

}
