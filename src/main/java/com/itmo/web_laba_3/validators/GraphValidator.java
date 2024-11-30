package com.itmo.web_laba_3.validators;

import com.itmo.web_laba_3.exceptions.ValidationException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class  GraphValidator {
    private static final Set<Double> xValues= new HashSet<>(Arrays.asList(-3.0, -2.0, -1.0, 0.0, 1.0, 2.0, 3.0, 4.0, 5.0));
    private static final Set<Double> rValues= new HashSet<>(Arrays.asList(1.0, 1.5, 2.0, 2.5, 3.0));
    public static void validateX(double x, boolean areaClick) throws ValidationException {
        try{
            if (!areaClick && !xValues.contains(x)){
                throw new ValidationException("X must be in set: {-2, -1.5, -1, -0.5, 0, 0.5, 1, 1.5, 2}");
            }
        } catch (NumberFormatException e){
            throw new ValidationException("X must be a double!");
        }
    }

    public static void validateY(double y, boolean areaClick) throws ValidationException{
        try{
            if (!areaClick && (y <= -5 || y >=3)){
                throw new ValidationException("Y must be in interval (-5, 3)");
            }
        } catch (NumberFormatException e){
            throw new ValidationException("Y must be a double!");
        }

    }

    public static void validateR(double r) throws ValidationException{
        try{
            if (!rValues.contains(r)){
                throw new ValidationException("R must be in set: {1, 1.5, 2, 2.5, 3}");
            }
        } catch (NumberFormatException e){
            throw new ValidationException("R must be a double!");
        }
    }
}
