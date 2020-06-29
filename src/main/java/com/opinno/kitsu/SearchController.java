/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opinno.kitsu;

import com.opinno.kitsu.Http.HttpGetRequest;
import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author guilogar
 */
@Controller
public class SearchController
{
    @RequestMapping("/search")
    public ModelAndView search(@RequestParam("data") String searchData) throws IOException, JSONException
    {
        ArrayList<Object> animus = new ArrayList<>();
        
        String url = "https://kitsu.io/api/edge/anime?filter[text]=" + searchData;
        
        HttpGetRequest hgr = new HttpGetRequest(url);
        String result = hgr.makeHttpRequest();
        JSONObject o = new JSONObject(result);
        JSONArray data = o.getJSONArray("data");

        for(int i = 0; i < data.length(); i++)
        {
            JSONObject oo = data.getJSONObject(i);
            animus.add(oo);
        }

        url = "https://kitsu.io/api/edge/manga?filter[text]=" + searchData;
        
        hgr = new HttpGetRequest(url);
        result = hgr.makeHttpRequest();
        o = new JSONObject(result);
        data = o.getJSONArray("data");

        for(int i = 0; i < data.length(); i++)
        {
            JSONObject oo = data.getJSONObject(i);
            animus.add(oo);
        }
        
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("animus", animus);
        return mv;
    }
}
