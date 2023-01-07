package com.rbubus.vendorapi.vendor_api.service;

import com.rbubus.vendorapi.vendor_api.domain.BUSTYPE;
import com.rbubus.vendorapi.vendor_api.model.BUSTYPEDTO;
import com.rbubus.vendorapi.vendor_api.repos.BUSTYPERepository;
import com.rbubus.vendorapi.vendor_api.util.NotFoundException;
import com.rbubus.vendorapi.vendor_api.util.WebUtils;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class BUSTYPEService {

    private final BUSTYPERepository bUSTYPERepository;

    public BUSTYPEService(final BUSTYPERepository bUSTYPERepository) {
        this.bUSTYPERepository = bUSTYPERepository;
    }

    public List<BUSTYPEDTO> findAll() {
        final List<BUSTYPE> bUSTYPEs = bUSTYPERepository.findAll(Sort.by("busid"));
        return bUSTYPEs.stream()
                .map((bUSTYPE) -> mapToDTO(bUSTYPE, new BUSTYPEDTO()))
                .collect(Collectors.toList());
    }

    public BUSTYPEDTO get(final Long busid) {
        return bUSTYPERepository.findById(busid)
                .map(bUSTYPE -> mapToDTO(bUSTYPE, new BUSTYPEDTO()))
                .orElseThrow(() -> new NotFoundException());
    }

    public Long create(final BUSTYPEDTO bUSTYPEDTO) {
        final BUSTYPE bUSTYPE = new BUSTYPE();
        mapToEntity(bUSTYPEDTO, bUSTYPE);
        return bUSTYPERepository.save(bUSTYPE).getBusid();
    }

    public void update(final Long busid, final BUSTYPEDTO bUSTYPEDTO) {
        final BUSTYPE bUSTYPE = bUSTYPERepository.findById(busid)
                .orElseThrow(() -> new NotFoundException());
        mapToEntity(bUSTYPEDTO, bUSTYPE);
        bUSTYPERepository.save(bUSTYPE);
    }

    public void delete(final Long busid) {
        bUSTYPERepository.deleteById(busid);
    }

    private BUSTYPEDTO mapToDTO(final BUSTYPE bUSTYPE, final BUSTYPEDTO bUSTYPEDTO) {
        bUSTYPEDTO.setBusid(bUSTYPE.getBusid());
        bUSTYPEDTO.setBustypename(bUSTYPE.getBustypename());
        bUSTYPEDTO.setDescription(bUSTYPE.getDescription());
        bUSTYPEDTO.setIsactive(bUSTYPE.getIsactive());
        return bUSTYPEDTO;
    }

    private BUSTYPE mapToEntity(final BUSTYPEDTO bUSTYPEDTO, final BUSTYPE bUSTYPE) {
        bUSTYPE.setBustypename(bUSTYPEDTO.getBustypename());
        bUSTYPE.setDescription(bUSTYPEDTO.getDescription());
        bUSTYPE.setIsactive(bUSTYPEDTO.getIsactive());
        return bUSTYPE;
    }

    @Transactional
    public String getReferencedWarning(final Long busid) {
        final BUSTYPE bUSTYPE = bUSTYPERepository.findById(busid)
                .orElseThrow(() -> new NotFoundException());
        if (!bUSTYPE.getBustypeidBUSESs().isEmpty()) {
            return WebUtils.getMessage("bUSTYPE.bUSES.oneToMany.referenced", bUSTYPE.getBustypeidBUSESs().iterator().next().getBusid());
        }
        return null;
    }

}
