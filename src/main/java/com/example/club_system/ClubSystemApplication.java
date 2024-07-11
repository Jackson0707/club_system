package com.example.club_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//因為有使用 spring-boot-starter-security 此依賴，要排除預設的基本安全性設定(帳密登入驗證)
//排除帳密登入驗證就是加上 exclude= SecurityAutoConfiguration.class 
@SpringBootApplication(exclude= SecurityAutoConfiguration.class)
public class ClubSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClubSystemApplication.class, args);
	}

}
