package config;

import controller.AuthController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import service.AuthService;

@Configuration
@EnableWebMvc
public class RestConfig implements WebMvcConfigurer {

  @Bean
  public AuthService authService() {
    return new AuthService();
  }

  @Bean
  public AuthController authController() {
    return new AuthController();
  }

}
