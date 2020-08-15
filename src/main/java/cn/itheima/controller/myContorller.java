package cn.itheima.controller;

import cn.itheima.pojo.User;
import cn.itheima.service.UserService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;


@Controller
public class myContorller {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public ModelAndView login(User user){
        ModelAndView mv = new ModelAndView();
        Boolean login = userService.login(user);
        if (login){
            mv.addObject("msg","登陆成功");
            mv.addObject("userA",user);
        }else{
            mv.addObject("msg","登陆失败");
        }
        mv.setViewName("/static/result.jsp");
        return null;
    }

    @RequestMapping("/download")
    public ResponseEntity<byte[]> download(String filename, HttpServletRequest req) throws IOException {
        ServletContext servletContext = req.getServletContext();
        //读取文件
        try (InputStream is = servletContext.getResourceAsStream("/WEB-INF/" + filename)) {
            //设置请求头
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.valueOf("application/octet-stream"));

            //文件名编个码
            String encodeFilename = UriUtils.encode(filename, "UTF-8");
//            headers.set("Content-Disposition", "attachment;filename=" +encodeFilename); // 该方式filename不起效
            headers.setContentDispositionFormData("attachment",encodeFilename);
            return new ResponseEntity<>(IOUtils.toByteArray(is), headers, HttpStatus.OK);
        }
    }
}