/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opinno.kitsu;

import com.opinno.kitsu.Http.HttpGetRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.json.JSONException;
import org.json.JSONObject;
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
    public ModelAndView index(Map<String, Object> model) throws IOException, JSONException
    {
        ArrayList<Object> animus = new ArrayList<>();
        animus.add(null);
        animus.add(null);
        animus.add(null);
        animus.add(null);
        animus.add(null);
        animus.add(null);
        
        HttpGetRequest hgr = new HttpGetRequest("https://kitsu.io/api/edge/anime");
        String result = hgr.makeHttpRequest();
        JSONObject o = new JSONObject(result);
        
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("animus", animus);
        return mv;
    }
}