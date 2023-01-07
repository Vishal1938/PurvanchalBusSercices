package com.rbubus.vendorapi.vendor_api.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.Set;


@Entity
public class BUSVENDOR {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vendorid;

    @Column(nullable = false)
    private String vendorname;

    @Column(nullable = false)
    private String vendordescription;

    @Column(nullable = false)
    private String vendorCategory;

    @Column(nullable = false, unique = true)
    private String vendorpan;

    @Column(nullable = false, unique = true)
    private String vendorgst;

    @Column(nullable = false)
    private String businessname;

    @Column
    private String state;

    @OneToMany(mappedBy = "vendoridfk")
    private Set<BUSES> vendoridfkBUSESs;

    public Long getVendorid() {
        return vendorid;
    }

    public void setVendorid(final Long vendorid) {
        this.vendorid = vendorid;
    }

    public String getVendorname() {
        return vendorname;
    }

    public void setVendorname(final String vendorname) {
        this.vendorname = vendorname;
    }

    public String getVendordescription() {
        return vendordescription;
    }

    public void setVendordescription(final String vendordescription) {
        this.vendordescription = vendordescription;
    }

    public String getVendorCategory() {
        return vendorCategory;
    }

    public void setVendorCategory(final String vendorCategory) {
        this.vendorCategory = vendorCategory;
    }

    public String getVendorpan() {
        return vendorpan;
    }

    public void setVendorpan(final String vendorpan) {
        this.vendorpan = vendorpan;
    }

    public String getVendorgst() {
        return vendorgst;
    }

    public void setVendorgst(final String vendorgst) {
        this.vendorgst = vendorgst;
    }

    public String getBusinessname() {
        return businessname;
    }

    public void setBusinessname(final String businessname) {
        this.businessname = businessname;
    }

    public String getState() {
        return state;
    }

    public void setState(final String state) {
        this.state = state;
    }

    public Set<BUSES> getVendoridfkBUSESs() {
        return vendoridfkBUSESs;
    }

    public void setVendoridfkBUSESs(final Set<BUSES> vendoridfkBUSESs) {
        this.vendoridfkBUSESs = vendoridfkBUSESs;
    }

}
