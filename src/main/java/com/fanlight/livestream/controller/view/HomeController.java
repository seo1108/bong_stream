package com.fanlight.livestream.controller.view;

import com.fanlight.livestream.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class HomeController {
    @Inject
    private TestService service;

    @RequestMapping(value = {"/", "/test",}, method = {RequestMethod.GET})
    public String test(@RequestParam Map<String,Object> params,
                       Model model) {

        try {
            Map<String, Object> map = service.getConcertInfo(params);

            model.addAttribute("concertInfo", map);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return "test";
    }

}
