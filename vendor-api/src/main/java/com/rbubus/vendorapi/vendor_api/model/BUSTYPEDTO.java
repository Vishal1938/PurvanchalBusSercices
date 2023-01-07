package com.rbubus.vendorapi.vendor_api.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class BUSTYPEDTO {

    private Long busid;

    @NotNull
    @Size(max = 255)
    private String bustypename;

    @NotNull
    @Size(max = 255)
    private String description;

    @NotNull
    @Size(max = 255)
    private String isactive;

    public Long getBusid() {
        return busid;
    }

    public void setBusid(final Long busid) {
        this.busid = busid;
    }

    public String getBustypename() {
        return bustypename;
    }

    public void setBustypename(final String bustypename) {
        this.bustypename = bustypename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getIsactive() {
        return isactive;
    }

    public void setIsactive(final String isactive) {
        this.isactive = isactive;
    }

}
