/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Animacion;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author BREND
 */
public class figura {
      String figura;
    ArrayList<punto> puntos =  new ArrayList<punto>();
    int indice;
    double radio,radio2;
  Color relleno,borde;
  punto eje;


    public figura(String figura, int indice, Color relleno, Color borde) {
        this.figura = figura;
        this.indice = indice;
        this.relleno = relleno;
        this.borde = borde;
    }
    public figura(String figura, int indice, Color relleno, Color borde,int radio) {
        this.figura = figura;
        this.indice = indice;
        this.relleno = relleno;
        this.borde = borde;
        this.radio=radio;
    }
    public figura(String figura, int indice, Color relleno, Color borde,int radio,int radio2) {
        this.figura = figura;
        this.indice = indice;
        this.relleno = relleno;
        this.borde = borde;
        this.radio=radio;
        this.radio2=radio2;
    }  
     public figura(String figura, int indice, Color relleno, Color borde,int radio,int radio2,punto eje) {
        this.figura = figura;
        this.indice = indice;
        this.relleno = relleno;
        this.borde = borde;
        this.radio=radio;
        this.radio2=radio2;
        this.eje=eje;
    }  
    

    public String getFigura() {
        return figura;
    }

    public void setFigura(String figura) {
        this.figura = figura;
    }

    public ArrayList<punto> getPuntos() {
        return puntos;
    }

    public void setPuntos(ArrayList<punto> puntos) {
        this.puntos = puntos;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public Color getRelleno() {
        return relleno;
    }

    public void setRelleno(Color relleno) {
        this.relleno = relleno;
    }

    public Color getBorde() {
        return borde;
    }

    public void setBorde(Color borde) {
        this.borde = borde;
    }

   

    
    
    public void addPunto(punto p){
    puntos.add(p);
    }
    
}
