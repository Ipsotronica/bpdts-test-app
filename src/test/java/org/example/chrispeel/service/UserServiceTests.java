package org.example.chrispeel.service;

import org.example.chrispeel.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.example.chrispeel.util.GlobalConstants.LONDON_LAT;
import static org.example.chrispeel.util.GlobalConstants.LONDON_LONG;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UserServiceTests {

    @Mock
    private HttpClient mockClient;

    @Mock
    private HttpResponse<String> mockResponse;

    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService();
        userService.setClient(mockClient);
    }


    @Test
    public void testGetUsersByRange() {
        String responseBody = "[{\"id\": 1, \"first_name\": \"Maurise\", \"last_name\": \"Shieldon\", \"email\": \"mshieldon0@squidoo.com\", \"ip_address\": \"192.57.232.111\", \"latitude\": 51.2704, \"longitude\": 0.5227}, {\"id\": 2, \"first_name\": \"Bendix\", \"last_name\": \"Halgarth\", \"email\": \"bhalgarth1@timesonline.co.uk\", \"ip_address\": \"4.185.73.82\", \"latitude\": 52.6369, \"longitude\": -1.1398}, {\"id\": 3, \"first_name\": \"Meghan\", \"last_name\": \"Southall\", \"email\": \"msouthall2@ihg.com\", \"ip_address\": \"21.243.184.215\", \"latitude\": \"52.2053\", \"longitude\": \"0.1218\"}]";
        when(mockResponse.body()).thenReturn(responseBody);
        when(mockClient.sendAsync(Mockito.any(HttpRequest.class), Mockito.any(HttpResponse.BodyHandler.class))).thenReturn(CompletableFuture.completedFuture(mockResponse));

        List<User> users = userService.getUsersWithinRangeOf(LONDON_LAT, LONDON_LONG, 50);
        assertEquals(2, users.size());
    }

    @Test
    public void testGetUsersByCity() {
        String responseBody = "[{\"id\": 135, \"first_name\": \"Mechelle\", \"last_name\": \"Boam\", \"email\": \"mboam3q@thetimes.co.uk\", \"ip_address\": \"113.71.242.187\", \"latitude\": -6.5115909, \"longitude\": 105.652983}, {\"id\": 396, \"first_name\": \"Terry\", \"last_name\": \"Stowgill\", \"email\": \"tstowgillaz@webeden.co.uk\", \"ip_address\": \"143.190.50.240\", \"latitude\": -6.7098551, \"longitude\": 111.3479498}]";
        when(mockResponse.body()).thenReturn(responseBody);
        when(mockClient.sendAsync(Mockito.any(HttpRequest.class), Mockito.any(HttpResponse.BodyHandler.class))).thenReturn(CompletableFuture.completedFuture(mockResponse));

        List<User> users = userService.getUsersByCity("blah");
        assertEquals(2, users.size());

    }
}
