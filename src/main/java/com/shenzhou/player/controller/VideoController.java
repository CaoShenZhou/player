package com.shenzhou.player.controller;


import com.shenzhou.player.config.NonStaticResourceHttpRequestHandler;
import com.shenzhou.player.entity.Video;
import com.shenzhou.player.service.IVideoDirService;
import com.shenzhou.player.service.IVideoService;
import com.shenzhou.player.util.VideoUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
public class VideoController {

    @Resource
    private IVideoService iVideoService;

    @GetMapping("/list")
    public List<Video> getList() {
        return iVideoService.list();
    }

    @Test
    public void aaa() throws IOException, EncoderException {
        String path = "D:\\迅雷下载";
        List<File> fileList = (List<File>) FileUtils.listFiles(new File(path), null, false);
        for (File file : fileList) {
            Video video = new Video();
            video.setName(file.getName());
            String md5 = DigestUtils.md5Hex(new FileInputStream(file.getAbsolutePath()));
            video.setMd5(md5);
            String format = FilenameUtils.getExtension(file.getName());
            video.setFormat(format);
            VideoUtil.getInfo(file.getAbsolutePath());
            break;
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
