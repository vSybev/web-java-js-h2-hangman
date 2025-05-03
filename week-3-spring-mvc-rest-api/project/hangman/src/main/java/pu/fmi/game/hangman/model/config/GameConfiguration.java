package pu.fmi.game.hangman.model.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pu.fmi.game.hangman.model.wordprovider.WordProvider;

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
