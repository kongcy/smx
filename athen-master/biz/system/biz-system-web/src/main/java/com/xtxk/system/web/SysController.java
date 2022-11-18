package com.xtxk.system.web;

import com.xtxk.core.util.IDCardUtil;
import com.xtxk.system.api.model.SystemConfig;
import com.xtxk.system.api.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created on 2016/8/26.
 */
@Controller
@RequestMapping(value = "/system/api")
public class SysController {

    @Autowired
    private SystemService systemService;


    @RequestMapping(value = "/config/create", method = RequestMethod.POST)
    public String create(SystemConfig config) {
        return "redirect:/system/config";
    }

    @RequestMapping(value = "/config/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        model.addAttribute("action", "update");
        // model.addAttribute("config",scs.findOne(id));
        return "system/configForm";
    }

    @RequestMapping(value = "/config/update", method = RequestMethod.POST)
    public String update(@Validated SystemConfig config, RedirectAttributes rs) {
        //  scs.saveConfig(config);
        rs.addFlashAttribute("message", "系统参数更新成功");
        return "redirect:/system/config";
    }

    @RequestMapping(value = "/config/check")
    @ResponseBody
    public Boolean checkKey(String conKey) {
        // return scs.checkKey(conKey);
        return false;
    }

    @RequestMapping(value = "/config/delete/{id}")
    public String deleteConfig(@PathVariable Long id, RedirectAttributes rs) {
        // scs.delete(id);
        rs.addFlashAttribute("message", "成功删除");
        return "redirect:/system/config";
    }

    public static void main(String[] args){
        String s="421122198609190052";
      //  s=s.substring(s.length()-6);
        boolean ls=IDCardUtil.isValidCard(s);
        System.out.println(ls);
    }
}
