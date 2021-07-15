package continious.integration.example;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    private final UserMapper userMapper = UserMapper.INSTANCE;
    private final RestTemplate restTemplate = new RestTemplate();
    UserDTO getUser(String path){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<UserResponse> response = restTemplate.exchange(path, HttpMethod.GET, entity,UserResponse.class);
        if(response.getStatusCode()!=HttpStatus.OK){
            return null;
        }
        else{
            return userMapper.buildUserDTO(response.getBody());
        }
    }
}
