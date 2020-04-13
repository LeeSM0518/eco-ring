package controller;

import command.LoginRequest;
import dto.Member;
import error.MemberNotFoundException;
import error.WrongPasswordException;
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
  public Member login(@RequestBody LoginRequest request) {
    try {
      return authService.login(request.getEmail(), request.getPassword());
    } catch (MemberNotFoundException memberNotFoundException) {
      throw new MemberNotFoundException();
    } catch (WrongPasswordException wrongPasswordException) {
      throw new WrongPasswordException();
    }
  }

}
