package com.example.redisspringdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/rest/user")
public class UserResource {

    @Autowired
    private UserRepository userRepository;

   @RequestMapping(method = RequestMethod.POST, value = "/auth/id={id}")
   public ResponseEntity<User> login(
           @RequestBody User user,
           @PathVariable(value = "id", required = false) Integer id){
       if(Objects.nonNull(userRepository.findByName(user.getName()))){
        if(userRepository.findByName(user.getName()).getPassword().equals(user.getPassword())){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
       }
       return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

   }
    @RequestMapping(method = RequestMethod.POST, value = "/api/add")
    public ResponseEntity<User> add(@RequestBody User user){
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
