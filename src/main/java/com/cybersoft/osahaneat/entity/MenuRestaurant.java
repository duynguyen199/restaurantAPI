package com.cybersoft.osahaneat.entity;

import com.cybersoft.osahaneat.entity.key.KeyMenuRestaurant;
import jakarta.persistence.*;

import java.util.Date;

@Entity(name = "menu_restaurant")
public class MenuRestaurant {


    @EmbeddedId
    private KeyMenuRestaurant keyMenuRestaurant;
    @ManyToOne
    @JoinColumn(name="cate_id",insertable = false,updatable = false)
    private Category category;


    @ManyToOne
    @JoinColumn(name="res_id",insertable = false,updatable = false)
    private Restaurant restaurant;

    @Column(name="create_date")
    private Date createDate;


    public KeyMenuRestaurant getKeyMenuRestaurant() {
        return keyMenuRestaurant;
    }

    public void setKeyMenuRestaurant(KeyMenuRestaurant keyMenuRestaurant) {
        this.keyMenuRestaurant = keyMenuRestaurant;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
