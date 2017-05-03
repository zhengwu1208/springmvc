package com.song.ssm.controller;

import java.io.File;
import java.util.Date;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * Created by zhengwu on 2017/3/18.
 */
@Controller
public class FileUploadController implements ServletContextAware {
    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    private ServletContext      servletContext;

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    //这里@RequestParam("file")一定要加，不知道为什么
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String handleUploadData(String name, @RequestParam("file") CommonsMultipartFile file) {
        if (!file.isEmpty()) {
            String realPath = servletContext.getRealPath("/temp/");
            String fileName = file.getOriginalFilename();
            String fileType = fileName.substring(fileName.lastIndexOf("."));
            File newfile = new File(realPath,
                fileName.substring(0, fileName.lastIndexOf(".")) + new Date().getTime() + fileType);

            try {
                file.getFileItem().write(newfile);
                return "redirect:upload_ok.jsp";
            } catch (Exception e) {
                logger.error("上传文件异常", e);
            }
        }
        return "redirect:upload_error.jsp";
    }

}
