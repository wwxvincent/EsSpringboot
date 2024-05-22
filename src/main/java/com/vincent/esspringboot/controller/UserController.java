package com.vincent.esspringboot.controller;

import com.vincent.esspringboot.entity.UserEntity;
import com.vincent.esspringboot.repository.UserRepository;
import com.vincent.esspringboot.service.IUserService;
import com.vincent.esspringboot.vo.SongSearchVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.search.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    @GetMapping("/initData")
    public void initData() {
        List<UserEntity> list = userService.selectAll();

        for (UserEntity userEntity : list) {
            userRepository.save(userEntity);
        }
    }

    @PostMapping("/deleteAll")
    public void deleteAll() {
        userRepository.deleteAll();
    }




    @ApiOperation("年龄排序")
    @PostMapping("/searchUserSort")
    public ResponseEntity<Page<UserEntity>> getUsersSortedByAgeAsc(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<UserEntity> users = userService.getUsersSortedByAgeAsc(page, size);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
