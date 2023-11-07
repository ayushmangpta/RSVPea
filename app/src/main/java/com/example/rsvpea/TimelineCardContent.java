package com.example.rsvpea;

import com.example.rsvpea.Models.UploadEventModel;

public class TimelineCardContent {
    String img;
    String title;
    UploadEventModel eventModel;

    public TimelineCardContent(String img, String title, UploadEventModel eventModel) {
        this.img = img;
        this.title = title;
        this.eventModel=eventModel;
    }
    public TimelineCardContent(){}
}
