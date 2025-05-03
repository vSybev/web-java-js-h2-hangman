package pu.fmi.game.hangman.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pu.fmi.game.hangman.model.entity.HangmanGame;
import pu.fmi.game.hangman.model.entity.Status;
import pu.fmi.game.hangman.model.service.HangmanGameService;
import pu.fmi.game.hangman.utils.GameUtils;

@Controller
public class HomeController {

  private final HangmanGameService hangmanGameService;

  public HomeController(HangmanGameService hangmanGameService){
    this.hangmanGameService = hangmanGameService;
  }

  @RequestMapping(value = "/home", method = RequestMethod.GET)
  public String home(){
    return "home.html";
  }

//  @PostMapping
//  @DeleteMapping
//  @PutMapping
  @PostMapping("start")
  public String startGame(Model model){
    System.out.println("Start Game");
    HangmanGame hangmanGame = hangmanGameService.startNewGame();
    model.addAttribute("hangmanGame", hangmanGame);
    model.addAttribute("letters", GameUtils.getLetters());
    return "play-game.html";
  }

  @PostMapping("guessLetter/{id}")
  public ModelAndView guessLetter(
      @PathVariable Long id,
      @RequestParam Character guessLetter){

    HangmanGame hangmanGame = hangmanGameService.makeGuess(id, guessLetter);

    // TODO: Добавете логика, за край на
    //  играта - да се показва game-end.html и
    //  съобщение "Спечелена"  или "Загубена"

    if(hangmanGame.getStatus().equals(Status.WON)){
      ModelAndView modelAndViewWon =
          new ModelAndView("game-end.html");
      modelAndViewWon.addObject("message", "Спечелена");
      return modelAndViewWon;
    }
    if(hangmanGame.getStatus().equals(Status.LOST)){
      ModelAndView modelAndViewLost =
          new ModelAndView("game-end.html");
      modelAndViewLost.addObject("message", "Загубена");
      return modelAndViewLost;
    }

    ModelAndView modelAndView = new ModelAndView("play-game.html");
    modelAndView.addObject("hangmanGame", hangmanGame);
    modelAndView.addObject("letters", GameUtils.getLetters());
    return modelAndView;
  }

}
