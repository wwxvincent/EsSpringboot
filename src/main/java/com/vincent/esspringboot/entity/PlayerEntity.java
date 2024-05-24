package com.vincent.esspringboot.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 5/22/24
 * @Description:
 */
@TableName("player")
@Data
@Document(indexName = "players")
public class PlayerEntity {
    @Id
    @Field(type= FieldType.Keyword)
    private String id;

    @Field(type= FieldType.Text)
    private String userId;

    @Field(type= FieldType.Text)
    private String songId;

    @Field(type= FieldType.Long)
    private Integer played;

    @Field(type= FieldType.Long , name = "favor")
    private Integer favor;

    @Field(type= FieldType.Text, analyzer = "ik_max_word")
    private String memo;
}
