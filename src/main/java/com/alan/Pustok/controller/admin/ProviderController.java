package com.alan.Pustok.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alan.Pustok.dto.ProviderDto;
import com.alan.Pustok.service.ProviderService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/provider")
public class ProviderController {
    @Autowired
    private ProviderService providerService;

    @GetMapping("")
    public String getProvider(Model model) {
        model.addAttribute("allProviders", providerService.findAll());
        return "admin/provider/show";
    }

    @GetMapping("/create")
    public String createProvider(Model model) {
        model.addAttribute("newProvider", new ProviderDto());
        return "admin/provider/create";
    }

    @PostMapping("/create")
    public String createProviderPost(@ModelAttribute("newProvider") @Valid ProviderDto providerDto,
            BindingResult result,
            @RequestParam("file") MultipartFile file) {
        if (result.hasErrors()) {
            return "admin/provider/create";
        }
        providerService.create(providerDto, file);
        return "redirect:/admin/provider";
    }

    @GetMapping("/update/{id}")
    public String updateProvider(@PathVariable int id, Model model) {
        model.addAttribute("newProvider", new ProviderDto());
        model.addAttribute("currentProvider", providerService.findProviderById(id));
        return "admin/provider/update";
    }

    @PostMapping("/update/{id}")
    public String updateProviderPost(@PathVariable int id,
            @ModelAttribute("newProvider") @Valid ProviderDto providerDto, BindingResult result,
            @RequestParam("file") MultipartFile file, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("currentProvider", providerService.findProviderById(id));
            return "admin/provider/update";
        }
        providerService.update(id, providerDto, file);
        return "redirect:/admin/provider";
    }

    @GetMapping("/delete/{id}")
    public String getMethodName(@PathVariable int id) {
        providerService.delete(id);
        return "redirect:/admin/provider";
    }
}
