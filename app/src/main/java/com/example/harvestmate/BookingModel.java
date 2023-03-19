package com.example.harvestmate;


public class BookingModel {

//    private int booking_icon;
    private String Booking_date;
    private String Booking_Center;
    private String Approval_status;

    // Constructor
    public BookingModel(String Booking_date, String Booking_Center, String Approval_status) {
        this.Booking_date = Booking_date;
        this.Booking_Center = Booking_Center;
        this.Approval_status = Approval_status;
//        System.out.println(this.Booking_date+"===="+this.Booking_Center+"====="+this.Approval_status);
//        this.booking_icon = booking_icon;
    }

    // Getter and Setter
    public String getBooking_date() {
        return Booking_date;
    }

    public void setBooking_date(String Booking_date) {
        this.Booking_date = Booking_date;
    }

    public String getBooking_Center() {
        return Booking_Center;
    }

    public void setBooking_Center(String Booking_Center) {
        this.Booking_Center = Booking_Center;
    }

    public String getApproval_status() {
        return Approval_status;
    }

    public void setApproval_status(String Approval_status) {
        this.Approval_status = Approval_status;
    }


}

