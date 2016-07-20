package com.kerwin.controller.html;

import com.kerwin.condition.Condition;
import com.kerwin.model.Play;
import com.kerwin.service.PlayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Kerwin on 2016/5/9.
 * Play controller
 */
@Controller
public class PlayController {
    private static final Logger log = LoggerFactory.getLogger(PlayController.class);
    @Autowired
    private PlayService playService;

    @RequestMapping(value = "walk.html")
    public ModelAndView showPlayInfo(Condition condition, @PageableDefault(sort = {"date"}, direction = Sort.Direction.DESC) Pageable pageable) {
        ModelAndView mv = new ModelAndView("walk");
        Page<Play> plays = playService.findAll(condition, pageable);
//        List<Map<String, Object>> list = new ArrayList<>();
//        for (Play play : plays) {
//            list.add(play.getUser().toDetailMap(play.toMap()));
//        }
        mv.addObject("plays", plays);
//        mv.addObject("plays", new PageImpl<>(list, pageable, plays.getTotalElements()));
        return mv;
    }

    @RequestMapping(value = "play_history")
    @ResponseBody
    public String showHistory(Integer userId) {
        String result = playService.findByUser(userId).toString();
        System.err.println(result);
        return result;
//        SimpleData data = playService.findByUserId(userId);
//        JSONObject json = new JSONObject(data);
//        return json.toString();
    }
}
