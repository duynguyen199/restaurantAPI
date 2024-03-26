package com.cybersoft.osahaneat.service;

import com.cybersoft.osahaneat.dto.CategoryDTO;
import com.cybersoft.osahaneat.dto.MenuDTO;
import com.cybersoft.osahaneat.entity.Category;
import com.cybersoft.osahaneat.entity.Food;
import com.cybersoft.osahaneat.reposistory.CategoryRepository;
import com.cybersoft.osahaneat.service.imp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CategoryService implements CategoryServiceImp {
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public List<CategoryDTO> getCategoryHomepage() {
        PageRequest pageRequest=  PageRequest.of(1,3, Sort.by("id"));
        Page<Category> listCategory = categoryRepository.findAll(pageRequest);

        List <CategoryDTO> listCategoryDTOS= new ArrayList<>();
        for(Category data:listCategory){
            CategoryDTO categoryDTO=new CategoryDTO();
            categoryDTO.setName(data.getName_cate());
      
            List<MenuDTO> menuDTOS=new ArrayList<>();
            for(Food dataFood:data.getListFood()){
                MenuDTO menuDTO=new MenuDTO();
                menuDTO.setTitle(dataFood.getTitle());
                menuDTO.setFreeShip(dataFood.isFreeShip());
                menuDTO.setImage(dataFood.getImage());

                menuDTOS.add(menuDTO);
            }
            categoryDTO.setMenu(menuDTOS);
            listCategoryDTOS.add(categoryDTO);
        }
        return listCategoryDTOS;
    }
}
