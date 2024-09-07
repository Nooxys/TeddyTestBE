package CiroVitiello.TeddyTestBE.controllers;

import CiroVitiello.TeddyTestBE.DTO.NewUserDTO;
import CiroVitiello.TeddyTestBE.entities.User;
import CiroVitiello.TeddyTestBE.exceptions.BadRequestException;
import CiroVitiello.TeddyTestBE.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import CiroVitiello.TeddyTestBE.repositories.UserDAO;


import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService us;

    @Autowired
    private UserDAO ur;

    @GetMapping
    public List<User> findUsers() {
      return this.us.getAllUsers();
    }

    @PostMapping
    public User addUser(@RequestBody @Validated NewUserDTO user, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return this.us.save(user);
    }

    @PutMapping("/{userId}")
    public User updateUser(@RequestBody @Validated  NewUserDTO user, BindingResult validation,  @PathVariable  int userId) {
        if (validation.hasErrors()) {
            throw  new BadRequestException(validation.getAllErrors());
        }
        return this.us.findByIdAndUpdate(user, userId);
    }

    @GetMapping("/{userId}")
    public User findUserById(@PathVariable int userId) {
        return us.findById(userId);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findUserByIdAndDelete(@PathVariable int userId) {
        us.findByIdAndDelete(userId);
    }


}


