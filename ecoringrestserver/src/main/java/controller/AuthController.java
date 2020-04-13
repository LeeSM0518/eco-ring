package controller;

import command.LoginRequest;
import dto.Member;
import mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import service.AuthService;

import java.util.List;

@RestController
public class AuthController {

  @Autowired
  private AuthService authService;

  @PostMapping("/auth/login")
  public String login(@RequestBody LoginRequest request) {

    return "test";
  }

  @GetMapping("/members")
  public List<Member> members() {
    return memberMapper.selectList();
  }

}
