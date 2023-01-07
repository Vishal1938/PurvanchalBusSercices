package com.rbubus.vendorapi.vendor_api.repos;

import com.rbubus.vendorapi.vendor_api.domain.BUSVENDOR;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BUSVENDORRepository extends JpaRepository<BUSVENDOR, Long> {

    boolean existsByVendorpanIgnoreCase(String vendorpan);

    boolean existsByVendorgstIgnoreCase(String vendorgst);

}
