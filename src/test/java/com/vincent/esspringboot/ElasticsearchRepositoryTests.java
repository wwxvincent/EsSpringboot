package com.vincent.esspringboot;

import com.vincent.esspringboot.entity.SongEntity;
import com.vincent.esspringboot.repository.SongRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class ElasticsearchRepositoryTests {

    @Autowired
    private SongRepository songRepository;

    @Test
    void testFindByName() {
        List<SongEntity> list = songRepository.findByName("雨爱");

        System.out.println("共查询到" + list.size() + "条记录。");

        for (SongEntity songEntity : list) {
            System.out.println(songEntity);
        }
    }
}
