package com.example.helpyar;

public class jobposteditem {
    String title;
    String amount;
    String posted_date;
    String jobStatus;

    public jobposteditem(String title, String amount, String posted_date, String jobStatus) {
        this.title = title;
        this.amount = amount;
        this.posted_date = posted_date;
        this.jobStatus = jobStatus;
    }


    public String getTitle() {
        return title;
    }

    public String getAmount() {
        return amount;
    }

    public String getPosted_date() {
        return posted_date;
    }

    public String getJobStatus() {
        return jobStatus;
    }
}
