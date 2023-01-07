package com.rbubus.vendorapi.vendor_api.controller;

import com.rbubus.vendorapi.vendor_api.model.BUSVENDORDTO;
import com.rbubus.vendorapi.vendor_api.service.BUSVENDORService;
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
@RequestMapping("/bUSVENDORs")
public class BUSVENDORController {

    private final BUSVENDORService bUSVENDORService;

    public BUSVENDORController(final BUSVENDORService bUSVENDORService) {
        this.bUSVENDORService = bUSVENDORService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("bUSVENDORs", bUSVENDORService.findAll());
        return "bUSVENDOR/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("bUSVENDOR") final BUSVENDORDTO bUSVENDORDTO) {
        return "bUSVENDOR/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("bUSVENDOR") @Valid final BUSVENDORDTO bUSVENDORDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (!bindingResult.hasFieldErrors("vendorpan") && bUSVENDORService.vendorpanExists(bUSVENDORDTO.getVendorpan())) {
            bindingResult.rejectValue("vendorpan", "Exists.bUSVENDOR.vendorpan");
        }
        if (!bindingResult.hasFieldErrors("vendorgst") && bUSVENDORService.vendorgstExists(bUSVENDORDTO.getVendorgst())) {
            bindingResult.rejectValue("vendorgst", "Exists.bUSVENDOR.vendorgst");
        }
        if (bindingResult.hasErrors()) {
            return "bUSVENDOR/add";
        }
        bUSVENDORService.create(bUSVENDORDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("bUSVENDOR.create.success"));
        return "redirect:/bUSVENDORs";
    }

    @GetMapping("/edit/{vendorid}")
    public String edit(@PathVariable final Long vendorid, final Model model) {
        model.addAttribute("bUSVENDOR", bUSVENDORService.get(vendorid));
        return "bUSVENDOR/edit";
    }

    @PostMapping("/edit/{vendorid}")
    public String edit(@PathVariable final Long vendorid,
            @ModelAttribute("bUSVENDOR") @Valid final BUSVENDORDTO bUSVENDORDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (!bindingResult.hasFieldErrors("vendorpan") &&
                !bUSVENDORService.get(vendorid).getVendorpan().equalsIgnoreCase(bUSVENDORDTO.getVendorpan()) &&
                bUSVENDORService.vendorpanExists(bUSVENDORDTO.getVendorpan())) {
            bindingResult.rejectValue("vendorpan", "Exists.bUSVENDOR.vendorpan");
        }
        if (!bindingResult.hasFieldErrors("vendorgst") &&
                !bUSVENDORService.get(vendorid).getVendorgst().equalsIgnoreCase(bUSVENDORDTO.getVendorgst()) &&
                bUSVENDORService.vendorgstExists(bUSVENDORDTO.getVendorgst())) {
            bindingResult.rejectValue("vendorgst", "Exists.bUSVENDOR.vendorgst");
        }
        if (bindingResult.hasErrors()) {
            return "bUSVENDOR/edit";
        }
        bUSVENDORService.update(vendorid, bUSVENDORDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("bUSVENDOR.update.success"));
        return "redirect:/bUSVENDORs";
    }

    @PostMapping("/delete/{vendorid}")
    public String delete(@PathVariable final Long vendorid,
            final RedirectAttributes redirectAttributes) {
        final String referencedWarning = bUSVENDORService.getReferencedWarning(vendorid);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR, referencedWarning);
        } else {
            bUSVENDORService.delete(vendorid);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("bUSVENDOR.delete.success"));
        }
        return "redirect:/bUSVENDORs";
    }

}
