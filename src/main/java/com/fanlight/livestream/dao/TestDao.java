package com.fanlight.livestream.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Map;

@Mapper
@Repository
public class TestDao {
    @Autowired
    @Resource(name="sqlSession")
    private SqlSession sqlSession;

    private static String namespace = "test";


    public Map<String, Object> getConcertInfo(Map<String, Object> map) throws Exception {
        Map<String, Object> rmap = sqlSession.selectOne(namespace + ".getConcertInfo", map);
        return rmap;
    }

}
