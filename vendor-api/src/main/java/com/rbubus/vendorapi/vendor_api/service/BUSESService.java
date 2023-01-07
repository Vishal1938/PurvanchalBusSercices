package com.rbubus.vendorapi.vendor_api.service;

import com.rbubus.vendorapi.vendor_api.domain.BUSES;
import com.rbubus.vendorapi.vendor_api.domain.BUSTYPE;
import com.rbubus.vendorapi.vendor_api.domain.BUSVENDOR;
import com.rbubus.vendorapi.vendor_api.model.BUSESDTO;
import com.rbubus.vendorapi.vendor_api.repos.BUSESRepository;
import com.rbubus.vendorapi.vendor_api.repos.BUSTYPERepository;
import com.rbubus.vendorapi.vendor_api.repos.BUSVENDORRepository;
import com.rbubus.vendorapi.vendor_api.util.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class BUSESService {

    private final BUSESRepository bUSESRepository;
    private final BUSTYPERepository bUSTYPERepository;
    private final BUSVENDORRepository bUSVENDORRepository;

    public BUSESService(final BUSESRepository bUSESRepository,
            final BUSTYPERepository bUSTYPERepository,
            final BUSVENDORRepository bUSVENDORRepository) {
        this.bUSESRepository = bUSESRepository;
        this.bUSTYPERepository = bUSTYPERepository;
        this.bUSVENDORRepository = bUSVENDORRepository;
    }

    public List<BUSESDTO> findAll() {
        final List<BUSES> bUSESs = bUSESRepository.findAll(Sort.by("busid"));
        return bUSESs.stream()
                .map((bUSES) -> mapToDTO(bUSES, new BUSESDTO()))
                .collect(Collectors.toList());
    }

    public BUSESDTO get(final Long busid) {
        return bUSESRepository.findById(busid)
                .map(bUSES -> mapToDTO(bUSES, new BUSESDTO()))
                .orElseThrow(() -> new NotFoundException());
    }

    public Long create(final BUSESDTO bUSESDTO) {
        final BUSES bUSES = new BUSES();
        mapToEntity(bUSESDTO, bUSES);
        return bUSESRepository.save(bUSES).getBusid();
    }

    public void update(final Long busid, final BUSESDTO bUSESDTO) {
        final BUSES bUSES = bUSESRepository.findById(busid)
                .orElseThrow(() -> new NotFoundException());
        mapToEntity(bUSESDTO, bUSES);
        bUSESRepository.save(bUSES);
    }

    public void delete(final Long busid) {
        bUSESRepository.deleteById(busid);
    }

    private BUSESDTO mapToDTO(final BUSES bUSES, final BUSESDTO bUSESDTO) {
        bUSESDTO.setBusid(bUSES.getBusid());
        bUSESDTO.setBusname(bUSES.getBusname());
        bUSESDTO.setSeatcapacity(bUSES.getSeatcapacity());
        bUSESDTO.setSleeperid(bUSES.getSleeperid());
        bUSESDTO.setSleeperud(bUSES.getSleeperud());
        bUSESDTO.setBusdiscription(bUSES.getBusdiscription());
        bUSESDTO.setBustypeid(bUSES.getBustypeid() == null ? null : bUSES.getBustypeid().getBusid());
        bUSESDTO.setVendoridfk(bUSES.getVendoridfk() == null ? null : bUSES.getVendoridfk().getVendorid());
        return bUSESDTO;
    }

    private BUSES mapToEntity(final BUSESDTO bUSESDTO, final BUSES bUSES) {
        bUSES.setBusname(bUSESDTO.getBusname());
        bUSES.setSeatcapacity(bUSESDTO.getSeatcapacity());
        bUSES.setSleeperid(bUSESDTO.getSleeperid());
        bUSES.setSleeperud(bUSESDTO.getSleeperud());
        bUSES.setBusdiscription(bUSESDTO.getBusdiscription());
        final BUSTYPE bustypeid = bUSESDTO.getBustypeid() == null ? null : bUSTYPERepository.findById(bUSESDTO.getBustypeid())
                .orElseThrow(() -> new NotFoundException("bustypeid not found"));
        bUSES.setBustypeid(bustypeid);
        final BUSVENDOR vendoridfk = bUSESDTO.getVendoridfk() == null ? null : bUSVENDORRepository.findById(bUSESDTO.getVendoridfk())
                .orElseThrow(() -> new NotFoundException("vendoridfk not found"));
        bUSES.setVendoridfk(vendoridfk);
        return bUSES;
    }

}
