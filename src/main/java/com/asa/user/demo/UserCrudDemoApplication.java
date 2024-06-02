package com.asa.user.demo;

import com.asa.user.demo.model.UserEntity;
import com.asa.user.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class UserCrudDemoApplication implements ApplicationRunner {
    @Autowired
    private UserService userService;
    public static void main(String[] args) {
        SpringApplication.run(UserCrudDemoApplication.class, args);
    }

    /**
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {

        for (int i=0;i<25;i++){
            String firstname = "Fn_"+i+1;
            String lastname = "LN_"+i+1;
            this.userService.addUser(UserEntity.builder()
                    .civility(i%2==0?CivilityType.Mr.name():CivilityType.Ms.name())
                    .firstname(firstname)
                    .lastname(lastname)
                    .birthday(LocalDate.of(1900+i,i%12+1,i%30+1))
                    .email(firstname+"."+lastname+"@gmail.com").build());
        }

    }

}
