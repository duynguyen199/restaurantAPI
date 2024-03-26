package com.cybersoft.osahaneat.reposistory;

import com.cybersoft.osahaneat.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository <Food, Integer>{


}
