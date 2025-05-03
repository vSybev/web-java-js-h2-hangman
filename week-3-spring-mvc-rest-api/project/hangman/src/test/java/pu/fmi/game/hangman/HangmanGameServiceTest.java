package pu.fmi.game.hangman;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pu.fmi.game.hangman.model.config.GameProperties;
import pu.fmi.game.hangman.model.wordprovider.GenericWordProvider;
import pu.fmi.game.hangman.model.entity.HangmanGame;
import pu.fmi.game.hangman.model.service.HangmanGameService;
import pu.fmi.game.hangman.model.entity.Status;

@ExtendWith(MockitoExtension.class)
public class HangmanGameServiceTest {

  @Mock private GenericWordProvider genericWordProvider;

  @Mock private GameProperties gameProperties;

  @InjectMocks private HangmanGameService hangmanGameService;

  @Test
  public void testStartNewGame(){

    when(gameProperties.getMaxNumberOfGuesses()).thenReturn(6);
    when(genericWordProvider.generateRandomWord()).thenReturn("test");

    HangmanGame hangmanGame = hangmanGameService.startNewGame();

    assertEquals(1, hangmanGame.getId());
    assertEquals("test", hangmanGame.getWordToGuess());
    assertEquals("____", hangmanGame.getCurrentWord());
    assertEquals(Status.IN_PROGRESS, hangmanGame.getStatus());
    assertEquals(6, hangmanGame.getWrongGuesses());
    assertEquals(0, hangmanGame.getCurrentWrongGuess());
    assertTrue(hangmanGame.getWrongLetters().isEmpty());
  }

  @Test
  public void testMakeGuessesAndGameWon(){

    when(gameProperties.getMaxNumberOfGuesses()).thenReturn(6);
    when(genericWordProvider.generateRandomWord()).thenReturn("лаптоп");

    hangmanGameService.startNewGame();

    hangmanGameService.makeGuess(1L, 'п');
    hangmanGameService.makeGuess(1L, 'а');
    hangmanGameService.makeGuess(1L, 'м');
    hangmanGameService.makeGuess(1L, 'л');
    hangmanGameService.makeGuess(1L, 'т');
    HangmanGame hangmanGameAfterGuess = hangmanGameService.makeGuess(1L, 'о');

    assertEquals("лаптоп", hangmanGameAfterGuess.getWordToGuess());
    assertEquals("лаптоп", hangmanGameAfterGuess.getCurrentWord());
    assertEquals(1, hangmanGameAfterGuess.getCurrentWrongGuess());
    assertEquals(Status.WON, hangmanGameAfterGuess.getStatus());
  }

  @Test
  public void testMakeGuessesAndGameLost(){

    when(gameProperties.getMaxNumberOfGuesses()).thenReturn(6);
    when(genericWordProvider.generateRandomWord()).thenReturn("лаптоп");

    hangmanGameService.startNewGame();

    hangmanGameService.makeGuess(1L, 'п');
    hangmanGameService.makeGuess(1L, 'я');
    hangmanGameService.makeGuess(1L, 'м');
    hangmanGameService.makeGuess(1L, 'и');
    hangmanGameService.makeGuess(1L, 'д');
    hangmanGameService.makeGuess(1L, 'е');
    HangmanGame hangmanGameAfterGuess = hangmanGameService.makeGuess(1L, 'у');

    assertEquals("лаптоп", hangmanGameAfterGuess.getWordToGuess());
    assertEquals("__п__п", hangmanGameAfterGuess.getCurrentWord());
    assertEquals(6, hangmanGameAfterGuess.getCurrentWrongGuess());
    assertEquals(6, hangmanGameAfterGuess.getWrongLetters().size());
    assertEquals(Status.LOST, hangmanGameAfterGuess.getStatus());
  }

}
