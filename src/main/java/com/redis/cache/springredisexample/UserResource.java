package com.redis.cache.springredisexample;

import com.redis.cache.springredisexample.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@EnableCaching
@RestController
@RequestMapping("/rest/user")
public class UserResource {

    @Autowired
    private UserRepository userRepository;

    public UserResource(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/add/{id}/{name}")
    @ResponseBody
    public Optional<User> add(@PathVariable("id") final String id, @PathVariable("name") final String name){

        userRepository.save(new User(id, name, 20000L));
        return userRepository.findById(id);
    }

    @GetMapping("/update/{id}/{name}")
    public Optional<User> update(@PathVariable("id") final String id,
                    @PathVariable("name") final String name){

        userRepository.save(new User(id, name, 1000L));
        System.out.println("Called newUser from DB");
        return userRepository.findById(id);
    }

    @GetMapping("/newUser/{id}/{name}")
    @Cacheable(key = "#id", value = "USER")
    public Optional<User> newUser(@PathVariable("id") final String id,
                       @PathVariable("name") final String name){

        userRepository.save(new User(id, name, 1000L));
        System.out.println("Called newUser from DB");
        return userRepository.findById(id);
    }

    @GetMapping("/delete/{id}")
    public Iterable<User> delete(@PathVariable("id") final String id){

        userRepository.deleteById(id);
        return all();
    }

    @GetMapping("/all")
    public Iterable<User> all(){

        return userRepository.findAll();
    }


}
