package com.rbubus.vendorapi.vendor_api.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.Set;


@Entity
public class BUSTYPE {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long busid;

    @Column(nullable = false)
    private String bustypename;

    @Column(nullable = false, name = "\"description\"")
    private String description;

    @Column(nullable = false)
    private String isactive;

    @OneToMany(mappedBy = "bustypeid")
    private Set<BUSES> bustypeidBUSESs;

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

    public Set<BUSES> getBustypeidBUSESs() {
        return bustypeidBUSESs;
    }

    public void setBustypeidBUSESs(final Set<BUSES> bustypeidBUSESs) {
        this.bustypeidBUSESs = bustypeidBUSESs;
    }

}
