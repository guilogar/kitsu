/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opinno.kitsu;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 *
 * @author guilogar
 */
@Controller
public class KitsuController {
    
    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello(@RequestParam(name="name", required=false, defaultValue="Stranger") String name)
    {
        return "holi caracoli";
    }
    
    @RequestMapping("/")
    public String index(HttpServletRequest request)
    {
        @SuppressWarnings("unchecked")
        List<String> messages = (List<String>) request.getSession().getAttribute("MY_SESSION_MESSAGES");
        
        if (messages == null)
        {
            messages = new ArrayList<>();
            request.getSession().setAttribute("MY_SESSION_MESSAGES", messages);
        }
        messages.add("perico perez");
        request.getSession().setAttribute("MY_SESSION_MESSAGES", messages);
        
        return "redirect:/anime";
    }
}