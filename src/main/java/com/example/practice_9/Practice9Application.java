package com.example.practice_9;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

// �]�����ϥ� spring-boot-starter-security ���̿�A�n�ư��w�]���򥻦w���ʳ]�w(�b�K�n�J����)�C
// �ư��b�K�n�J���ҴN�O�[�W exclude = SecurityAutoConfiguration.class�C
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class Practice9Application {

	public static void main(String[] args) {
		SpringApplication.run(Practice9Application.class, args);
	}

}
