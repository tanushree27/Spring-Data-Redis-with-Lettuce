package com.redis.cache.springredisexample;

import com.redis.cache.springredisexample.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Map;

public interface UserRepository extends CrudRepository<User, String> {

}
