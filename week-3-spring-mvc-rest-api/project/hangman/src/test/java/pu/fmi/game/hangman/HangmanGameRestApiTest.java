package pu.fmi.game.hangman;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import pu.fmi.game.hangman.model.entity.HangmanGame;
import pu.fmi.game.hangman.model.entity.Status;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class HangmanGameRestApiTest {

  @Autowired
  WebTestClient client;

  @Test
  void testGetGameById() {
    client
        .post()
        .uri("/hangman-games")
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus()
        .isOk();

    client
        .get()
        .uri("/hangman-games/{id}", 1L)
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody()
          .jsonPath("$.id").isEqualTo(1)
          .jsonPath("$.status").isEqualTo(Status.IN_PROGRESS)
          .jsonPath("$.currentWrongGuess").isEqualTo(0)
          .jsonPath("$.wrongGuesses").isEqualTo(6);
  }


  @Test
  void testGetAllGames() {

    // Първоначално създаване на 3 игри
    client
        .post()
        .uri("/hangman-games")
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus()
        .isOk();
    client
        .post()
        .uri("/hangman-games")
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus()
        .isOk();
    client
        .post()
        .uri("/hangman-games")
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus()
        .isOk();

    // Тестване на GET /hangman-games което би трябвало да връща списък със всички игри
    client
        .get()
        .uri("/hangman-games")
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus()
        .isOk()
        .expectBodyList(HangmanGame.class)
        .hasSize(3);
  }

}
