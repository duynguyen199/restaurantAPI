package com.cybersoft.osahaneat.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity(name="category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column (name="name_cate")
    private String name_cate;
    @Column(name="create_date")
    private Date createDate;

    @OneToMany(mappedBy = "category")
    private Set<Food> listFood;

    @OneToMany (mappedBy = "category")
    private Set<MenuRestaurant> listMenuRestaurant;

    public Set<MenuRestaurant> getListMenuRestaurant() {
        return listMenuRestaurant;
    }

    public void setListMenuRestaurant(Set<MenuRestaurant> listMenuRestaurant) {
        this.listMenuRestaurant = listMenuRestaurant;
    }

    public Set<Food> getListFood() {
        return listFood;
    }

    public void setListFood(Set<Food> listFood) {
        this.listFood = listFood;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_cate() {
        return name_cate;
    }

    public void setName_cate(String name_cate) {
        this.name_cate = name_cate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
