package pu.fmi.game.hangman;

public class FileWordProvider implements GenericWordProvider {

  @Override
  public String generateRandomWord(){
    // Generate word from file
    return "file";
  }

}
