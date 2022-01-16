package spring.ezservice.providers.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import spring.ezservice.providers.models.User;
import spring.ezservice.providers.repositories.UserRepository;
import spring.ezservice.providers.services.UserService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

//    @RequestMapping(value = "/user",method = RequestMethod.POST)
//    public void save(@Valid @RequestBody User user)
//    {
//        userRepository.save(user);
//    }
//@RequestMapping("/user")
//public ResponseEntity<User> createUser(@Valid @RequestBody User user){
//    User savedUser = userRepository.save(user);
//    return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
//}
@RequestMapping(value="/user",method = RequestMethod.POST)
@ResponseStatus(HttpStatus.CREATED)
public ResponseEntity<?> registerUser(@Valid @RequestBody User user){

    // add check for username exists in a DB
    if(userService.isUserNameExists(user.getUserName())){
        return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
    }

    // add check for email exists in DB
//    if(userRepository.existsByEmail(signUpDto.getEmail())){
//        return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
//    }

    // create user object
//    User user = new User();
//    user.setName(signUpDto.getName());
//    user.setUsername(signUpDto.getUsername());
//    user.setEmail(signUpDto.getEmail());
//    user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

//    Role roles = roleRepository.findByName("ROLE_ADMIN").get();
//    user.setRoles(Collections.singleton(roles));

    userRepository.save(user);

    return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

}


    @RequestMapping(value = "/",method = RequestMethod.GET)
    public List<User> getUsers()
    {
        return userRepository.findAll();
    }

    @RequestMapping(value="/user/{id}",method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable Long id)
    {
        userRepository.deleteById(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        return errors;
    }

}
