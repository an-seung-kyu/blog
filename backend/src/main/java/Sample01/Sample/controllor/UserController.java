package Sample01.Sample.controllor;

import Sample01.Sample.model.User;
import Sample01.Sample.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping ("/login")
    public JSONObject login(@RequestBody JSONObject user) {
        String userId = (String) user.get("userId");
        return userService.findUsersByUserId(userId);
    }
    @GetMapping("/{userId}")
    public User getUserInfo(@PathVariable("userId") String userId, @AuthenticationPrincipal User user) {
        return userService.getUserInfo(userId);
    }

    /*@GetMapping("")
    public List<Users> findAll() {
        return userRepository.findAll();
    }*/



}
