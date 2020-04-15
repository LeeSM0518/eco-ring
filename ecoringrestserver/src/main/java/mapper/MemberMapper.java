package mapper;

import dto.Member;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MemberMapper {

  @Select("select * from member where email=#{email}")
  Member selectOne(@Param("email") String email);

  @Insert("insert into member (name, email, password, authority) " +
      "values (#{name}, #{email}, #{password}, 'USER')")
  void insertUser(@Param("name") String name, @Param("email") String email, @Param("password") String password);

}
