package com.shenzhou.player.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.shenzhou.player.config.NonStaticResourceHttpRequestHandler;
import com.shenzhou.player.entity.Video;
import com.shenzhou.player.entity.VideoLabel;
import com.shenzhou.player.service.IVideoDirService;
import com.shenzhou.player.service.IVideoLabelService;
import com.shenzhou.player.service.IVideoService;
import com.shenzhou.player.util.VideoUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ws.schild.jave.EncoderException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;

/**
 * SQL主机 前端控制器
 *
 * @author Mr.Cao
 */
@Validated
@RestController
@RequestMapping("/video")
@SpringBootTest
public class VideoController {

    @Resource
    private IVideoService iVideoService;

    @Resource
    private IVideoLabelService iVideoLabelService;

    @GetMapping("/list")
    public List<Video> getList() {
        return iVideoService.list();
    }

    @Test
    public void bb() {
        System.out.println(RandomStringUtils.randomAlphanumeric(10));
    }

    @Test
    public void aaa() {
        String path = "E:\\电影\\中字";
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
                String newPath = file.getParent() + File.separator + newName;
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
                        boolean getVideoInfoResult = VideoUtil.getInfo(newPath, video);
                        // 如果获取视频成功
                        if (getVideoInfoResult) {
                            iVideoService.updateById(video);
                            VideoLabel videoLabel = new VideoLabel();
                            videoLabel.setVideoId(id);
                            videoLabel.setLabelId("PJJh0yom3Z");
                            iVideoLabelService.add(videoLabel);
                        }
                    } catch (IOException e) {
                        System.out.println("文件:" + newPath + ",获取MD5值失败");
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    @Autowired
    private NonStaticResourceHttpRequestHandler nonStaticResourceHttpRequestHandler;

    @GetMapping("/video")
    public void videoPreview(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //假如我把视频1.mp4放在了static下的video文件夹里面
        //sourcePath 是获取resources文件夹的绝对地址
        //realPath 即是视频所在的磁盘地址

        Path filePath = Paths.get("C:\\Users\\Mr_Cao\\Desktop\\720(5).mp4");
        if (Files.exists(filePath)) {
            String mimeType = Files.probeContentType(filePath);
            response.setContentType(mimeType);
            request.setAttribute(NonStaticResourceHttpRequestHandler.ATTR_FILE, filePath);
            nonStaticResourceHttpRequestHandler.handleRequest(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        }
    }


}
