package com.javahomeworkone;

import com.javahomeworkone.category.Category;
import com.javahomeworkone.category.CategoryRepository;
import com.javahomeworkone.user.User;
import com.javahomeworkone.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class CategoryRepositoryTests {

    @Autowired private CategoryRepository categoryRepository;

    @Autowired private UserRepository userRepository;

    @Test
    public void testAddNew(){
        Category category = new Category();
        category.setName("category 1");
        Integer userId = 14;
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.get();
        category.setUser(user);

        Category savedCategory = categoryRepository.save(category);

        Assertions.assertThat(savedCategory).isNotNull();
        Assertions.assertThat(savedCategory.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAll(){
        Iterable<Category> categories = categoryRepository.findAll();
        Assertions.assertThat(categories).hasSizeGreaterThan(0);

        for (Category category : categories){
            System.out.println(category);
        }
    }

    @Test
    public void testUpdate(){
        Integer categoryId = 9;
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        Category category = optionalCategory.get();
        category.setName("updated category");
        categoryRepository.save(category);

        Category updatedCategory = categoryRepository.findById(categoryId).get();
        Assertions.assertThat(updatedCategory.getName()).isEqualTo("updated category");
    }

    @Test
    public void testGet(){
        Integer categoryId = 9;
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        Assertions.assertThat(optionalCategory).isPresent();
        System.out.println(optionalCategory.get());
    }

    @Test
    public void testDelete(){
        Integer categoryId = 9;
        categoryRepository.deleteById(categoryId);

        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        Assertions.assertThat(optionalCategory).isNotPresent();
    }
}
