package pu.fmi.game.hangman;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "hangman-game")
public class GameProperties {

  private int maxNumberOfGuesses;
  private String defaultWord;
  private String defaultLevel;

  public int getMaxNumberOfGuesses() {
    return maxNumberOfGuesses;
  }

  public void setMaxNumberOfGuesses(int maxNumberOfGuesses) {
    this.maxNumberOfGuesses = maxNumberOfGuesses;
  }

  public String getDefaultWord() {
    return defaultWord;
  }

  public void setDefaultWord(String defaultWord) {
    this.defaultWord = defaultWord;
  }

  public String getDefaultLevel() {
    return defaultLevel;
  }

  public void setDefaultLevel(String defaultLevel) {
    this.defaultLevel = defaultLevel;
  }
}
