package com.itmo.web_laba_3.beans;

import com.itmo.web_laba_3.exceptions.ValidationException;
import com.itmo.web_laba_3.model.GraphShot;
import com.itmo.web_laba_3.services.AreaCheckService;
import com.itmo.web_laba_3.services.GraphShotDAO;
import com.itmo.web_laba_3.validators.GraphValidator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;


@Named
@ApplicationScoped
public class ControllerBean implements Serializable {
    private GraphShot graphShot;
    private final GraphShotDAO graphShotDAO = new GraphShotDAO();
    public ControllerBean(){
        this.graphShot = new GraphShot();
    }
    public void setGraphShot(GraphShot graphShot) {
        this.graphShot = graphShot;
    }

    public GraphShot getGraphShot() {
        return graphShot;
    }

    public void setX(double x){
        getGraphShot().setX(x);
    }
    public String save() {
        try {
            GraphValidator.validateX(graphShot.getX(), graphShot.getByClick());
            GraphValidator.validateY(graphShot.getY(), graphShot.getByClick());
            GraphValidator.validateR(graphShot.getR());
        } catch (ValidationException e) {
            return "/templates/400.xhtml?faces-redirect=true";
        }
        graphShot.setResult(AreaCheckService.checkArea(getGraphShot().getX(), getGraphShot().getY(), getGraphShot().getR()));
        graphShotDAO.save(graphShot);
        graphShot = new GraphShot();
        return "/templates/main.xhtml?faces-redirect=true";
    }

    public List<GraphShot> getAllShots(){
        return graphShotDAO.getShots();
    }

    public boolean checkButtonX(double x){
        return x==getGraphShot().getX();
    }

    public String clearAllShots(){
        graphShotDAO.clearAllShots();
        return "/templates/main.xhtml?faces-redirect=true";
    }
}