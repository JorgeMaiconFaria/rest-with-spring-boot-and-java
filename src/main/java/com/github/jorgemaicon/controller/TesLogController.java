package com.github.jorgemaicon.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test/v1")
public class TesLogController {
    private Logger logger = LoggerFactory.getLogger(TesLogController.class.getName());

    @GetMapping
    public String testLog() {
        logger.info("Log info");

        return "Deram certos os logs";
    }
}
