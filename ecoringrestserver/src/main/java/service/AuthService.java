package service;

import command.LoginRequest;
import dto.Member;
import mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthService {

  @Autowired
  private MemberMapper memberMapper;

  public Member login(LoginRequest loginRequest) {

  }

}
