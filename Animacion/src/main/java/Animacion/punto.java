/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Animacion;

/**
 *
 * @author BREND
 */
public class punto {
      double x,y,radio;

    public punto(double x, double y) { 
        this.x = x;
        this.y = y;
    }

    public double getX() {
        double Retx=0;
        Retx=x;
        return Retx;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        double Rety=0;
        Rety=y;
        return Rety;
    }

    public void setY(double y) {
        this.y = y;
    }
}
