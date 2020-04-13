package controller;

import dto.Member;
import mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

  @Autowired
  private MemberMapper memberMapper;

  @GetMapping("/test")
  public String test() {
    return "test";
  }

  @GetMapping("/members")
  public List<Member> members() {
    return memberMapper.selectList();
  }

  @Mapping
}
