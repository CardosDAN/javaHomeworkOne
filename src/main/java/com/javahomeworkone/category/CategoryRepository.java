package com.javahomeworkone.category;

import com.javahomeworkone.user.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
    public long countById(Integer id);

    List<Category> findByUser(User user, Sort sort);
}
