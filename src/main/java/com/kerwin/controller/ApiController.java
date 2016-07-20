package com.kerwin.controller;

import com.kerwin.utils.IPUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ApiController {
    private final static Logger log = LoggerFactory.getLogger(ApiController.class);

    /**
     * show all APIs
     */
    @RequestMapping("/api")
    public ModelAndView showAPI(HttpServletRequest request, ModelAndView mv) {
        String ip = IPUtils.getIpAddress(request);
        if (ip.startsWith("192.") || ip.equals("127.0.0.1")) {
            List<Func> list = new ArrayList<>();
            try {
                InputStream stream = this.getClass().getClassLoader().getResourceAsStream("api");
                List<String> lines = IOUtils.readLines(stream, "utf-8");
                Func f = new Func();
                for (int i = 0; i < lines.size(); i += 2) {
                    String line = lines.get(i);
                    log.info("" + i);
                    if (StringUtils.isBlank(line)) {
                        i--; // i+=2 in loop
                        continue;
                    }
                    if (line.startsWith("/")) {
                        f = new Func();
                        list.add(f);
                        // url
                        f.url(line);
                        // desc
                        f.desc(lines.get(i + 1));
//                    } else if (line.startsWith(".")) {
//                        f.addReturns(line.substring(1), lines.get(i + 1));
                    } else {
                        f.addParam(line, lines.get(i + 1));
                    }
                }
                log.info("file read succeed");
            } catch (IOException e) {
                log.error("/api", e);
            }
            mv.addObject("list", list);
            mv.setViewName("api");
            return mv;
        } else {
            mv.setViewName("error");
            return mv;
        }
    }

    class Func {
        String _url;
        String _desc;
        List<Param> params = new ArrayList<>();
        List<Param> returns = new ArrayList<>();

        public Func addParam(String name, String desc) {
            params.add(new Param(name, desc));
            return this;
        }

        public Func addReturns(String name, String desc) {
            returns.add(new Param(name, desc));
            return this;
        }

        public String getUrl() {
            return _url;
        }

        public Func url(String url) {
            this._url = url;
            return this;
        }

        public Func url(String url, String desc) {
            this._url = url;
            this._desc = desc;
            return this;
        }

        public String getDesc() {
            return _desc;
        }

        public Func desc(String desc) {
            this._desc = desc;
            return this;
        }

        public List<Param> getParams() {
            return params;
        }

        public Func params(List<Param> params) {
            this.params = params;
            return this;
        }

        public List<Param> getReturns() {
            return returns;
        }

        public Func returns(List<Param> returns) {
            this.returns = returns;
            return this;
        }
    }

    @Data
    @AllArgsConstructor
    class Param {
        String name;
        String desc;
    }
}
