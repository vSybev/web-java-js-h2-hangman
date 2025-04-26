package pu.fmi.game.hangman;

import java.util.List;
import java.util.Random;

public class WordProvider implements GenericWordProvider {

  private final List<String> wordsCollection = List.of("мерцедес", "шаблон", "ауди", "парк", "лаптоп");

  @Override
  public String generateRandomWord(){
    Random random = new Random();
    return this.wordsCollection.get(random.nextInt(this.wordsCollection.size()));
  }

}
