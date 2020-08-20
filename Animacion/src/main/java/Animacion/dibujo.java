/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Animacion;

import java.util.ArrayList;

/**
 *
 * @author BREND
 */

public class dibujo {
    ArrayList <figura> arrayFiguras= new ArrayList<figura>();
    String nombreFig;
  punto eje;
  Boolean ConRelleno=true;
    public dibujo(String nombreFig) {
        this.nombreFig = nombreFig;
    }
    public ArrayList<figura> getArrayFiguras() {
        return arrayFiguras;
    }

    public void setFiguras(ArrayList<figura> figuras) {
        this.arrayFiguras = figuras;
    }

    public String getNombreFig() {
        return nombreFig;
    }

    public void setNombreFig(String nombreFig) {
        this.nombreFig = nombreFig;
    }

    public punto getEje() {
        return eje;
    }

    public void setEje(punto eje) {
        this.eje = eje;
    }

 
    
    
    
    
}
