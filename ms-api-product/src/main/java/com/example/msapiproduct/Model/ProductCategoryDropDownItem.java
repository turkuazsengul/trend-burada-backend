package com.example.msapiproduct.Model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductCategoryDropDownItem {
    private String key;
    private String label;
    private List<Children> children;

    public ProductCategoryDropDownItem(){
        this.children = new ArrayList<>();
    }

    public void addList(Children obj){
        children.add(obj);
    }
}
