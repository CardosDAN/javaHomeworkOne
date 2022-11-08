package com.javahomeworkone.category;

import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
    public long countById(Integer id);

    Category findByListsId(String listid);
}
