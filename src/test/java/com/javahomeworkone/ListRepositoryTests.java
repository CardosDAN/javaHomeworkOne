package com.javahomeworkone;

import com.javahomeworkone.category.Category;
import com.javahomeworkone.category.CategoryRepository;
import com.javahomeworkone.list.List;
import com.javahomeworkone.list.ListRepository;
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
public class ListRepositoryTests {
    @Autowired private ListRepository listRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private CategoryRepository categoryRepository;

    @Test
    public void testAddNew(){
        List list = new List();
        list.setTitle("this is a test");
        list.setText("this is a test");

        Integer userId = 14;
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.get();
        list.setUser(user);

        Integer categoryId = 1;
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        Category category = optionalCategory.get();
        list.setCategory(category);

        List savedList = listRepository.save(list);

        Assertions.assertThat(savedList).isNotNull();
        Assertions.assertThat(savedList.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAll(){
        Iterable<List> lists = listRepository.findAll();
        Assertions.assertThat(lists).hasSizeGreaterThan(0);

        for (List list : lists){
            System.out.println(list);
        }
    }

    @Test
    public void testUpdate(){
        Integer listId = 4;
        Optional<List> optionalList = listRepository.findById(listId);
        List list = optionalList.get();
        list.setTitle("updated title");
        listRepository.save(list);

        List updatedList = listRepository.findById(listId).get();
        Assertions.assertThat(updatedList.getTitle()).isEqualTo("updated title");
    }

    @Test
    public void testGet(){
        Integer listId = 4;
        Optional<List> optionalList = listRepository.findById(listId);
        Assertions.assertThat(optionalList).isPresent();
        System.out.println(optionalList.get());
    }

    @Test
    public void testDelete(){
        Integer listId = 4;
        listRepository.deleteById(listId);

        Optional<List> optionalList = listRepository.findById(listId);
        Assertions.assertThat(optionalList).isNotPresent();
    }
}
