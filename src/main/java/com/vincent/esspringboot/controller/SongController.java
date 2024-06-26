package com.vincent.esspringboot.controller;

import com.vincent.esspringboot.entity.SongEntity;
import com.vincent.esspringboot.repository.SongRepository;
import com.vincent.esspringboot.service.ISongService;
import com.vincent.esspringboot.vo.SongSearchVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.search.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/song")
@Api(value = "Song Management", tags = {"Song management"})
public class SongController {

    @Autowired
    private ISongService songService;

    @Autowired
    private SongRepository songRepository;

    @ApiOperation("手动导入数据进es")
    @GetMapping("/initData")
    public void initData() {
        List<SongEntity> list = songService.selectAll();

        for (SongEntity songEntity : list) {
            songRepository.save(songEntity);
        }
    }

    @ApiOperation("手动删除es数据；empty es index")
    @PostMapping("/deleteAll")
    public void deleteAll() {
        songRepository.deleteAll();
    }

    @ApiOperation("showing mysql database, table songs")
    @GetMapping("/selectAll")
    public List<SongEntity> selectAll() {
        return songService.selectAll();
    }

    @ApiOperation("search by song's name")
    @PostMapping("/searchName")
    public List<SongEntity> searchSongsByName(@RequestBody SongSearchVO songSearchVO) {
        return songRepository.findByName(songSearchVO.getName());
    }

    @ApiOperation("search by song's singer")
    @PostMapping("/searchSinger")
    public List<SongEntity> searchSongsBySinger(@RequestBody SongSearchVO songSearchVO) {
        return songRepository.findBySinger(songSearchVO.getSinger());
    }


//    GET /songs/_search
//    {
//        "query": {
//        "multi_match": {
//            "query": "你",
//                    "fields": ["name", "singer^2", "note"]
//        }
//    }
//    }
    @ApiOperation("歌名，歌手，note，多字段查询，singer字段会有两倍权重")
    @PostMapping("/searchSongs1")
    public SearchResponse searchSongs(@RequestBody SongSearchVO songSearchVO) throws IOException {
        return songService.searchSongs(songSearchVO.getKeyword());
    }

//    GET /songs/_search
//    {
//        "query": {
//        "function_score": {
//            "query": { "match": { "name": "你" }},
//            "functions": [
//            {
//                "filter": { "match": { "singer": "许" }},
//                "weight": 2
//            }
//      ],
//            "score_mode": "multiply"
//        }
//    }
//    }
//当这个查询被执行时，Elasticsearch会首先找到name字段中包含"你"的所有文档。
// 然后，它会检查这些文档的singer字段是否包含"许"。如果包含，那么该文档的得分将被乘以2。
// 最后，Elasticsearch会按照修改后的得分从高到低返回这些文档。
    @ApiOperation("name，singer双字段查询，name为基础查询；singer若有匹配，权重乘2")
    @PostMapping("/searchSong2")
    public SearchResponse searchSongs2(@RequestBody SongSearchVO songSearchVO) throws IOException {
        return songService.searchSongs2(songSearchVO.getName(), songSearchVO.getSinger());
    }

//    *** Boosting Query ：Boosting Query允许你提高一个查询的得分，同时降低另一个查询的得分。
//    GET /songs/_search
//    {
//        "query": {
//        "boosting": {
//            "positive": {
//                "match": {
//                    "name": "你"
//                }
//            },
//            "negative": {
//                "term": {
//                    "singer": "不喜欢的歌手"
//                }
//            },
//            "negative_boost": 0.5
//        }
//    }
//    }
//    在这个例子中，我们提高了名称（name）字段中包含"你"的文档的得分，
//    同时降低了歌手（singer）字段中包含"不喜欢的歌手"的文档的得分（通过将其得分乘以0.5）。
@ApiOperation("name，singer双字段查询，name为基础查询；singer若有匹配，权重乘0.5")
@PostMapping("/searchSong3")
public SearchResponse searchSongs3(@RequestBody SongSearchVO songSearchVO) throws IOException {
    return songService.searchSongs3(songSearchVO.getName(), songSearchVO.getSinger());
}

//    *** Dis Max Query允许你指定多个查询子句，并基于这些子句为每个文档计算一个最大得分。
//    GET /songs/_search
//    {
//        "query": {
//        "dis_max": {
//            "queries": [
//            {
//                "match": {
//                "name": "你"
//            },
//                "boost": 2
//            },
//            {
//                "match": {
//                "note": "相关的歌词"
//            },
//                "boost": 1.5
//            }
//      ]
//        }
//    }
//    }
//    在这个例子中，我们搜索名称（name）字段中包含"你"的文档，并为这些文档分配了2倍的权重。
//    同时，我们也搜索歌词（note）字段中包含"相关的歌词"的文档，并为这些文档分配了1.5倍的权重。
//    最终，每个文档的得分将是这两个查询子句中的最高得分。

}
