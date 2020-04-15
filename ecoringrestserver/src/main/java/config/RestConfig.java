package config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import controller.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import service.AuthService;
import service.DeviceService;
import service.DustService;

import java.util.List;

@Configuration
@EnableWebMvc
public class RestConfig implements WebMvcConfigurer {

  @Bean
  public AuthExceptionAdvice authExceptionAdvice() {
    return new AuthExceptionAdvice();
  }

  @Bean
  public DeviceExceptionAdvice deviceExceptionAdvice() {
    return new DeviceExceptionAdvice();
  }

  @Bean
  public AuthService authService() {
    return new AuthService();
  }

  @Bean
  public DeviceService deviceService() {
    return new DeviceService();
  }

  @Bean
  public DustService dustService() {
    return new DustService();
  }

  @Bean
  public AuthController authController() {
    return new AuthController();
  }

  @Bean
  public DeviceController deviceController() {
    return new DeviceController();
  }

  @Bean
  public DustController dustController() {
    return new DustController();
  }

}
