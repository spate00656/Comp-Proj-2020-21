package com.woa.dataClasses;

import android.os.Parcel;
import android.os.Parcelable;

public class JobData implements Parcelable {

    String companyName
    , date
    , jobDescription
    , jobKey
    , jobStatus
    , jobTitle
    , location;

    public JobData(){}

    public JobData(String companyName, String date, String jobDescription, String jobKey, String jobStatus, String jobTitle, String location) {
        this.companyName = companyName;
        this.date = date;
        this.jobDescription = jobDescription;
        this.jobKey = jobKey;
        this.jobStatus = jobStatus;
        this.jobTitle = jobTitle;
        this.location = location;
    }

    protected JobData(Parcel in) {
        companyName = in.readString();
        date = in.readString();
        jobDescription = in.readString();
        jobKey = in.readString();
        jobStatus = in.readString();
        jobTitle = in.readString();
        location = in.readString();
    }

    public static final Creator<JobData> CREATOR = new Creator<JobData>() {
        @Override
        public JobData createFromParcel(Parcel in) {
            return new JobData(in);
        }

        @Override
        public JobData[] newArray(int size) {
            return new JobData[size];
        }
    };

    public String getCompanyName() {
        return companyName;
    }

    public String getDate() {
        return date;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public String getJobKey() {
        return jobKey;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(companyName);
        parcel.writeString(date);
        parcel.writeString(jobDescription);
        parcel.writeString(jobKey);
        parcel.writeString(jobStatus);
        parcel.writeString(jobTitle);
        parcel.writeString(location);
    }
}
