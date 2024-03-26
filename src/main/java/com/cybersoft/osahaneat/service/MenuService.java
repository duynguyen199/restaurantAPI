package com.cybersoft.osahaneat.service;

import com.cybersoft.osahaneat.entity.Category;
import com.cybersoft.osahaneat.entity.Food;
import com.cybersoft.osahaneat.entity.Restaurant;
import com.cybersoft.osahaneat.reposistory.FoodRepository;
import com.cybersoft.osahaneat.service.imp.FileServiceImp;
import com.cybersoft.osahaneat.service.imp.MenuServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class MenuService implements MenuServiceImp {
    @Autowired
    FileServiceImp fileServiceImp;

    @Autowired
    FoodRepository foodRepository;
    @Override
    public boolean createMenu(MultipartFile file, String title, boolean is_freeship, String time_ship, double price, int cate_id) {
        boolean isInsert= false;
        try{
            boolean isSaveFileSuccess= fileServiceImp.saveFile(file);
            if(isSaveFileSuccess){
                Food food = new Food();
                food.setTitle(title);
                food.setImage(file.getOriginalFilename());
                food.setTimeShip(time_ship);
                food.setPrice(price);

                Category category=new Category();
                category.setId(cate_id);
                food.setCategory(category);
                foodRepository.save(food);
                isInsert = true;

            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


        return isInsert;
    }
}
