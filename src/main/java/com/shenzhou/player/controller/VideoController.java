package com.shenzhou.player.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.shenzhou.player.config.NonStaticResourceHttpRequestHandler;
import com.shenzhou.player.dto.video.UpdateVideoLikeDTO;
import com.shenzhou.player.entity.Label;
import com.shenzhou.player.entity.Video;
import com.shenzhou.player.entity.VideoLabel;
import com.shenzhou.player.service.ILabelService;
import com.shenzhou.player.service.IVideoLabelService;
import com.shenzhou.player.service.IVideoService;
import com.shenzhou.player.util.VideoUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Mr.Cao
 **/
@Validated
@RestController
@RequestMapping("/video")
@SpringBootTest
public class VideoController {

    @Resource
    private IVideoService iVideoService;

    @Resource
    private ILabelService iLabelService;

    @Resource
    private IVideoLabelService iVideoLabelService;

    @Resource
    private NonStaticResourceHttpRequestHandler nonStaticResourceHttpRequestHandler;

    @GetMapping("/list")
    public List<Video> getVideoList() {
        return iVideoService.list();
    }

    @PostMapping("/update-like")
    public boolean updateVideoLike(@Validated @RequestBody UpdateVideoLikeDTO dto) {
        Video video = new Video();
        BeanUtils.copyProperties(dto, video);
        return iVideoService.updateById(video);
    }

    @Test
    public void bb() {
        System.out.println(new File("F:\\电影\\在归档\\ri-chang-wan-nai-zi-18les-zhao-mei-mei-liao.mp4").getParent());
    }

    @Test
    public void aaa() {
        String path = "F:/电影/在归档";
        List<File> fileList = (List<File>) FileUtils.listFiles(new File(path), null, false);
        for (int i = 0; i < fileList.size(); i++) {
            System.out.println("总文件个数:" + fileList.size() + ",正在处理第" + (i + 1) + "个文件.");
            File file = fileList.get(i);
            String extension = FilenameUtils.getExtension(file.getName());
            Video video = new Video();
            if (iVideoService.add(video)) {
                String id = video.getId();
                System.out.println(id);
                String newName = id + "." + extension;
//                String newPath = file.getParent() + File.separator + newName;
                String newPath = "F:/电影/好了/" + newName;
                // 如果改名成功
                boolean renameResult = file.renameTo(new File(newPath));
                if (renameResult) {
                    video.setName(newName);
                    String md5 = "";
                    try {
                        md5 = DigestUtils.md5Hex(new FileInputStream(newPath));
                        video.setMd5(md5);
                        video.setFormat(extension);
                        video.setSize(new File(newPath).length());
                        video.setLike(false);
                        boolean getVideoInfoResult = VideoUtil.getInfo(newPath, video);
                        // 如果获取视频成功
                        if (getVideoInfoResult) {
                            iVideoService.updateById(video);
//                            VideoLabel videoLabel = new VideoLabel();
//                            videoLabel.setVideoId(id);
//                            videoLabel.setLabelId("PJJh0yom3Z");
//                            iVideoLabelService.add(videoLabel);
                        }
                    } catch (IOException e) {
                        System.out.println("文件:" + newPath + ",获取MD5值失败");
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    //    @GetMapping("/{id}")
//    public Map<String, Object> getVideoById(@PathVariable("id") String id) {
//        Video video = iVideoService.getById(id);
//        if (null != video) {
//            LambdaQueryWrapper<VideoLabel> videoLabelLambdaQueryWrapper = new LambdaQueryWrapper<>();
//            videoLabelLambdaQueryWrapper.eq(VideoLabel::getVideoId, id);
//            List<VideoLabel> videoLabelList = iVideoLabelService.list(videoLabelLambdaQueryWrapper);
//            List<Label> labelList = iLabelService.list();
//            Map<String, Object> data = new HashMap<>();
//            data.put("video", video);
//            data.put("labelList", labelList);
//            data.put("videoLabelList", videoLabelList);
//            return data;
//        }
//        return null;
//    }

    @GetMapping("/{id}")
    public String videoPreview(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Video video = iVideoService.getById(id);
        if (null != video) {
            Path filePath = Paths.get("H:/电影/已归档/" + video.getName());
            if (Files.exists(filePath)) {
                String mimeType = Files.probeContentType(filePath);
                if (!StringUtils.isEmpty(mimeType)) {
                    response.setContentType(mimeType);
                }
                request.setAttribute(NonStaticResourceHttpRequestHandler.ATTR_FILE, filePath);
                nonStaticResourceHttpRequestHandler.handleRequest(request, response);
            }
        } else {
//            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
            response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        }
        return "123";
    }


    @DeleteMapping("/{id}")
    public boolean delVideoById(@PathVariable("id") String id) {
        Video video = iVideoService.getById(id);
        if (null != video) {
            String filePath = "H:/电影/已归档/" + video.getName();
            try {
                FileUtils.forceDelete(new File(filePath));
                LambdaQueryWrapper<VideoLabel> videoLabelLambdaQueryWrapper = new LambdaQueryWrapper<>();
                videoLabelLambdaQueryWrapper.eq(VideoLabel::getVideoId, id);
                int videoLabelCount = iVideoLabelService.count(videoLabelLambdaQueryWrapper);
                if (videoLabelCount > 0) {
                    // 删除视频标签
                    LambdaUpdateWrapper<VideoLabel> videoLabelLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
                    videoLabelLambdaUpdateWrapper.eq(VideoLabel::getVideoId, id);
                    boolean delVideoLabelResult = iVideoLabelService.remove(videoLabelLambdaUpdateWrapper);
                    if (!delVideoLabelResult) {
                        return false;
                    }
                }
                return iVideoService.removeById(id);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


}
