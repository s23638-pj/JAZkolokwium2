package com.example.jazs23638nbp.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Klasa odpowiedzialna za entity nbp
 */
@javax.persistence.Entity
public class Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String deskaRatunku;

    public Double gold;

    public double rate;

    public LocalDate requestDate;

    public LocalTime requestTime;

    public String startDate;

    public String endDate;

    public String getDeskaRatunku() {
        return deskaRatunku;
    }

    public void setDeskaRatunku(String gdybyToDzialalo) {
        this.deskaRatunku = gdybyToDzialalo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getGold() {
        return gold;
    }

    public void setGold(Double gold) {
        this.gold = gold;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public LocalTime getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(LocalTime requestTime) {
        this.requestTime = requestTime;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

//    @Override
//    public String toString() {
//        return rate;
//    }

}
