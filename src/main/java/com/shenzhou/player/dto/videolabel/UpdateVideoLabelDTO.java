package com.shenzhou.player.dto.videolabel;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Mr.Cao
 **/
@Data
public class UpdateVideoLabelDTO {

    @NotBlank(message = "videoId 不可为空;")
    @Length(min = 10, max = 10, message = "videoId 长度应为{max}位;")
    private String videoId;

    @NotBlank(message = "labelId 不可为空;")
    @Length(min = 10, max = 10, message = "labelId 长度应为{max}位;")
    private String labelId;

}
