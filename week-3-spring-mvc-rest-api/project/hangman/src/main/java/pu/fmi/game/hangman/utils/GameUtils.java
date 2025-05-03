package pu.fmi.game.hangman.utils;

import java.util.Arrays;
import java.util.List;

public class GameUtils {

  private GameUtils() {}

  public static List<Character> getLetters() {
    return Arrays.asList(
        'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з', 'и', 'й',
        'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у',
        'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ь', 'ю', 'я'
    );
  }

}
