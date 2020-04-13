package service;

import dto.Member;
import error.MemberNotFoundException;
import error.WrongPasswordException;
import mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthService {

  @Autowired
  private MemberMapper memberMapper;

  public Member login(String email, String password) {
    Member member = memberMapper.selectOne(email, password);
    if (member == null) {
      throw new WrongPasswordException();
    }
    if (!member.matchPassword(password)) {
      throw new MemberNotFoundException();
    }

    return member;
  }

}
