package com.shenzhou.player.controller;


import com.shenzhou.player.entity.Video;
import com.shenzhou.player.service.IVideoService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

/**
 * SQL主机 前端控制器
 *
 * @author Mr.Cao
 */
@Validated
@RestController
@RequestMapping("/video")
public class VideoController {

    @Resource
    private IVideoService iVideoService;

    @GetMapping("/list")
    public List<Video> getList() {
        return iVideoService.list();
    }

}
