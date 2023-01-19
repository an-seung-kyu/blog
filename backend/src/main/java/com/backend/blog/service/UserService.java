package com.backend.blog.service;

import com.backend.blog.model.User;
import com.backend.blog.regository.UserRepository;
import com.backend.blog.security.JwtTokenProvider;
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
        return userRepository.findUsersByUserEmail(userId);
    }

    public JSONObject findUsersByUserId (String userId) {
        User user = userRepository.findUsersByUserEmail(userId);
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