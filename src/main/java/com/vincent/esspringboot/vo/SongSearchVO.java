package com.vincent.esspringboot.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 5/21/24
 * @Description:
 */
@Data
public class SongSearchVO {

    @ApiModelProperty(value = "keyword")
    private String keyword;

    @ApiModelProperty(value = "song's name")
    private String name;

    @ApiModelProperty(value = "song's singer")
    private String singer;

    @ApiModelProperty(value = "song's note")
    private String note;
}
