package com.example.api_calling.Models;

public class Company {

    String comname,catchphrase,bs;

    public Company(String comname, String catchphrase, String bs) {
        this.comname = comname;
        this.catchphrase = catchphrase;
        this.bs = bs;
    }

    public String getComname() {
        return comname;
    }

    public void setComname(String comname) {
        this.comname = comname;
    }

    public String getCatchphrase() {
        return catchphrase;
    }

    public void setCatchphrase(String catchphrase) {
        this.catchphrase = catchphrase;
    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }
}
