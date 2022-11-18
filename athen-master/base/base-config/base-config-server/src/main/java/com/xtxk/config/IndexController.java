package com.xtxk.config;

import com.xtxk.config.model.CfgTable;
import com.xtxk.config.service.ConfigService;
import com.xtxk.core.json.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static com.xtxk.core.json.JsonResult.success;

/**
 * @author chenying
 * @date 2020-04-24 17:51
 * @time 17:51
 * @since 1.0.0
 **/
@Controller
@RequestMapping
public class IndexController {

    @Autowired
    private ConfigService configService;

    @RequestMapping(value = "/view/tableList")
    public JsonResult findTables(String application, String profile){
        List<CfgTable> configTablelist=configService.findByApplicationAndProfile(application,profile);
        return success("",configTablelist);
    }

    @RequestMapping(value = "/tableData")
    public String index(String application, String profile, Model model){
        model.addAttribute("application",application);
        model.addAttribute("profile",profile);
        return "index";
    }

}
