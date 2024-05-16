package com.vincent.esspringboot.controller;

import com.vincent.esspringboot.entity.SongEntity;
import com.vincent.esspringboot.repository.SongRepository;
import com.vincent.esspringboot.service.ISongService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/song")
@Api(tags = "Song Management", description = "Operations pertaining to Song management")
public class SongController {

    @Autowired
    private ISongService songService;

    @Autowired
    private SongRepository songRepository;

    @GetMapping("/initData")
    public void initData() {
        List<SongEntity> list = songService.selectAll();

        for (SongEntity songEntity : list) {
            songRepository.save(songEntity);
        }
    }

    @GetMapping("/selectAll")
    public List<SongEntity> selectAll() {
        return songService.selectAll();
    }

    @PostMapping("/searchName")
    public List<SongEntity> searchByName(@RequestBody String keyword) {
        return songRepository.findByName(keyword);
    }

    @PostMapping("/searchSinger")
    public List<SongEntity> searchBySinger(@RequestBody String keyword) {
        return songRepository.findBySinger(keyword);
    }

//    @PostMapping("/searchNameFuzzy")
//    public List<SongEntity> searchByNameFuzzy(@RequestBody String keyword) {
//        return songRepository.findByNameFuzzy(keyword);
//    }
}
