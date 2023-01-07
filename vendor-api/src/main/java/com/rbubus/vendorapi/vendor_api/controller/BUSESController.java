package com.rbubus.vendorapi.vendor_api.controller;

import com.rbubus.vendorapi.vendor_api.domain.BUSTYPE;
import com.rbubus.vendorapi.vendor_api.domain.BUSVENDOR;
import com.rbubus.vendorapi.vendor_api.model.BUSESDTO;
import com.rbubus.vendorapi.vendor_api.repos.BUSTYPERepository;
import com.rbubus.vendorapi.vendor_api.repos.BUSVENDORRepository;
import com.rbubus.vendorapi.vendor_api.service.BUSESService;
import com.rbubus.vendorapi.vendor_api.util.WebUtils;
import jakarta.validation.Valid;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
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
@RequestMapping("/bUSESs")
public class BUSESController {

    private final BUSESService bUSESService;
    private final BUSTYPERepository bUSTYPERepository;
    private final BUSVENDORRepository bUSVENDORRepository;

    public BUSESController(final BUSESService bUSESService,
            final BUSTYPERepository bUSTYPERepository,
            final BUSVENDORRepository bUSVENDORRepository) {
        this.bUSESService = bUSESService;
        this.bUSTYPERepository = bUSTYPERepository;
        this.bUSVENDORRepository = bUSVENDORRepository;
    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("bustypeidValues", bUSTYPERepository.findAll(Sort.by("busid"))
                .stream()
                .collect(Collectors.toMap(BUSTYPE::getBusid, BUSTYPE::getBustypename)));
        model.addAttribute("vendoridfkValues", bUSVENDORRepository.findAll(Sort.by("vendorid"))
                .stream()
                .collect(Collectors.toMap(BUSVENDOR::getVendorid, BUSVENDOR::getVendorname)));
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("bUSESs", bUSESService.findAll());
        return "bUSES/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("bUSES") final BUSESDTO bUSESDTO) {
        return "bUSES/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("bUSES") @Valid final BUSESDTO bUSESDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "bUSES/add";
        }
        bUSESService.create(bUSESDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("bUSES.create.success"));
        return "redirect:/bUSESs";
    }

    @GetMapping("/edit/{busid}")
    public String edit(@PathVariable final Long busid, final Model model) {
        model.addAttribute("bUSES", bUSESService.get(busid));
        return "bUSES/edit";
    }

    @PostMapping("/edit/{busid}")
    public String edit(@PathVariable final Long busid,
            @ModelAttribute("bUSES") @Valid final BUSESDTO bUSESDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "bUSES/edit";
        }
        bUSESService.update(busid, bUSESDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("bUSES.update.success"));
        return "redirect:/bUSESs";
    }

    @PostMapping("/delete/{busid}")
    public String delete(@PathVariable final Long busid,
            final RedirectAttributes redirectAttributes) {
        bUSESService.delete(busid);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("bUSES.delete.success"));
        return "redirect:/bUSESs";
    }

}
