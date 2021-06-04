package com.shenzhou.player.dto.video;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author Mr.Cao
 **/
@Data
public class UpdateVideoLikeDTO {

    @Length(min = 10, max = 10, message = "id 长度应为{max}位;")
    private String id;

    @NotNull(message = "like 不可为空;")
    private Boolean like;

}
