package com.examplexyy.demo.web;

import com.examplexyy.demo.model.Personal;
import com.examplexyy.demo.repo.PersonalRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@Slf4j
public class DemoController {

    @Autowired private PersonalRepo personalRepo;

    @RequestMapping("hello")
    public Personal tryHello(){
        Personal person = new Personal();
        person.setAge(18);
        person.setName("xyyDemo");
        person.setEmail("18850198317@163.com");
        person.setBirthday(new Date());
        personalRepo.save(person);
        log.info("xyy person ->{}", person);
        return person;
    }
}
