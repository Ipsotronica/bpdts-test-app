package org.example.chrispeel.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.chrispeel.model.User;
import org.example.chrispeel.util.DistanceCalculator;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static final String URL_BASE = "https://bpdts-test-app.herokuapp.com/";

    @Inject
    private HttpClient client;

    public void setClient(HttpClient client) {
        this.client = client;
    }

    public List<User> getUsersByCity(String city) {
        final String action = String.format("city/%s/users", city);
        return getUsersFromEndpoint(action);
    }

    public List<User> getUsersWithinRangeOf(double originLat, double originLong, int range) {
        final String action = "users";

        List<User> usersFromEndpoint = getUsersFromEndpoint(action);

        return usersFromEndpoint.parallelStream().filter(user -> DistanceCalculator.distanceBetween(originLat, originLong, user.getLatitude(), user.getLongitude()) < range).collect(Collectors.toList());
    }

    private CompletableFuture<String> doRequest(String action) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_BASE + action))
                .header("Accept", "application/json")
                .timeout(Duration.ofMinutes(2))
                .GET()
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }

    private List<User> getUsersFromEndpoint(String action) {
        try {
            String body = doRequest(action).get();
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(body, mapper.getTypeFactory().constructCollectionType(List.class, User.class));
        } catch (JsonProcessingException e) {
            throw new UncheckedIOException(e);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

}
