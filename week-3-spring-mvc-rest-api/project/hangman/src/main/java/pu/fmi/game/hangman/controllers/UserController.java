package pu.fmi.game.hangman.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  @PostMapping
  public String createNewUser(@Valid @RequestBody User user){
    return user.toString();
  }

  @RequestMapping(method = RequestMethod.PUT)
  public String updateUser(
      @RequestParam Long id,
      @RequestParam String firstName,
      @RequestParam(
          required = false,
          defaultValue = "Default L Name") String lastName){

    return "updated" + id + " " + firstName + " " + lastName;
  }

  //@Table
  private static class User {

    @NotNull
    private Long id;

    @NotEmpty
    private String firstName;

    @Size(min = 2, max = 5)
    private String lastName;

    @Override
    public String toString() {
      return "User{" +
          "firstName='" + firstName + '\'' +
          ", lastName='" + lastName + '\'' +
          '}';
    }

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public String getFirstName() {
      return firstName;
    }

    public void setFirstName(String firstName) {
      this.firstName = firstName;
    }

    public String getLastName() {
      return lastName;
    }

    public void setLastName(String lastName) {
      this.lastName = lastName;
    }
  }

}
