package com.javahomeworkone.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired private CategoryService service;

    @GetMapping("category/categories")
    public String showCategoryList(Model model){
        List<Category> categoryList = service.listAll();
        model.addAttribute("categoryList", categoryList);
        return "category/categories";
    }

    @GetMapping("/category/categories/new")
    public String showNewForm(Model model){
        model.addAttribute("category", new Category());
        model.addAttribute("pageTitle", "Add new category");
        return "category/category_form";
    }

    @PostMapping("/categories/save")
    public String saveCategory(Category category, RedirectAttributes ra){
        service.save(category);
        ra.addFlashAttribute("message", "The category has been saved successfully");
        return "redirect:/category/categories";
    }

    @GetMapping("/category/categories/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try {
            Category category = service.get(id);
            model.addAttribute("category", category);
            model.addAttribute("pageTitle", "Edit category (ID: " + id + ")");
            return "category/category_form";
        }catch (CategoryNotFoundException e){
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/category/categories";
        }
    }

    @GetMapping("/category/categories/delete/{id}")
    public String deleteCategory(@PathVariable("id") Integer id, RedirectAttributes ra){
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "The category id: " + id + " has been deleted");
        }catch (CategoryNotFoundException e){
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/category/categories";
    }
}
