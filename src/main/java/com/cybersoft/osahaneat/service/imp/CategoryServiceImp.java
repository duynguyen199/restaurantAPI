package com.cybersoft.osahaneat.service.imp;

import com.cybersoft.osahaneat.dto.CategoryDTO;
import com.cybersoft.osahaneat.reposistory.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface CategoryServiceImp {

    List<CategoryDTO> getCategoryHomepage();
}
