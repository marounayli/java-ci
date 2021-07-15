package continious.integration.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@PropertySource("classpath:api.properties")
public class UserController {

    private final UserService userService;
    private final String baseUrl;

    public UserController(UserService userService, @Value("${user.service.url}") String baseUrl) {
        this.userService = userService;
        this.baseUrl = baseUrl;
    }
    @GetMapping("/{service}")
    ResponseEntity<UserDTO> helloWorld(@PathVariable("service") String service){
        return ResponseEntity.ok(userService.getUser(baseUrl+service));
    }
}
