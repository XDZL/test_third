package com.spring.mvc;

import com.dj.web.model.User;
import com.dj.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/user")
public class DefaultController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/mybatis")
    public ModelAndView indexView(@RequestParam String id, HttpServletResponse response){
        response.setCharacterEncoding("utf-8");
        User user = userService.getUserUsingId(id);
        ModelAndView modelAndView = new ModelAndView("mybatis");
        modelAndView.addObject("user",user);
        return modelAndView;
    }
    @RequestMapping(value = "/show")
    public String show(){
        return "show";
    }
    @RequestMapping(value = "/add", method=RequestMethod.POST)
    public String add(User user,
                      ModelMap model){
        System.out.println(user);
        model.addAttribute("Command",user.getCommand());
        model.addAttribute("Description",user.getDescription());
        model.addAttribute("Content",user.getContent());
        userService.ininsertUser(user);
        return "result";
    }
}
