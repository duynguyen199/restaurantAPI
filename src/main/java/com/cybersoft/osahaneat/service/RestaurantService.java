package com.cybersoft.osahaneat.service;

import com.cybersoft.osahaneat.dto.CategoryDTO;
import com.cybersoft.osahaneat.dto.MenuDTO;
import com.cybersoft.osahaneat.dto.RestaurantDTO;
import com.cybersoft.osahaneat.entity.Food;
import com.cybersoft.osahaneat.entity.MenuRestaurant;
import com.cybersoft.osahaneat.entity.RatingRestaurant;
import com.cybersoft.osahaneat.entity.Restaurant;
import com.cybersoft.osahaneat.reposistory.RestaurantRepository;
import com.cybersoft.osahaneat.service.imp.FileServiceImp;
import com.cybersoft.osahaneat.service.imp.RestaurantServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RestaurantService implements RestaurantServiceImpl {
    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    FileServiceImp fileServiceImp;
    @Override
    public boolean insertRestaurant(MultipartFile file, String title, String subtitle, String description, boolean is_freeship, String address, String open_date) {
        boolean isInsert= false;
        try{
           boolean isSaveFileSuccess= fileServiceImp.saveFile(file);
           if(isSaveFileSuccess){
               Restaurant restaurant = new Restaurant();
               restaurant.setTitle(title);
               restaurant.setSubtitle(subtitle);
               restaurant.setDescription(description);
               restaurant.setImage(file.getOriginalFilename());
               restaurant.setFreeship(is_freeship);
                restaurant.setAddress(address);
               SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm" );
               Date openDate=  simpleDateFormat.parse(open_date);
               restaurant.setOpenDate(openDate);
               restaurantRepository.save(restaurant);
               isInsert=true;
           }
       }catch (Exception e){
           System.out.println(e.getMessage());
       }


            return isInsert;

    }

    @Override
    public List<RestaurantDTO> getHomePageRestaurant() {
        List <RestaurantDTO> restaurantDTOS= new ArrayList<>();
        PageRequest pageRequest=PageRequest.of(0,6);
       Page<Restaurant> listData=  restaurantRepository.findAll(pageRequest);
       for(Restaurant data: listData){
           RestaurantDTO restaurantDTO= new RestaurantDTO();
           restaurantDTO.setId(data.getId());
           restaurantDTO.setImage(data.getImage());
           restaurantDTO.setTitle(data.getTitle());
           restaurantDTO.setSubtitle(data.getSubtitle());
           restaurantDTO.setFreeShip(data.isFreeship());
           restaurantDTO.setRating(calculatorRating(data.getListRatingRestaurant()));
           restaurantDTOS.add(restaurantDTO);
       }
        return restaurantDTOS;
    }

    @Override
    public RestaurantDTO getDetailRestaurant(int id) {

      Optional<Restaurant> restaurant=  restaurantRepository.findById(id);
        RestaurantDTO restaurantDTO= new RestaurantDTO();

        if(restaurant.isPresent()){
            List < CategoryDTO> categoryDTOList = new ArrayList<>();
            Restaurant data = restaurant.get();

            restaurantDTO.setTitle(data.getTitle());
            restaurantDTO.setSubtitle(data.getSubtitle());
            restaurantDTO.setImage(data.getImage());
            restaurantDTO.setRating(calculatorRating(data.getListRatingRestaurant()));
            restaurantDTO.setFreeShip(data.isFreeship());
            restaurantDTO.setDesc(data.getDescription());
            restaurantDTO.setOpdenDate(data.getOpenDate());
            //category
            for (MenuRestaurant menuRestaurant:data.getListMenuRestaurant()) {
                List<MenuDTO> menuDTOList =new ArrayList<>();
                CategoryDTO categoryDTO= new CategoryDTO();
                categoryDTO.setName(menuRestaurant.getCategory().getName_cate());
                //menu
                for (Food food:menuRestaurant.getCategory().getListFood()) {
                    MenuDTO menuDTO=new MenuDTO();
                    menuDTO.setImage(food.getImage());
                    menuDTO.setFreeShip(food.isFreeShip());
                    menuDTO.setTitle(food.getTitle());

                    menuDTO.setDescription((food.getDescription()));
                    menuDTO.setPrice((food.getPrice()));
                    menuDTO.setId(food.getId());
                    menuDTOList.add(menuDTO);
                }
                categoryDTO.setMenu(menuDTOList);
                categoryDTOList.add(categoryDTO);
            }
            restaurantDTO.setListCategory(categoryDTOList);
      }

        return restaurantDTO;
    }

    private  double calculatorRating(Set<RatingRestaurant> listRating){
        double totalPoint = 0;
        for (RatingRestaurant data:listRating){
            totalPoint+= data.getRatePoint();
        }
        return totalPoint/listRating.size();

    }


}
