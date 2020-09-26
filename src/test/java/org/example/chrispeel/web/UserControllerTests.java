package org.example.chrispeel.web;

import org.example.chrispeel.model.User;
import org.example.chrispeel.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService mockUserService;

    @Test
    public void testGetUsers() throws Exception {
        User userCity1 = new User();
        userCity1.setId(135);
        userCity1.setFirstName("Mechelle");
        userCity1.setLastName("Boam");
        userCity1.setEmail("mboam3q@thetimes.co.uk");
        userCity1.setIpAddress("113.71.242.187");
        userCity1.setLatitude(-6.5115909);
        userCity1.setLongitude(105.652983);

        User userCity2 = new User();
        userCity2.setId(396);
        userCity2.setFirstName("Terry");
        userCity2.setLastName("Stowgill");
        userCity2.setEmail("tstowgillaz@webeden.co.uk");
        userCity2.setIpAddress("143.190.50.240");
        userCity2.setLatitude(-6.7098551);
        userCity2.setLongitude(111.3479498);

        User userRange1 = new User();
        userRange1.setId(1);
        userRange1.setFirstName("Maurise");
        userRange1.setLastName("Shieldon");
        userRange1.setEmail("mshieldon0@squidoo.com");
        userRange1.setIpAddress("192.57.232.111");
        userRange1.setLatitude(51.2704);
        userRange1.setLongitude(0.5227);

        User userRange3 = new User();
        userRange3.setId(3);
        userRange3.setFirstName("Meghan");
        userRange3.setLastName("Southall");
        userRange3.setEmail("msouthall2@ihg.com");
        userRange3.setIpAddress("21.243.184.215");
        userRange3.setLatitude(52.2053);
        userRange3.setLongitude(0.1218);


        List<User> cityUsers = List.of(userCity1, userCity2);
        List<User> rangeUsers = List.of(userRange1, userRange3);

        when(mockUserService.getUsersByCity(Mockito.anyString())).thenReturn(cityUsers);
        when(mockUserService.getUsersWithinRangeOf(Mockito.anyDouble(), Mockito.anyDouble(), Mockito.anyInt())).thenReturn(rangeUsers);

        this.mockMvc
                .perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)));
    }
}
