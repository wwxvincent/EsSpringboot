package com.vincent.esspringboot.controller;

import com.vincent.esspringboot.service.IIndexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 5/24/24
 * @Description:
 */

@RestController
@RequestMapping("api/index")
@Api(value = "index Management", description = "Operations for index management")
public class indexController {

    @Value("${download.synonymsPath}")
    private String SYNONYMS_PATH;
    @Autowired
    private IIndexService indexService;

    @ApiOperation("create new synonyms index via file")
    @PostMapping("/createIndexSynonyms")
    public boolean createIndexWithSynonyms() throws IOException {
        return indexService.createIndexWithSynonyms(SYNONYMS_PATH);
    }


}
