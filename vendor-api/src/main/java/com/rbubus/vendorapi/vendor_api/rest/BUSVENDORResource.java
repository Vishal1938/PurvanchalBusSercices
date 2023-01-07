package com.rbubus.vendorapi.vendor_api.rest;

import com.rbubus.vendorapi.vendor_api.model.BUSVENDORDTO;
import com.rbubus.vendorapi.vendor_api.service.BUSVENDORService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/bUSVENDORs", produces = MediaType.APPLICATION_JSON_VALUE)
public class BUSVENDORResource {

    private final BUSVENDORService bUSVENDORService;

    public BUSVENDORResource(final BUSVENDORService bUSVENDORService) {
        this.bUSVENDORService = bUSVENDORService;
    }

    @GetMapping
    public ResponseEntity<List<BUSVENDORDTO>> getAllBUSVENDORs() {
        return ResponseEntity.ok(bUSVENDORService.findAll());
    }

    @GetMapping("/{vendorid}")
    public ResponseEntity<BUSVENDORDTO> getBUSVENDOR(@PathVariable final Long vendorid) {
        return ResponseEntity.ok(bUSVENDORService.get(vendorid));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createBUSVENDOR(
            @RequestBody @Valid final BUSVENDORDTO bUSVENDORDTO) {
        return new ResponseEntity<>(bUSVENDORService.create(bUSVENDORDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{vendorid}")
    public ResponseEntity<Void> updateBUSVENDOR(@PathVariable final Long vendorid,
            @RequestBody @Valid final BUSVENDORDTO bUSVENDORDTO) {
        bUSVENDORService.update(vendorid, bUSVENDORDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{vendorid}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteBUSVENDOR(@PathVariable final Long vendorid) {
        bUSVENDORService.delete(vendorid);
        return ResponseEntity.noContent().build();
    }

}
