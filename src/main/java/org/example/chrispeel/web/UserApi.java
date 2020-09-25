package org.example.chrispeel.web;

import org.example.chrispeel.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class UserApi {

    @GetMapping
    public List<User> getUsersInLondonOrWithin50Miles(){
        return Collections.emptyList();
    }
}
