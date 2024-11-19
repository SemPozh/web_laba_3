package com.itmo.web_laba_3.model;
import com.itmo.web_laba_3.exceptions.ValidationException;
import com.itmo.web_laba_3.validators.GraphValidator;
import jakarta.persistence.*;

@Entity
@Table(name = "shots")
public class GraphShot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "x")
    private double x;
    @Column(name = "y")
    private double y;
    @Column(name = "r")
    private double r;
    @Column(name = "result")
    private boolean result;
    @Column(name = "by_click")
    private boolean byClick;

    public GraphShot(){
        this.r = 2;
        this.byClick = false;
    }
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y){
        this.y = y;
    }

    public void setByClick(boolean byClick) {
        this.byClick = byClick;
    }

    public boolean getByClick(){
        return byClick;
    }

    public double getR() {
        return r;
    }

    public boolean getResult(){
        return this.result;
    }

    public void setR(double r) {
        this.r = r;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}