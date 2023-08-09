package com.fanlight.livestream.service;

import com.fanlight.livestream.dao.TestDao;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Map;

@Service
public class TestService {
    @Inject
    private TestDao dao;

    public Map<String, Object> getConcertInfo(Map<String, Object> map) throws Exception {
        return dao.getConcertInfo(map);
    }
}
