/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opinno.kitsu;

import com.opinno.kitsu.Http.HttpGetRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author guilogar
 */
@Controller
public class AnimeController
{
    @RequestMapping("/anime")
    public ModelAndView anime(HttpSession session) throws IOException, JSONException
    {
        @SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

        if (messages == null)
        {
            messages = new ArrayList<>();
        }
        
        for(String s : messages)
        {
            System.out.println(s);
        }
        
        ArrayList<Object> animus = new ArrayList<>();
        
        String url = "https://kitsu.io/api/edge/anime";
        int j = 0;
        do
        {
            HttpGetRequest hgr = new HttpGetRequest(url);
            String result = hgr.makeHttpRequest();
            JSONObject o = new JSONObject(result);
            JSONArray data = o.getJSONArray("data");

            for(int i = 0; i < data.length(); i++)
            {
                JSONObject oo = data.getJSONObject(i);
                animus.add(oo);
            }

            JSONObject links = o.getJSONObject("links");
            
            url = links.getString("next");
        } while(++j < 1);
        
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("animus", animus);
        return mv;
    }
    
    @RequestMapping("/animu/{id}")
    public ModelAndView animu(@PathVariable("id") long id) throws IOException, JSONException
    {
        ArrayList<Object> genres = new ArrayList<>();
        
        String url = "https://kitsu.io/api/edge/anime/" + id;
        HttpGetRequest hgr = new HttpGetRequest(url);
        String result = hgr.makeHttpRequest();
        JSONObject o = new JSONObject(result);
        
        JSONObject anime = o.getJSONObject("data");
        
        url = "https://kitsu.io/api/edge/anime/" + id + "/genres";
        hgr = new HttpGetRequest(url);
        result = hgr.makeHttpRequest();
        o = new JSONObject(result);
        JSONArray gen = o.getJSONArray("data");
        
        for(int i = 0; i < gen.length(); i++)
        {
            genres.add(gen.getJSONObject(i));
        }
        
        ModelAndView mv = new ModelAndView("animu");
        mv.addObject("anime", anime);
        mv.addObject("genres", genres);
        return mv;
    }
}
