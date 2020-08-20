/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Animacion;

import java.awt.Color;

/**
 *
 * @author BREND
 */
public class lineas {
      punto A;
    punto B;
Color c;

    public lineas(punto A, punto B, Color c) {
        this.A = A;
        this.B = B;
        this.c = c;
    }
  

    public punto getA() {
        return A;
    }

    public void setA(punto A) {
        this.A = A;
    }

    public punto getB() {
        return B;
    }

    public void setB(punto B) {
        this.B = B;
    }
}
