package com.rbubus.vendorapi.vendor_api.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class BUSVENDORDTO {

    private Long vendorid;

    @NotNull
    @Size(max = 255)
    private String vendorname;

    @NotNull
    @Size(max = 255)
    private String vendordescription;

    @NotNull
    @Size(max = 255)
    private String vendorCategory;

    @NotNull
    @Size(max = 255)
    private String vendorpan;

    @NotNull
    @Size(max = 255)
    private String vendorgst;

    @NotNull
    @Size(max = 255)
    private String businessname;

    @Size(max = 255)
    private String state;

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

}
