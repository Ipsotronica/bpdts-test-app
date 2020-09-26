package org.example.chrispeel.web;

import org.example.chrispeel.model.User;
import org.example.chrispeel.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.example.chrispeel.util.GlobalConstants.LONDON_LAT;
import static org.example.chrispeel.util.GlobalConstants.LONDON_LONG;

@RestController
public class UserController {

    @Inject
    private UserService userService;

    @GetMapping("/users")
    public List<User> getUsersInLondonOrWithin50Miles() {
        List<User> londonUsers = userService.getUsersByCity("London");
        List<User> rangeUsers = userService.getUsersWithinRangeOf(LONDON_LAT, LONDON_LONG, 50);

        ArrayList<User> users = new ArrayList<>(londonUsers);
        users.addAll(rangeUsers);

        return users;
    }
}
