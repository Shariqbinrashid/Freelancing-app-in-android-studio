package com.example.helpyar;

public class User {
    String email;
    String fname;
    String lname;
    String dob;
    String age;
    String password;
    String bio;
    String userRank;
    String rating;
    String pno;

    public User() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getUserRank() {
        return userRank;
    }

    public void setUserRank(String userRank) {
        this.userRank = userRank;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPno() {
        return pno;
    }

    public void setPno(String pno) {
        this.pno = pno;
    }

    public User(String email, String fname, String lname, String dob, String age, String password, String bio, String userRank, String rating, String pno) {

        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
        this.age = age;
        this.password = password;
        this.bio = bio;
        this.userRank = userRank;
        this.rating = rating;
        this.pno = pno;
    }
}
