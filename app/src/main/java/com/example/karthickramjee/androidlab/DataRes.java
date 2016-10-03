package com.example.karthickramjee.androidlab;

/**
 * Created by karthickramjee on 03/10/16.
 */
public class DataRes {
    private String name;
    private int number;
    private String mail;
    public DataRes()
    {

    }
    public DataRes(int num,String n,String m)
    {
        super();
        name=n;
        number=num;
        mail=m;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber()
    {
        return number;
    }
    public void setMail(String mail)
    {
        this.mail=mail;
    }
    public String getMail()
    {
        return mail;
    }
}
