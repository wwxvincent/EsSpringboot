package com.vincent.esspringboot.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@TableName("user")
@Data
@Document(indexName = "users")
public class UserEntity {

    @Id
    @Field(type= FieldType.Keyword)
    private String id;

    /**
     * 用户名
     */
    @Field(type= FieldType.Text, analyzer = "ik_max_word")
    private String name;

    /**
     * 歌手
     */
    @Field(type= FieldType.Text, analyzer = "ik_max_word")
    private String email;

    @Field(type= FieldType.Long)
    private Integer memberShip;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date memberShipTime;
    /**
     * 描述信息
     */
    @Field(type= FieldType.Text, analyzer = "ik_max_word")
    private String note;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastUpdateTime;


//    /**
//     * 最后一次修改时间
//     */
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//    private LocalDateTime lastUpdateTime;
}

