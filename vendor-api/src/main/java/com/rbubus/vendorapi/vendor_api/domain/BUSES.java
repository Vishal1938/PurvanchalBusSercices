package com.rbubus.vendorapi.vendor_api.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class BUSES {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long busid;

    @Column(nullable = false)
    private String busname;

    @Column(nullable = false)
    private Integer seatcapacity;

    @Column(nullable = false)
    private Integer sleeperid;

    @Column(nullable = false)
    private Integer sleeperud;

    @Column(nullable = false)
    private String busdiscription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bustypeid_id")
    private BUSTYPE bustypeid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendoridfk_id")
    private BUSVENDOR vendoridfk;

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

    public BUSTYPE getBustypeid() {
        return bustypeid;
    }

    public void setBustypeid(final BUSTYPE bustypeid) {
        this.bustypeid = bustypeid;
    }

    public BUSVENDOR getVendoridfk() {
        return vendoridfk;
    }

    public void setVendoridfk(final BUSVENDOR vendoridfk) {
        this.vendoridfk = vendoridfk;
    }

}
