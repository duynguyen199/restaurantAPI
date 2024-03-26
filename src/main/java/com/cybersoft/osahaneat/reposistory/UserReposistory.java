package com.cybersoft.osahaneat.reposistory;

import com.cybersoft.osahaneat.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserReposistory extends JpaRepository<Users,Integer>  {
        /*
        * select * from users where username=" and password=""       * */

        List<Users> findByUsernameAndPassword(String usernane, String password);

        Users findByUsername(String username);


}
