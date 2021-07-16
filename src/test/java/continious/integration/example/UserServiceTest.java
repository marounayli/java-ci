package continious.integration.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@TestPropertySource("classpath:api.properties")
public class UserServiceTest {

    private final String baseUrl;

    public UserServiceTest(@Value("${user.service.url}") String baseUrl) {
        this.baseUrl = baseUrl;
    }

    private UserResponse getUser(String parameter){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<UserResponse> response = new RestTemplate().exchange(baseUrl+parameter, HttpMethod.GET, entity,UserResponse.class);
        return response.getBody();
    }

    @Test
    void getFirstUser(){
        UserResponse responseBody = getUser("user202");
        assert(responseBody!=null);
        assert(responseBody.getName().equals("Maroun Ayli"));
        assert(responseBody.getAge()==22);
    }


    @Test
    void getSecondUser(){
        UserResponse responseBody = getUser("user201");
        assert(responseBody!=null);
        assert(responseBody.getName().equals("Alpha Beta"));
        assert(responseBody.getAge()==25);
    }


}
