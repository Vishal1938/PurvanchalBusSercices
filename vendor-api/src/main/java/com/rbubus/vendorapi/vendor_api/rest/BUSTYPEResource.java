package com.rbubus.vendorapi.vendor_api.rest;

import com.rbubus.vendorapi.vendor_api.model.BUSTYPEDTO;
import com.rbubus.vendorapi.vendor_api.service.BUSTYPEService;
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
@RequestMapping(value = "/api/bUSTYPEs", produces = MediaType.APPLICATION_JSON_VALUE)
public class BUSTYPEResource {

    private final BUSTYPEService bUSTYPEService;

    public BUSTYPEResource(final BUSTYPEService bUSTYPEService) {
        this.bUSTYPEService = bUSTYPEService;
    }

    @GetMapping
    public ResponseEntity<List<BUSTYPEDTO>> getAllBUSTYPEs() {
        return ResponseEntity.ok(bUSTYPEService.findAll());
    }

    @GetMapping("/{busid}")
    public ResponseEntity<BUSTYPEDTO> getBUSTYPE(@PathVariable final Long busid) {
        return ResponseEntity.ok(bUSTYPEService.get(busid));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createBUSTYPE(@RequestBody @Valid final BUSTYPEDTO bUSTYPEDTO) {
        return new ResponseEntity<>(bUSTYPEService.create(bUSTYPEDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{busid}")
    public ResponseEntity<Void> updateBUSTYPE(@PathVariable final Long busid,
            @RequestBody @Valid final BUSTYPEDTO bUSTYPEDTO) {
        bUSTYPEService.update(busid, bUSTYPEDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{busid}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteBUSTYPE(@PathVariable final Long busid) {
        bUSTYPEService.delete(busid);
        return ResponseEntity.noContent().build();
    }

}
