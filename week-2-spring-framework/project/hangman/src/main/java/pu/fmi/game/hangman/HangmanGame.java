package pu.fmi.game.hangman;

import java.time.LocalDateTime;
import java.util.List;

public class HangmanGame {

  private Long id;
  private String wordToGuess;
  private LocalDateTime startedOnDate;
  private String currentWord;
  private Status status;
  private List<Character> wrongLetters;
  private int wrongGuesses;
  private int currentWrongGuess;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getWordToGuess() {
    return wordToGuess;
  }

  public void setWordToGuess(String wordToGuess) {
    this.wordToGuess = wordToGuess;
  }

  public LocalDateTime getStartedOnDate() {
    return startedOnDate;
  }

  public void setStartedOnDate(LocalDateTime startedOnDate) {
    this.startedOnDate = startedOnDate;
  }

  public String getCurrentWord() {
    return currentWord;
  }

  public void setCurrentWord(String currentWord) {
    this.currentWord = currentWord;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public List<Character> getWrongLetters() {
    return wrongLetters;
  }

  public void setWrongLetters(List<Character> wrongLetters) {
    this.wrongLetters = wrongLetters;
  }

  public int getWrongGuesses() {
    return wrongGuesses;
  }

  public void setWrongGuesses(int wrongGuesses) {
    this.wrongGuesses = wrongGuesses;
  }

  public int getCurrentWrongGuess() {
    return currentWrongGuess;
  }

  public void setCurrentWrongGuess(int currentWrongGuess) {
    this.currentWrongGuess = currentWrongGuess;
  }

  @Override
  public String toString() {
    return "HangmanGame{" +
        "id=" + id +
        ", wordToGuess='" + wordToGuess + '\'' +
        ", startedOnDate=" + startedOnDate +
        ", currentWord='" + currentWord + '\'' +
        ", status=" + status +
        ", wrongLetters=" + wrongLetters +
        ", wrongGuesses=" + wrongGuesses +
        ", currentWrongGuess=" + currentWrongGuess +
        '}';
  }
}
