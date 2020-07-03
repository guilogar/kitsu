/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opinno.kitsu;

import com.opinno.kitsu.Http.HttpGetRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author guilogar
 */
@Controller
public class SearchController
{
    @RequestMapping(
        value = "/search", 
        method = RequestMethod.GET
    )
    public ModelAndView searchGet(@RequestParam("data") String searchData) throws IOException, JSONException
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
    
    @RequestMapping(
        value = "/search",
        consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
        method = RequestMethod.POST
    )
    public ModelAndView searchPost(SearchData searchData, BindingResult binding)
        throws UnsupportedEncodingException, IOException, JSONException
    {
        if(binding.hasErrors())
        {
            // catch this and return a 400 response from your general handler
            throw new IllegalArgumentException();
        }
        ArrayList<Object> animus = new ArrayList<>();
        
        // only for html string....
        System.out.println(searchData);
        Jsoup.clean(searchData.toString(), Whitelist.basic());
        System.out.println(searchData);
        
        String url = "https://kitsu.io/api/edge/anime?filter[text]=" + searchData.toString();
        
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
