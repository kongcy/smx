package com.xtxk.system.web;

import com.xtxk.core.annotation.NoTokenVerify;
import com.xtxk.core.json.JsonUtil;
import com.xtxk.core.json.ResultVo;
import com.xtxk.core.util.LogUtil;
import com.xtxk.core.util.RequestUtils;
import com.xtxk.core.util.U;
import com.xtxk.system.api.DTO.UserInfo;
import com.xtxk.system.api.service.ApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.xtxk.core.json.ResultVo.fail;
import static com.xtxk.core.json.ResultVo.success;

/**
 * 外部接口
 * @author chenying
 * @date 2022/11/3 2:43 PM
 * @time 2:43 PM
 * @since 1.0.0
 **/
@NoTokenVerify
@Api(tags = "智能柜上传数据服务")
@RestController
@RequestMapping(value = "/api/lockers")
public class ApiController {
    @Autowired
    private ApiService apiService;

    @PostMapping("stationusers/addusers")
    @ApiOperation(value = "上传用户")
    public ResultVo addUser(UserInfo userInfo){
        if(userInfo==null||U.isBlank(U.isBlank(userInfo.getSignstr()))){
            userInfo = RequestUtils.convertObj(UserInfo.class);
        }
        LogUtil.LOG.info("userInfo: "+ JsonUtil.toJson(userInfo));
      return apiService.uploadUserInfo(userInfo)>0?success("操作成功！"):fail("上传失败！");
    }
}
