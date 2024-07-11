package com.example.club_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//�]�����ϥ� spring-boot-starter-security ���̿�A�n�ư��w�]���򥻦w���ʳ]�w(�b�K�n�J����)
//�ư��b�K�n�J���ҴN�O�[�W exclude= SecurityAutoConfiguration.class 
@SpringBootApplication(exclude= SecurityAutoConfiguration.class)
public class ClubSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClubSystemApplication.class, args);
	}

}
