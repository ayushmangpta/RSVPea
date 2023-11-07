package com.example.rsvpea.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class UploadEventModel implements Parcelable {
    private String eventTitle, Desc, date, venue, image;

    public UploadEventModel(String eventTitle, String desc, String date, String venue, String image) {
        this.eventTitle = eventTitle;
        this.Desc = desc;
        this.date = date;
        this.venue = venue;
        this.image = image;
    }
    public UploadEventModel(){}

    protected UploadEventModel(Parcel in) {
        eventTitle = in.readString();
        Desc = in.readString();
        date = in.readString();
        venue = in.readString();
        image = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(eventTitle);
        dest.writeString(Desc);
        dest.writeString(date);
        dest.writeString(venue);
        dest.writeString(image);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UploadEventModel> CREATOR = new Creator<UploadEventModel>() {
        @Override
        public UploadEventModel createFromParcel(Parcel in) {
            return new UploadEventModel(in);
        }

        @Override
        public UploadEventModel[] newArray(int size) {
            return new UploadEventModel[size];
        }
    };

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
