package com.javahomeworkone.list;

import com.javahomeworkone.category.Category;

import com.javahomeworkone.user.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ListRepository extends CrudRepository<com.javahomeworkone.list.List, Integer> {
    public long countById(Integer id);

    java.util.List<com.javahomeworkone.list.List> findByCategory(Category category, Sort sort);

    List<com.javahomeworkone.list.List> findByUser(User user, Sort sort);

    @Query(value = "select * from lists l where l.title like %:keyword%", nativeQuery = true)
    List<com.javahomeworkone.list.List> findByKeyword(@Param("keyword") String keyword);
}
