package com.ten.ten;

import com.ten.ten.entities.UserEntity;
import com.ten.ten.repositories.UserReporitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class TenApplication    {

	public static void main(String[] args) {
		SpringApplication.run(TenApplication.class, args);
		System.out.println("Hello Ten");
	}
}
