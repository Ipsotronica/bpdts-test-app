package org.example.chrispeel.service;

import org.example.chrispeel.model.User;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.Client;
import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    private final Client client;

    public UserService(Client client) {
        this.client = client;
    }

    public List<User> getUsersByCity(String city){
        return Collections.emptyList();
    }

    public List<User> getUsersWithinRangeOf(double originLat, double originLong, int range){

        return Collections.emptyList();
    }

}
