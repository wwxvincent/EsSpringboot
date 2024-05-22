package com.vincent.esspringboot.controller;

import com.vincent.esspringboot.entity.PlayerEntity;
import com.vincent.esspringboot.service.IPlayerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 5/22/24
 * @Description:
 */

@RestController
@RequestMapping("api/player")
@Api(value = "player Management", description = "Operations for player management")
public class playerController {

    @Autowired
    private IPlayerService playerService;

    @ApiOperation("showing mysql database, table player")
    @PostMapping ("/selectAll")
    public List<PlayerEntity> selectAll() {
        return playerService.selectAll();
    }

    @ApiOperation("showing mysql database, 用户所有歌曲信息")
    @PostMapping ("/selectAllByUser")
    public List<PlayerEntity> selectAllByUser(@RequestBody String id) {
        return playerService.selectAllByUser(id);
    }
}
