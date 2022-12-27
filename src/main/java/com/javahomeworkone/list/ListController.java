package com.javahomeworkone.list;

import com.javahomeworkone.CustomUserDetails;
import com.javahomeworkone.category.Category;
import com.javahomeworkone.category.CategoryService;
import com.javahomeworkone.user.User;
import com.javahomeworkone.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ListController {

    @Autowired private ListService listService;

    @Autowired private CategoryService categoryService;

    @Autowired private UserService userService;

    @GetMapping("/list/lists")
    public String showListsList(Model model){
        List<com.javahomeworkone.list.List> listLists = listService.listAll();
        model.addAttribute("listLists", listLists);
        return "list/lists";
    }

    @GetMapping("/list/show/{id}")
    public String showList(@PathVariable("id")Integer listId, Model model) throws ListNotFoundException {
        com.javahomeworkone.list.List list = listService.get(listId);
        model.addAttribute("list", list);
        List<Category> categoryList = categoryService.listAll();
        model.addAttribute("categoryList", categoryList);
        return "/list/show";
    }

    @GetMapping("/list/lists/new")
    public String showNewForm(Model model){
        List<User> userList = userService.listAll();
        List<Category> categoryList = categoryService.listAll();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("list", new com.javahomeworkone.list.List());
        model.addAttribute("pageTitle", "Add new list");
        return "list/list_form";
    }

    @PostMapping("/lists/save")
    public String saveList(com.javahomeworkone.list.List list, RedirectAttributes ra){
        var user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        list.setUser(((CustomUserDetails) user).user);
        listService.save(list);

        ra.addFlashAttribute("message", "The list has been saved successfully");
        return "redirect:/list/lists";
    }

    @GetMapping("/list/lists/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try{
            com.javahomeworkone.list.List list = listService.get(id);
            model.addAttribute("list", list);
            List<Category> categoryList = categoryService.listAll();
            model.addAttribute("categoryList", categoryList);
            model.addAttribute("pageTitle", " Edit user (ID : " + id  +")");
            return "list/list_form";
        }catch (ListNotFoundException e){
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/list/lists";
        }
    }

    @GetMapping("/list/lists/delete/{id}")
    public String deleteList(@PathVariable("id") Integer id, RedirectAttributes ra){
        try {
            listService.delete(id);
            ra.addFlashAttribute("message", "The list id: " + id + " has been deleted");
        } catch (ListNotFoundException e){
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/list/lists";
    }
}
