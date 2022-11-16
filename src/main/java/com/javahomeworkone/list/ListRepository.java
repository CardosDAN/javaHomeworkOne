package com.javahomeworkone.list;

import com.javahomeworkone.category.Category;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ListRepository extends CrudRepository<com.javahomeworkone.list.List, Integer> {
    public long countById(Integer id);

    java.util.List<com.javahomeworkone.list.List> findByCategory(Category category, Sort sort);
}
