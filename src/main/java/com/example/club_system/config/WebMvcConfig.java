package com.example.club_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // 定義成配置檔並交由spring管
public class WebMvcConfig implements WebMvcConfigurer {
 
 @Bean
 public CorsFilter corsFilter() {
     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
     source.registerCorsConfiguration("/**", corsConfig());
     return new CorsFilter(source);
 }

 private CorsConfiguration corsConfig() {
     CorsConfiguration corsConfiguration = new CorsConfiguration();
     corsConfiguration.addAllowedOriginPattern("*");
     corsConfiguration.addAllowedHeader("*");
     corsConfiguration.addAllowedMethod("*");
     corsConfiguration.setAllowCredentials(true);
     return corsConfiguration;
 }

 
 @Override
 public void addCorsMappings(CorsRegistry registry) {
  registry.addMapping("/**")
    .allowedOriginPatterns("*") // spring boot v2.4 之後使用
    .allowCredentials(true).allowedMethods("POST", "GET") // 多個用逗號(,)分隔;"POST", "GET"
    .allowedHeaders("*");
 }
}
