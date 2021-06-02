package com.shenzhou.player.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author Mr.Cao
 **/
@Data
public class AddVideoDirDTO {
    @NotBlank(message = "name 不可为空;")
    @Length(min = 2, max = 15, message = "name 长度应为{min}-{max}位;")
    private String name;

    @NotBlank(message = "path 不可为空;")
    @Length(min = 1, message = "name 长度最小应为{min}位;")
    private String path;

}
