package pu.fmi.game.hangman;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GameConfiguration {

  @Bean
  public String myFirstBean() {
    return "This is my first Bean";
  }

  @Bean
  public WordProvider wordProvider() {
    return new WordProvider();
  }

  @Bean
  public GameProperties gameProperties() {
    return new GameProperties();
  }
}
