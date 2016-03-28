package controllers;

import models.CategoryModel;
import models.FileModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Контроллер главной страницы для администратора
 */
@Controller
public class AdminIndexController {

    /**
     * Dashboard
     * @param model
     * @return Путь до представления
     */
    @RequestMapping(value = { "/admin" }, method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("pageTitle", "Index");

        int fileCount = FileModel.getCount();
        model.addAttribute("fileCount", fileCount);

        int categoryCount = CategoryModel.getCount();
        model.addAttribute("categoryCount", categoryCount);

        return "dashboard/index";
    }
}
