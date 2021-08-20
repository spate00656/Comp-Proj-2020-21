package com.woa.dataClasses;

public class HelpfulLink {

    private String title, text, videoLink, firstLink, secondLink;

    public HelpfulLink(String title, String text, String videoLink, String firstLink, String secondLink) {
        this.title = title;
        this.text = text;
        this.videoLink = videoLink;
        this.firstLink = firstLink;
        this.secondLink = secondLink;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public String getFirstLink() {
        return firstLink;
    }

    public String getSecondLink() {
        return secondLink;
    }
}
