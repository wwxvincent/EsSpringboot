package com.vincent.esspringboot.controller;

import com.vincent.esspringboot.entity.UserEntity;
import com.vincent.esspringboot.repository.UserRepository;
import com.vincent.esspringboot.service.IUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
@Api(tags = "User Management", description = "Operations pertaining to User management")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/selectAll")
    public List<UserEntity> selectAll() {
        return userService.selectAll();
    }

//    @GetMapping("/initData")
//    public void initData() {
//        List<UserEntity> list = userService.selectAll();
//
//        for (UserEntity userEntity : list) {
//            userRepository.save(userEntity);
//        }
//    }
//
//    @PostMapping("/deleteAll")
//    public void deleteAll() {
//        songRepository.deleteAll();
//    }
//


//    @PostMapping("/searchName")
//    public List<SongEntity> searchByName(@RequestBody String keyword) {
//        return songRepository.findByName(keyword);
//    }
//
//    @PostMapping("/searchSinger")
//    public List<SongEntity> searchBySinger(@RequestBody String keyword) {
//        return songRepository.findBySinger(keyword);
//    }

//    @PostMapping("/searchNameFuzzy")
//    public List<SongEntity> searchByNameFuzzy(@RequestBody String keyword) {
//        return songRepository.findByNameFuzzy(keyword);
//    }
}
