package controller;

import command.LoginRequest;
import command.SignUpRequest;
import dto.Member;
import error.DuplicateEmailException;
import error.MemberNotFoundException;
import error.WrongPasswordException;
import mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import service.AuthService;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class AuthController {

  @Autowired
  private AuthService authService;

  @PostMapping("/auth/login")
  public Member login(@RequestBody LoginRequest request, HttpSession session) {
    try {
      Member member = authService.login(request.getEmail(), request.getPassword());
      session.setAttribute(member.getEmail(), member);
      return member;
    } catch (MemberNotFoundException memberNotFoundException) {
      throw new MemberNotFoundException();
    } catch (WrongPasswordException wrongPasswordException) {
      throw new WrongPasswordException();
    }
  }

  @PostMapping("/auth/logout")
  // TODO 이메일을 수신받아서 이메일로 session에서 attribute를 제거
  public void logout(HttpSession session) {
    session.invalidate();
  }

  @PostMapping("/auth/signup")
  public void signUp(@RequestBody SignUpRequest request) {
    try {
      authService.signUp(request.getName(), request.getEmail(), request.getPassword());
    } catch (DuplicateEmailException exception) {
      throw new DuplicateEmailException();
    }
  }

}
