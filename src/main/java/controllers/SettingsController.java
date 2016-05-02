package controllers;

import config.IsFilled;
import config.Settings;
import exceptions.InternalException;
import exceptions.NotFoundException;
import models.SettingsModel;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Контроллер настроек приложения
 */
@Controller
public class SettingsController {
    /**
     * Стартовая страница с настройками
     */
    @RequestMapping(value = {"/init-settings" }, method = RequestMethod.GET)
    public String initSettings(Model model) {
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        IsFilled isFilled = (IsFilled) ctx.getBean("isFilled");
        if (isFilled.isFilled() == null) {
            isFilled.setIsFilled(Settings.isAllFilled());
        }
        if (isFilled.isFilled()) {
            return "redirect:/";
        }

        SettingsModel model1 = SettingsModel.findById(SettingsModel.UPLOAD_PATH);
        SettingsModel model2 = SettingsModel.findById(SettingsModel.UPLOAD_REQUEST_PATH);
        if (model1 == null) {
            model1 = new SettingsModel(SettingsModel.UPLOAD_PATH);
        }
        if (model2 == null) {
            model2 = new SettingsModel(SettingsModel.UPLOAD_REQUEST_PATH);
        }
        model.addAttribute("model1", model1);
        model.addAttribute("model2", model2);

        HashMap<String, String> dbProperties = Settings.getDbProperties();
        model.addAttribute("dbProperties", dbProperties);

        HashMap<String, String> adProperties = Settings.getADProperties();
        model.addAttribute("adProperties", adProperties);

        model.addAttribute("pageTitle", "Настройки");
        return "setting/init-settings";
    }

    @RequestMapping(value = {"/settings-init-save" }, method = RequestMethod.POST)
    public String settingsInitSave(
            @RequestParam String catalogPath,
            @RequestParam String requestPath,
            @RequestParam String dbUrl,
            @RequestParam String dbUser,
            @RequestParam(value="dbPass", required=false) String dbPass,
            @RequestParam(value="dbPool", required=true) Integer dbPool,
            @RequestParam String ldapUrl,
            @RequestParam String ldapManager,
            @RequestParam String ldapPassword,
            @RequestParam String ldapUserFilter,
            @RequestParam String ldapGroupSearch,
            @RequestParam String ldapGroupFilter,
            @RequestParam String ldapRole,
            RedirectAttributes attr
    ) {
        HashMap<String, String> errors = new HashMap<>();

        File path1 = new File(catalogPath);
        if (!path1.exists() || !path1.isDirectory()) {
            errors.put("catalogPath", "Такой путь не найден");
        } else {
            if (!path1.canRead()) {
                errors.put("catalogPath", "Нет прав на чтение");
            } else if (!path1.canWrite()) {
                errors.put("catalogPath", "Нет прав на запись");
            } else {
                SettingsModel catalogModel = SettingsModel.findById(SettingsModel.UPLOAD_PATH);
                if (catalogModel != null) {
                    catalogModel.setValue(path1.getAbsolutePath());
                    try {
                        catalogModel.update();
                    } catch (SQLException e) {
                        throw new InternalException("Ошибка при сохранении пути в БД");
                    }
                }

            }
        }

        File path2 = new File(requestPath);
        if (!path2.exists() || !path2.isDirectory()) {
            errors.put("requestPath", "Такой путь не найден");
        } else {
            if (!path2.canRead()) {
                errors.put("requestPath", "Нет прав на чтение");
            } else if (!path2.canWrite()) {
                errors.put("requestPath", "Нет прав на запись");
            } else {
                SettingsModel requestModel = SettingsModel.findById(SettingsModel.UPLOAD_REQUEST_PATH);
                if (requestModel != null) {
                    requestModel.setValue(path2.getAbsolutePath());
                    try {
                        requestModel.update();
                    } catch (SQLException e) {
                        throw new InternalException("Ошибка при сохранении пути в БД");
                    }
                }

            }
        }

        Settings.setDbProperties(dbUrl, dbUser, dbPass, dbPool);

        Settings.setAdProperties(ldapUrl, ldapManager, ldapPassword, ldapUserFilter, ldapGroupSearch, ldapGroupFilter, ldapRole);

        if (errors.size() > 0) {
            attr.addFlashAttribute("errors", errors);
            return "redirect:/init-settings";
        }
        return "redirect:/";
    }
}
