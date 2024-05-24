package com.vincent.esspringboot.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 5/22/24
 * @Description:
 */

@ApiModel(value = "信息展示")
@Data
public class displayInfoVO {

    private String id;

    private String name;

    private String userId;

    private Integer played;

    private Integer favor;

    private String songName;

    private String singer;

    private String note;

    private String memo;
}
