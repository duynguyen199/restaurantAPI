package com.cybersoft.osahaneat.dto;

import java.util.List;

public class CategoryDTO {

    private String name;
     private List<MenuDTO> menu;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MenuDTO> getMenu() {
        return menu;
    }

    public void setMenu(List<MenuDTO> menu) {
        this.menu = menu;
    }
}
