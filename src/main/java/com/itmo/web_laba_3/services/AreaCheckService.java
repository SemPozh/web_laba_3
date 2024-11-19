package com.itmo.web_laba_3.services;

public class AreaCheckService {
    public static boolean checkArea(double x, double y, double r){
        return ((y >=-x-r) && (x<=0) && (y<=0)) || ((x*x + y*y <= r*r/4) && (x<=0) && (y>=0)) || ((x<=r) && (x>=0) && (y<=0) && (y>=-r/2));
    }
}
