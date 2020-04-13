package mapper;

import dto.Member;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MemberMapper {
  @Select("select * from member")
  List<Member> selectList();
}
