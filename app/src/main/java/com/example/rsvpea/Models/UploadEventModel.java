package com.example.rsvpea.Models;

public class UploadEventModel {
    private String eventTitle, Desc, date, venue, image;

    public UploadEventModel(String eventTitle, String desc, String date, String venue, String image) {
        this.eventTitle = eventTitle;
        this.Desc = desc;
        this.date = date;
        this.venue = venue;
        this.image = image;
    }
    public UploadEventModel(){}

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
