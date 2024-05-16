package com.vincent.esspringboot.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@TableName("song")
@Data
@Document(indexName = "songs")
public class SongEntity {

    @Id
    @Field(type= FieldType.Keyword)
    private String id;

    /**
     * 歌曲名
     */
    @Field(type= FieldType.Text, analyzer = "ik_max_word")
    private String name;

    /**
     * 歌手
     */
    @Field(type= FieldType.Text, analyzer = "ik_max_word")
    private String singer;

    /**
     * 描述信息
     */
    @Field(type= FieldType.Text, analyzer = "ik_max_word")
    private String note;

    /**
     * 歌曲文件
     */
    private String url;

    /**
     * 歌曲文件是否存在/是否已上传
     */
    @Field(type= FieldType.Long)
    private Integer uploaded;

//    /**
//     * 最后一次修改时间
//     */
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//    private LocalDateTime lastUpdateTime;
}
