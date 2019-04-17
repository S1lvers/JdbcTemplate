package ru.sberdata.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    /**
     * На главной странице фронт-енд приложение на ReactJS
     *
     * @return
     */
    @RequestMapping("/")
    public String index() {
        return "forward:/static/index.html";
    }

}
