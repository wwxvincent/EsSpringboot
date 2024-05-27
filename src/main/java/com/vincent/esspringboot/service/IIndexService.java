package com.vincent.esspringboot.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.IOException;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 5/24/24
 * @Description:
 */
public interface IIndexService {
    boolean createIndexWithSynonyms(String path) throws IOException;

}
