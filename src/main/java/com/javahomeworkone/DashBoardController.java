package com.javahomeworkone;

import com.javahomeworkone.list.ListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lists")
public class DashBoardController {

    @Autowired
    ListRepository listRepository;

    @GetMapping("/category/{categoryId}")
    public List<com.javahomeworkone.list.List> getListByCategory(@PathVariable int categoryId){
        var user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return listRepository.findByCategoryIdAndUser(categoryId, ((CustomUserDetails) user).user);
    }
}
