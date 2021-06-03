package com.shenzhou.player.util;

import com.shenzhou.player.entity.Video;
import org.springframework.web.multipart.MultipartFile;
import ws.schild.jave.EncoderException;
import ws.schild.jave.MultimediaInfo;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.VideoSize;

import java.io.File;

/**
 * 视频工具
 *
 * @author Mr.Cao
 **/
public class VideoUtil {

    /**
     * 获取视频信息
     *
     * @param path
     * @throws EncoderException
     */
    public static boolean getInfo(String path, Video video) {
        MultimediaObject multimediaObject = new MultimediaObject(new File(path));
        MultimediaInfo info = null;
        try {
            info = multimediaObject.getInfo();
        } catch (EncoderException e) {
            System.out.println("该文件处理异常:" + path);
            e.printStackTrace();
            return false;
        }
        VideoSize size = info.getVideo().getSize();
        video.setResolution(size.getWidth() + "×" + size.getHeight());
        video.setDuration(info.getDuration());
        return true;
    }
}
