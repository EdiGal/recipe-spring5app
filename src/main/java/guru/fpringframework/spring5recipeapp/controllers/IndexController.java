package guru.fpringframework.spring5recipeapp.controllers;

import guru.fpringframework.spring5recipeapp.domain.Category;
import guru.fpringframework.spring5recipeapp.domain.UnitOfMeasure;
import guru.fpringframework.spring5recipeapp.repositories.CategoryRepository;
import guru.fpringframework.spring5recipeapp.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @GetMapping("/")
    public String getIndexPage(Model model){

        Optional<Category> catOpt = categoryRepository.findByDescription("American");
        if(catOpt.isPresent()) {
            Category category = catOpt.get();
            System.out.println(category.getDescription());
            Optional<UnitOfMeasure> unitOpt = unitOfMeasureRepository.findByDescription("Teaspoon");
            UnitOfMeasure unitOfMeasure = unitOpt.get();
            System.out.println(unitOfMeasure.getDescription());
            model.addAttribute("category", category);
            model.addAttribute("unitOfMeasure", unitOfMeasure);
            return "index";
        }
        return "category not found";
    }
}
