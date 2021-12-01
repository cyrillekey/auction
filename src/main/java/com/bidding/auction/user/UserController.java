package com.bidding.auction.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.bidding.auction.exception.FieldNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;


    @GetMapping(path="/get-all-users")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    @GetMapping(path="/find-by-id/{id}")
    public User findUserById(@PathVariable Integer id){
        Optional<User> userFound=userRepository.findById(id);
        if(!userFound.isPresent()){
            throw new FieldNotFoundException("user not found id="+id);
        }
        return userFound.get();
    }
    @PostMapping("/register-new")
    public ResponseEntity<Object> saveNewUser(@Valid @RequestBody User newUser){
        User saveduser=userRepository.save(newUser);
        URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saveduser.getUserId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
