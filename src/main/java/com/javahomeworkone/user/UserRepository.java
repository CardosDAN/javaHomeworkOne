package com.javahomeworkone.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {
    public long countById(Integer id);

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findByEmail(String email);
}
