package Sample01.Sample.service;

import Sample01.Sample.model.User;
import Sample01.Sample.repository.UserRepository;
import Sample01.Sample.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public User getUserInfo(String userId) {
        return userRepository.findUsersByUserId(userId);
    }

    public JSONObject findUsersByUserId (String userId) {
        User user = userRepository.findUsersByUserId(userId);
        if (user == null) throw new NullPointerException("아이디가 존재하지 않습니다.");
        String token = jwtTokenProvider.createToken(user);
        JSONObject data = new JSONObject();
        data.put("result", true);
        data.put("token", token);
        data.put("_id", user.get_id());
        data.put("userName", user.getUsername());
        data.put("userEmail", user.getUserEmail());
        return data;
    }
}
