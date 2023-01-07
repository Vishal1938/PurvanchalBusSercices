package com.rbubus.vendorapi.vendor_api.controller;

import com.rbubus.vendorapi.vendor_api.model.BUSTYPEDTO;
import com.rbubus.vendorapi.vendor_api.service.BUSTYPEService;
import com.rbubus.vendorapi.vendor_api.util.WebUtils;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/bUSTYPEs")
public class BUSTYPEController {

    private final BUSTYPEService bUSTYPEService;

    public BUSTYPEController(final BUSTYPEService bUSTYPEService) {
        this.bUSTYPEService = bUSTYPEService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("bUSTYPEs", bUSTYPEService.findAll());
        return "bUSTYPE/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("bUSTYPE") final BUSTYPEDTO bUSTYPEDTO) {
        return "bUSTYPE/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("bUSTYPE") @Valid final BUSTYPEDTO bUSTYPEDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "bUSTYPE/add";
        }
        bUSTYPEService.create(bUSTYPEDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("bUSTYPE.create.success"));
        return "redirect:/bUSTYPEs";
    }

    @GetMapping("/edit/{busid}")
    public String edit(@PathVariable final Long busid, final Model model) {
        model.addAttribute("bUSTYPE", bUSTYPEService.get(busid));
        return "bUSTYPE/edit";
    }

    @PostMapping("/edit/{busid}")
    public String edit(@PathVariable final Long busid,
            @ModelAttribute("bUSTYPE") @Valid final BUSTYPEDTO bUSTYPEDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "bUSTYPE/edit";
        }
        bUSTYPEService.update(busid, bUSTYPEDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("bUSTYPE.update.success"));
        return "redirect:/bUSTYPEs";
    }

    @PostMapping("/delete/{busid}")
    public String delete(@PathVariable final Long busid,
            final RedirectAttributes redirectAttributes) {
        final String referencedWarning = bUSTYPEService.getReferencedWarning(busid);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR, referencedWarning);
        } else {
            bUSTYPEService.delete(busid);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("bUSTYPE.delete.success"));
        }
        return "redirect:/bUSTYPEs";
    }

}
