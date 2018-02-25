package com.example.sqlmaven;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/rest/users")
public class UsersResource {

    @Autowired
    UsersRepository usersRepository;

    @GetMapping(value = "/all")
    public List<Users> getAll(){
        return usersRepository.findAll();
    }

    @PostMapping(value = "/find/{name}/{password}")
    public ResponseEntity<Users> login(
            @PathVariable(value = "name") String name,
            @PathVariable(value = "password") String password) {
        Users user = usersRepository.findByName(name);
        if(Objects.nonNull(user)){
            if(user.getPassword().equals(password))
                return new ResponseEntity<>(user,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }
    @PostMapping(value = "/load")
    public List<Users> persist(@RequestBody final Users users){
        usersRepository.save(users);
        return usersRepository.findAll();
    }
}
