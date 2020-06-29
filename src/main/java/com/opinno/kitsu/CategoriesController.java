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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author guilogar
 */
@Controller
public class CategoriesController
{
    @RequestMapping("/categories")
    public ModelAndView categories() throws IOException, JSONException
    {
        ArrayList<Object> categories = new ArrayList<>();
        
        String url = "https://kitsu.io/api/edge/categories";
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
                
                if(!oo.getJSONObject("attributes").getString("image").equalsIgnoreCase("null"))
                {
                    categories.add(oo);
                }
            }

            JSONObject links = o.getJSONObject("links");
            
            url = links.getString("next");
        } while(categories.size() < 15);
        
        ModelAndView mv = new ModelAndView("categories");
        mv.addObject("categories", categories);
        return mv;
    }
    
    @RequestMapping("/category/{id}")
    public ModelAndView category(@PathVariable("id") long id) throws IOException, JSONException
    {
        ArrayList<Object> genres = new ArrayList<>();
        
        String url = "https://kitsu.io/api/edge/categories/" + id;
        HttpGetRequest hgr = new HttpGetRequest(url);
        String result = hgr.makeHttpRequest();
        JSONObject o = new JSONObject(result);
        
        JSONObject category = o.getJSONObject("data");
        
        /*
        url = "https://kitsu.io/api/edge/category/" + id + "/genres";
        hgr = new HttpGetRequest(url);
        result = hgr.makeHttpRequest();
        o = new JSONObject(result);
        JSONArray gen = o.getJSONArray("data");
        
        for(int i = 0; i < gen.length(); i++)
        {
            genres.add(gen.getJSONObject(i));
        }*/
        
        ModelAndView mv = new ModelAndView("category");
        mv.addObject("category", category);
        return mv;
    }
}
