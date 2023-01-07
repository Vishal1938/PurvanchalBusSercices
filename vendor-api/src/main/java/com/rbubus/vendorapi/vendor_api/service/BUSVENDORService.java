package com.rbubus.vendorapi.vendor_api.service;

import com.rbubus.vendorapi.vendor_api.domain.BUSVENDOR;
import com.rbubus.vendorapi.vendor_api.model.BUSVENDORDTO;
import com.rbubus.vendorapi.vendor_api.repos.BUSVENDORRepository;
import com.rbubus.vendorapi.vendor_api.util.NotFoundException;
import com.rbubus.vendorapi.vendor_api.util.WebUtils;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class BUSVENDORService {

    private final BUSVENDORRepository bUSVENDORRepository;

    public BUSVENDORService(final BUSVENDORRepository bUSVENDORRepository) {
        this.bUSVENDORRepository = bUSVENDORRepository;
    }

    public List<BUSVENDORDTO> findAll() {
        final List<BUSVENDOR> bUSVENDORs = bUSVENDORRepository.findAll(Sort.by("vendorid"));
        return bUSVENDORs.stream()
                .map((bUSVENDOR) -> mapToDTO(bUSVENDOR, new BUSVENDORDTO()))
                .collect(Collectors.toList());
    }

    public BUSVENDORDTO get(final Long vendorid) {
        return bUSVENDORRepository.findById(vendorid)
                .map(bUSVENDOR -> mapToDTO(bUSVENDOR, new BUSVENDORDTO()))
                .orElseThrow(() -> new NotFoundException());
    }

    public Long create(final BUSVENDORDTO bUSVENDORDTO) {
        final BUSVENDOR bUSVENDOR = new BUSVENDOR();
        mapToEntity(bUSVENDORDTO, bUSVENDOR);
        return bUSVENDORRepository.save(bUSVENDOR).getVendorid();
    }

    public void update(final Long vendorid, final BUSVENDORDTO bUSVENDORDTO) {
        final BUSVENDOR bUSVENDOR = bUSVENDORRepository.findById(vendorid)
                .orElseThrow(() -> new NotFoundException());
        mapToEntity(bUSVENDORDTO, bUSVENDOR);
        bUSVENDORRepository.save(bUSVENDOR);
    }

    public void delete(final Long vendorid) {
        bUSVENDORRepository.deleteById(vendorid);
    }

    private BUSVENDORDTO mapToDTO(final BUSVENDOR bUSVENDOR, final BUSVENDORDTO bUSVENDORDTO) {
        bUSVENDORDTO.setVendorid(bUSVENDOR.getVendorid());
        bUSVENDORDTO.setVendorname(bUSVENDOR.getVendorname());
        bUSVENDORDTO.setVendordescription(bUSVENDOR.getVendordescription());
        bUSVENDORDTO.setVendorCategory(bUSVENDOR.getVendorCategory());
        bUSVENDORDTO.setVendorpan(bUSVENDOR.getVendorpan());
        bUSVENDORDTO.setVendorgst(bUSVENDOR.getVendorgst());
        bUSVENDORDTO.setBusinessname(bUSVENDOR.getBusinessname());
        bUSVENDORDTO.setState(bUSVENDOR.getState());
        return bUSVENDORDTO;
    }

    private BUSVENDOR mapToEntity(final BUSVENDORDTO bUSVENDORDTO, final BUSVENDOR bUSVENDOR) {
        bUSVENDOR.setVendorname(bUSVENDORDTO.getVendorname());
        bUSVENDOR.setVendordescription(bUSVENDORDTO.getVendordescription());
        bUSVENDOR.setVendorCategory(bUSVENDORDTO.getVendorCategory());
        bUSVENDOR.setVendorpan(bUSVENDORDTO.getVendorpan());
        bUSVENDOR.setVendorgst(bUSVENDORDTO.getVendorgst());
        bUSVENDOR.setBusinessname(bUSVENDORDTO.getBusinessname());
        bUSVENDOR.setState(bUSVENDORDTO.getState());
        return bUSVENDOR;
    }

    public boolean vendorpanExists(final String vendorpan) {
        return bUSVENDORRepository.existsByVendorpanIgnoreCase(vendorpan);
    }

    public boolean vendorgstExists(final String vendorgst) {
        return bUSVENDORRepository.existsByVendorgstIgnoreCase(vendorgst);
    }

    @Transactional
    public String getReferencedWarning(final Long vendorid) {
        final BUSVENDOR bUSVENDOR = bUSVENDORRepository.findById(vendorid)
                .orElseThrow(() -> new NotFoundException());
        if (!bUSVENDOR.getVendoridfkBUSESs().isEmpty()) {
            return WebUtils.getMessage("bUSVENDOR.bUSES.oneToMany.referenced", bUSVENDOR.getVendoridfkBUSESs().iterator().next().getBusid());
        }
        return null;
    }

}
