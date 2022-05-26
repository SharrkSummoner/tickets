package org.example;

import java.time.LocalDateTime;

public class Ticket {
    private ticketType ticketType;
    private String description;
    private double price;
    private String ownerName;
    private LocalDateTime buyingDate;
    private LocalDateTime endingDate;

    public Ticket(ticketType ticketType,
                  String description,
                  double price,
                  String ownerName,
                  LocalDateTime buyingDate,
                  LocalDateTime endingDate) {

        this.ticketType = ticketType;
        this.description = description;
        this.price = price;
        this.ownerName = ownerName;
        this.buyingDate = buyingDate;
        this.endingDate = endingDate;
    }

    public ticketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(ticketType ticketType) {
        this.ticketType = ticketType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public LocalDateTime getBuyingDate() {
        return buyingDate;
    }

    public void setBuyingDate(LocalDateTime buyingDate) {
        this.buyingDate = buyingDate;
    }

    public LocalDateTime getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(LocalDateTime endingDate) {
        this.endingDate = endingDate;
    }
}
