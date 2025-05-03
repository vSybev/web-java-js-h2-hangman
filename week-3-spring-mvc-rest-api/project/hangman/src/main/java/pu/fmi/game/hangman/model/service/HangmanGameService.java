package pu.fmi.game.hangman.model.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pu.fmi.game.hangman.model.config.GameProperties;
import pu.fmi.game.hangman.model.entity.HangmanGame;
import pu.fmi.game.hangman.model.entity.Status;
import pu.fmi.game.hangman.model.wordprovider.GenericWordProvider;

@Service
public class HangmanGameService {

  private static final int INITIAL_WRONG_GUESSES = 0;
  private static final String PLACEHOLDER_SYMBOL = "_";

  private List<HangmanGame> storedGamesCollection = new ArrayList<>();

  private GenericWordProvider wordProvider;
  private GameProperties gameProperties;

  public HangmanGameService(GenericWordProvider wordProvider, GameProperties gameProperties){
    this.wordProvider = wordProvider;
    this.gameProperties = gameProperties;
  }

  @PostConstruct
  public void init(){
    System.out.println("Този методи се извиква при инициализация на bean-a");
  }

  public HangmanGame startNewGame(){
    HangmanGame hangmanGame = new HangmanGame();

    int currentSize = storedGamesCollection.size();
    int nextId = currentSize + 1;
    hangmanGame.setId((long) nextId);

    String randomSelectedWord = wordProvider.generateRandomWord();
    hangmanGame.setWordToGuess(randomSelectedWord);
    hangmanGame.setStartedOnDate(LocalDateTime.now());
    hangmanGame.setWrongGuesses(gameProperties.getMaxNumberOfGuesses());
    hangmanGame.setCurrentWrongGuess(INITIAL_WRONG_GUESSES);
    hangmanGame.setStatus(Status.IN_PROGRESS);
    hangmanGame.setCurrentWord(generateInitialWord(randomSelectedWord.length()));
    hangmanGame.setWrongLetters(new ArrayList<>());

    storedGamesCollection.add(hangmanGame);
    return hangmanGame;
  }

  public HangmanGame makeGuess(Long id, char letter){

    HangmanGame hangmanGame = getGameById(id);

    if(hangmanGame == null){
      return null;
    }

    String wordToGuess = hangmanGame.getWordToGuess();
    String currentWord = hangmanGame.getCurrentWord();

    if(currentWord.indexOf(letter) >= 0 && hangmanGame.getWrongLetters().contains(letter)){
      return hangmanGame;
    }

    if(checkIsLetterWrong(wordToGuess, letter)){
      hangmanGame.setCurrentWrongGuess(hangmanGame.getCurrentWrongGuess() + 1);
      hangmanGame.getWrongLetters().add(letter);
    } else {
      String currentWordAfterFilled =
          getCurrentWordAfterLetterFill(wordToGuess, currentWord, letter);
      hangmanGame.setCurrentWord(currentWordAfterFilled);
    }

    updateGameStatusIfFinished(hangmanGame);
    return hangmanGame;
  }

  private boolean checkIsLetterWrong(String wordToGuess, char letter){
    return wordToGuess.indexOf(letter) == -1;
  }

  private String getCurrentWordAfterLetterFill(String wordToGuess, String currentWord, char letter){

    StringBuilder newCurrentWord = new StringBuilder();

    for (int index = 0; index < wordToGuess.length(); index++) {

      char actualLetter = wordToGuess.charAt(index);
      char currentLetter = currentWord.charAt(index);

      if(actualLetter == letter){
        newCurrentWord.append(letter);
        continue;
      }

      newCurrentWord.append(currentLetter);
    }

    return newCurrentWord.toString();
  }

  private void updateGameStatusIfFinished(HangmanGame hangmanGame){
    if(hangmanGame.getWordToGuess().equals(hangmanGame.getCurrentWord())){
      hangmanGame.setStatus(Status.WON);
      return;
    }
    if(hangmanGame.getCurrentWrongGuess() >= hangmanGame.getWrongGuesses()){
      hangmanGame.setStatus(Status.LOST);
    }

  }

  public HangmanGame getGameById(Long id){
    for(HangmanGame hangmanGame : storedGamesCollection){
      if(hangmanGame.getId().equals(id)){
        return hangmanGame;
      }
    }
    return null;
  }

  @PreDestroy
  public void destroy(){
    System.out.println("Този методи се извиква когато bean-a се премахне. (приложението спре да работи)");
  }

  private String generateInitialWord(int wordToGuessLength){
    StringBuilder stringBuilder = new StringBuilder();
    for(int i = 0; i < wordToGuessLength; i++){
      stringBuilder.append(PLACEHOLDER_SYMBOL);
    }
    return stringBuilder.toString();
  }
}
