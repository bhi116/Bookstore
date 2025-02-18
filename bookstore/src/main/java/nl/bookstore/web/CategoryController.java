package nl.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import nl.bookstore.domain.Category;
import nl.bookstore.domain.CategoryRepository;

@Controller
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/categorylist")
    public String categoryList(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "categorylist";      //categorylist.html
    }

    @GetMapping("/addcategory")     //lisää kategoria
    public String addCategory(Model model){
        model.addAttribute("category", new Category());
        return "addcategory";       //addcategory.html
    }

    @PostMapping("/savecategory")       //tallenna kategoria
    public String save(Category category){
        categoryRepository.save(category);
        return "redirect:categorylist";
    }
}
