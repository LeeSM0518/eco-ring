package config;

import controller.TestController;
import mapper.MemberMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class RestConfig implements WebMvcConfigurer {

  @Bean
  public TestController testController() {
    return new TestController();
  }

}
