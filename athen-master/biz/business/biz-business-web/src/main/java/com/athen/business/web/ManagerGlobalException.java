/**
 * 处理全局异常的控制类. 如果要自定义错误处理类<br/>
 * <pre>
 * &#064;RestController
 * public class IndexController implements ErrorController {
 *
 *   private static final String PATH = "/error";
 *
 *   &#064;RequestMapping(value = PATH)
 *   public String error() {
 *     return "Error handling";
 *   }
 *
 *   &#064;Override
 *   public String getErrorPath() {
 *     return PATH;
 *   }
 * }</pre>
 *
 * @see org.springframework.boot.autoconfigure.web.ErrorController
 * @see org.springframework.boot.autoconfigure.web.ErrorProperties
 * @see org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration
 */
package com.athen.business.web;

import com.xtxk.core.exception.ForbiddenException;
import com.xtxk.core.exception.NotLoginException;
import com.xtxk.core.exception.ServiceException;
import com.xtxk.core.json.JsonResult;
import com.xtxk.core.util.A;
import com.xtxk.core.util.LogUtil;
import com.xtxk.core.util.RequestUtils;
import com.xtxk.core.util.U;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import static com.xtxk.core.json.JsonResult.fail;
import static com.xtxk.core.json.JsonResult.notLogin;

@ControllerAdvice
public class ManagerGlobalException {


    @Value("${online:false}")
    private boolean online;

    @ExceptionHandler(ServiceException.class)
    public JsonResult serviceException(ServiceException e) {
        LogUtil.LOG.error(e.getMessage());
        return fail(e.getMessage());
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public JsonResult serviceException(SQLIntegrityConstraintViolationException e) {
        LogUtil.LOG.error(e.getMessage());
        return fail(e.getMessage());
    }


    /**
     * 校验必填参数拦截处理
     *
     * @param exception 错误信息集合
     * @return 错误信息
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JsonResult validationBodyException(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        return fail(result.getFieldError().getDefaultMessage());
    }

    /**
     * 参数类型转换错误 {@link HttpMessageConversionException}
     *
     * @param e 错误
     * @return 错误信息
     */
    @ExceptionHandler(HttpMessageConversionException.class)
    public JsonResult parameterTypeException(HttpMessageConversionException e) {
        LogUtil.LOG.error(e.getMessage());
        return fail("请求参数类型有误: " + e.getMessage());
    }

    /**
     * 请求时没登录
     */
    @ExceptionHandler(NotLoginException.class)
    public JsonResult noLogin(NotLoginException e, HttpServletResponse response) throws IOException {
        if (LogUtil.ROOT_LOG.isDebugEnabled())
            LogUtil.ROOT_LOG.debug("请求 ({}) 时未登录 {}", RequestUtils.getUrl(), e.getMessage());
        return notLogin();
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public void notSupported(HttpRequestMethodNotSupportedException e, HttpServletResponse response) throws IOException {
        if (LogUtil.ROOT_LOG.isDebugEnabled())
            LogUtil.ROOT_LOG.debug("方法不支持: " + e.getMessage());

        fail("不支持此种请求方式!" + (online ? U.EMPTY :
                String.format(" 当前方式(%s), 支持方式(%s)", e.getMethod(), A.toStr(e.getSupportedMethods())))).toJson(response);
    }

    /**
     * 请求没有相应的处理
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public void forbidden(NoHandlerFoundException e, HttpServletResponse response) throws IOException {
        if (LogUtil.ROOT_LOG.isDebugEnabled())
            LogUtil.ROOT_LOG.debug(e.getMessage(), e);
        fail("404").toJson(response);
    }

    /**
     * 请求时没权限
     */
    @ExceptionHandler(ForbiddenException.class)
    public void forbidden(ForbiddenException e, HttpServletResponse response) throws IOException {
        if (LogUtil.ROOT_LOG.isErrorEnabled())
            LogUtil.ROOT_LOG.error("没权限: " + e.getMessage());
        if (RequestUtils.isAjaxRequest()) {
            fail(e.getMessage()).toJson(response);
        } else {
            response.sendRedirect("/403");
        }
    }

    /**
     * 上传文件太大
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public void notFound(MaxUploadSizeExceededException e, HttpServletResponse response) throws IOException {
        if (LogUtil.ROOT_LOG.isDebugEnabled())
            LogUtil.ROOT_LOG.debug("文件太大: " + e.getMessage(), e);
        // 右移 20 位相当于除以两次 1024, 正好表示从字节到 Mb
        fail("上传文件太大! 请保持在 " + (e.getMaxUploadSize() >> 20) + "M 以内").toJson(response);
    }

    /**
     * 未知的所有其他异常
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView exception(Exception e, HttpServletResponse response) throws IOException {
        if (LogUtil.ROOT_LOG.isErrorEnabled())
            LogUtil.ROOT_LOG.error("发生错误: " + e.getMessage(), e);
        ModelAndView model = new ModelAndView();
        if (RequestUtils.isAjaxRequest()) {
            fail(online ? "请求出现问题, 我们会尽快处理好" : e.getMessage()).toJson(response);
        } else {
            model.addObject("error", e.getMessage());
            model.setViewName("500");
        }
        return model;
    }

}
