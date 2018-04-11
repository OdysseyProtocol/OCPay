package com.odwallet.rechage.controller;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class HeartController {


    protected org.slf4j.Logger logger = LoggerFactory.getLogger(HeartController.class);
    @RequestMapping(value = "/isAlive", method = RequestMethod.GET)
    public @ResponseBody

    String isAlive( ) {
        logger.warn("===================server started========");
        return  "ok";
    }




}
