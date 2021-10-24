package com.techelevator.model;

import java.math.BigDecimal;

public class Space {

    private long id;
    private String name;
    private boolean isAccessible;
    private int openFrom;
    private int openTo;
    private BigDecimal dailyRate;
    private int maxOccupancy;
    private long venueId;

    public long getVenueId() {
        return venueId;
    }

    public void setVenueId(long venueId) {
        this.venueId = venueId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAccessible() {
        return isAccessible;
    }

    public void setAccessible(boolean accessible) {
        isAccessible = accessible;
    }

    public int getOpenFrom() {
        return openFrom;
    }

    public void setOpenFrom(int openFrom) {
        this.openFrom = openFrom;
    }

    public int getOpenTo() {
        return openTo;
    }

    public void setOpenTo(int openTo) {
        this.openTo = openTo;
    }

    public BigDecimal getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(BigDecimal dailyRate) {
        this.dailyRate = dailyRate;
    }

    public int getMaxOccupancy() {
        return maxOccupancy;
    }

    public void setMaxOccupancy(int maxOccupancy) {
        this.maxOccupancy = maxOccupancy;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
