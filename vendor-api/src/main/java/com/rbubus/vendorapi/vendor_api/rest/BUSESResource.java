package com.rbubus.vendorapi.vendor_api.rest;

import com.rbubus.vendorapi.vendor_api.model.BUSESDTO;
import com.rbubus.vendorapi.vendor_api.service.BUSESService;
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
@RequestMapping(value = "/api/bUSESs", produces = MediaType.APPLICATION_JSON_VALUE)
public class BUSESResource {

    private final BUSESService bUSESService;

    public BUSESResource(final BUSESService bUSESService) {
        this.bUSESService = bUSESService;
    }

    @GetMapping
    public ResponseEntity<List<BUSESDTO>> getAllBUSESs() {
        return ResponseEntity.ok(bUSESService.findAll());
    }

    @GetMapping("/{busid}")
    public ResponseEntity<BUSESDTO> getBUSES(@PathVariable final Long busid) {
        return ResponseEntity.ok(bUSESService.get(busid));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createBUSES(@RequestBody @Valid final BUSESDTO bUSESDTO) {
        return new ResponseEntity<>(bUSESService.create(bUSESDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{busid}")
    public ResponseEntity<Void> updateBUSES(@PathVariable final Long busid,
            @RequestBody @Valid final BUSESDTO bUSESDTO) {
        bUSESService.update(busid, bUSESDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{busid}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteBUSES(@PathVariable final Long busid) {
        bUSESService.delete(busid);
        return ResponseEntity.noContent().build();
    }

}
