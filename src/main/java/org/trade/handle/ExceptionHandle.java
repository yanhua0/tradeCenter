package org.trade.handle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.trade.dto.ExceptionResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@ControllerAdvice
public class ExceptionHandle {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    public ModelAndView handle(Exception e, HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.error("系统发生错误-------------------");
        ExceptionResult r = null;
        if (e instanceof RuntimeException) {
            if (e.getMessage() == null) {
                r = new ExceptionResult(2, "NullPointerException");
            } else {
                r = new ExceptionResult(-1, e.getMessage());
            }
        } else {
            r = new ExceptionResult(0, "未知错误---请联系管理员!错误信息："+e.getMessage());
        }
        ModelAndView mv = new ModelAndView();
        logger.error("错误信息：" + r.getErrorInfo());
        mv.addObject("message", r);
        mv.setViewName("error");
        return mv;

    }
}

