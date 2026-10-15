package com.koreanwordle.game;

import com.koreanwordle.game.listener.PublicApiProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(PublicApiProperties.class)
public class GameApplication{
	public static void main(String[] args) {
		SpringApplication.run(GameApplication.class, args);
	}
}
