package com.rbubus.vendorapi.vendor_api.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class BUSESDTO {

    private Long busid;

    @NotNull
    @Size(max = 255)
    private String busname;

    @NotNull
    private Integer seatcapacity;

    @NotNull
    private Integer sleeperid;

    @NotNull
    private Integer sleeperud;

    @NotNull
    @Size(max = 255)
    private String busdiscription;

    private Long bustypeid;

    private Long vendoridfk;

    public Long getBusid() {
        return busid;
    }

    public void setBusid(final Long busid) {
        this.busid = busid;
    }

    public String getBusname() {
        return busname;
    }

    public void setBusname(final String busname) {
        this.busname = busname;
    }

    public Integer getSeatcapacity() {
        return seatcapacity;
    }

    public void setSeatcapacity(final Integer seatcapacity) {
        this.seatcapacity = seatcapacity;
    }

    public Integer getSleeperid() {
        return sleeperid;
    }

    public void setSleeperid(final Integer sleeperid) {
        this.sleeperid = sleeperid;
    }

    public Integer getSleeperud() {
        return sleeperud;
    }

    public void setSleeperud(final Integer sleeperud) {
        this.sleeperud = sleeperud;
    }

    public String getBusdiscription() {
        return busdiscription;
    }

    public void setBusdiscription(final String busdiscription) {
        this.busdiscription = busdiscription;
    }

    public Long getBustypeid() {
        return bustypeid;
    }

    public void setBustypeid(final Long bustypeid) {
        this.bustypeid = bustypeid;
    }

    public Long getVendoridfk() {
        return vendoridfk;
    }

    public void setVendoridfk(final Long vendoridfk) {
        this.vendoridfk = vendoridfk;
    }

}
