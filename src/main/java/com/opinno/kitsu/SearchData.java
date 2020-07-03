/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opinno.kitsu;

/**
 *
 * @author guilogar
 */
public class SearchData {
    
    public String data;
    public int value;
    
    public SearchData(String data)
    {
        this.data = data;
        this.value = 0;
    }
    
    public String getData()
    {
        return this.data;
    }
    
    public void setData(String data)
    {
        this.data = data;
    }
    
    @Override
    public String toString()
    {
        return this.data + this.value;
    }
}
