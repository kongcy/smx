package com.xtxk.config;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: chenying
 * Date: 2019-08-14
 * Time: 17:09
 * since: 1.0.0
 */
@RestController
@RequestMapping(value = "/api/v1")
public class DomeController {
    @RequestMapping(value = "/checkHeartbeat")
    public String dome(){
        return "服务正常!";
    }
}
