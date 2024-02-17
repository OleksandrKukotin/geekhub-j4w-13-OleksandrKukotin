package org.geekhub.hw11.controller;

import org.geekhub.hw11.service.HistoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HistoryController {

    private final HistoryService service;

    public HistoryController(HistoryService service) {
        this.service = service;
    }

    @GetMapping("/history")
    public ModelAndView history(ModelAndView modelAndView) {
        modelAndView.setViewName("history");
        modelAndView.addObject("historyEntries", service.getAllEntries());
        return modelAndView;
    }
}
