/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Animacion;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author BREND
 */
public class Animacion extends JFrame implements Runnable {

    /**
     * @param args the command line arguments
     */
    Graphics gra;
    BufferStrategy bs;
    private Image offScreenImage;
    private Graphics offScreenGraphics;
    public BufferStrategy strategy;
    private BufferedImage buffer;
    public int xorigen, yorigen, rd1, d2;
    public double pendienteLo, pendienteSeg, pendienteTres;
    private Graphics graPixel;
    punto p1, p4;
    punto p2, p5;
    punto p3, p6;
    public double angulo = 90;
    double[][] rotacion = new double[3][3];
    double[][] escalacion = new double[3][3];
    double p[] = new double[3];
    double Sy = 1.5, Sx = 1.5, xprima, yprima, xo, yo;
    Thread hilo;
    ArrayList<lineas> arrLineas = new ArrayList<lineas>();
    ArrayList<punto> arrPuntos = new ArrayList<punto>();
    ArrayList<punto> arrPuntosCoincidentes = new ArrayList<punto>();
    ArrayList<punto> nave1 = new ArrayList<punto>();
    ArrayList<dibujo> arrayDibujos = new ArrayList<dibujo>();
    dibujo nave2 = new dibujo("nave2");
    dibujo nave3 = new dibujo("nave3");
    dibujo nave4 = new dibujo("nave4");
    dibujo Tie1 = new dibujo("Tie1");
    dibujo Tie2 = new dibujo("Tie2");
    dibujo Tie3 = new dibujo("Tie3");

    dibujo halcon = new dibujo("Halcon");
    dibujo estrella = new dibujo("estrella");
    dibujo disparo1 = new dibujo("disparo1");
    dibujo disparo2 = new dibujo("disparo2");
    dibujo disparo3 = new dibujo("disparo3");
    dibujo disparo4 = new dibujo("disparo4");
    dibujo planeta = new dibujo("planeta");
    dibujo arwing = new dibujo("arwing");
    dibujo Xwing = new dibujo("Xwing");

    Animacion() {

        this.setSize(1000, 1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.WHITE);
        this.setForeground(Color.white);
        // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);
        //   setExtendedState(JFrame.MAXIMIZED_BOTH); 
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();

        offScreenImage = createImage(this.getSize().width, this.getSize().height);
        offScreenGraphics = offScreenImage.getGraphics();

        createBufferStrategy(2);
        strategy = getBufferStrategy();

        escalacion[0][0] = Sx;
        escalacion[0][1] = 0;
        escalacion[0][2] = 0;

        escalacion[1][0] = 0;
        escalacion[1][1] = Sy;
        escalacion[1][2] = 0;

        escalacion[2][0] = 0;
        escalacion[2][1] = 0;
        escalacion[2][2] = 1;

        planeta.getArrayFiguras().add(new figura("Circulo", 0, Color.MAGENTA, Color.MAGENTA, 30));
        planeta.getArrayFiguras().get(0).addPunto(new punto(40, -280));
        planeta.setEje(new punto(40, -280));
        arrayDibujos.add(planeta);

        estrella.getArrayFiguras().add(new figura("Circulo", 0, Color.DARK_GRAY, Color.DARK_GRAY, 150));
        estrella.getArrayFiguras().get(0).addPunto(new punto(-460, 200));
        estrella.getArrayFiguras().add(new figura("Circulo", 0, Color.BLACK, Color.LIGHT_GRAY, 30));
        estrella.getArrayFiguras().get(1).addPunto(new punto(-500, 320));
        estrella.setEje(new punto(-460, 200));
        arrayDibujos.add(estrella);
        punto preferenciaNave = new punto(-460, -240);
        punto eje = new punto(0, 0);

        nave2.getArrayFiguras().add(new figura("Triangulo", 0, Color.GRAY, Color.blue));
        nave2.getArrayFiguras().get(0).addPunto(new punto(preferenciaNave.x, preferenciaNave.y));
        nave2.getArrayFiguras().get(0).addPunto(new punto((preferenciaNave.x + 100), preferenciaNave.y + 40));
        nave2.getArrayFiguras().get(0).addPunto(new punto(preferenciaNave.x, preferenciaNave.y + 80));
        nave2.getArrayFiguras().add(new figura("Rectangulo", 1, Color.DARK_GRAY, Color.black));
        nave2.getArrayFiguras().get(1).addPunto(new punto(preferenciaNave.x + 10, preferenciaNave.y + 25));
        nave2.getArrayFiguras().get(1).addPunto(new punto(preferenciaNave.x + 40, preferenciaNave.y + 50));
        nave2.getArrayFiguras().add(new figura("Rectangulo", 2, Color.GRAY, Color.black));
        nave2.getArrayFiguras().get(2).addPunto(new punto(preferenciaNave.x + 20, preferenciaNave.y + 15));
        nave2.getArrayFiguras().get(2).addPunto(new punto(preferenciaNave.x + 30, preferenciaNave.y + 40));
        nave2.getArrayFiguras().add(new figura("Rectangulo", 3, Color.LIGHT_GRAY, Color.black));
        nave2.getArrayFiguras().get(3).addPunto(new punto(preferenciaNave.x + 15, preferenciaNave.y));
        nave2.getArrayFiguras().get(3).addPunto(new punto(preferenciaNave.x + 35, preferenciaNave.y + 15));
        nave2.setEje(new punto(-460, -240));
        arrayDibujos.add(nave2);

        preferenciaNave = new punto(-350, -140);

        nave3.getArrayFiguras().add(new figura("Triangulo", 0, Color.GRAY, Color.blue));
        nave3.getArrayFiguras().get(0).addPunto(new punto(preferenciaNave.x, preferenciaNave.y));
        nave3.getArrayFiguras().get(0).addPunto(new punto((preferenciaNave.x + 100), preferenciaNave.y + 40));
        nave3.getArrayFiguras().get(0).addPunto(new punto(preferenciaNave.x, preferenciaNave.y + 80));
        nave3.getArrayFiguras().add(new figura("Rectangulo", 1, Color.DARK_GRAY, Color.black));
        nave3.getArrayFiguras().get(1).addPunto(new punto(preferenciaNave.x + 10, preferenciaNave.y + 25));
        nave3.getArrayFiguras().get(1).addPunto(new punto(preferenciaNave.x + 40, preferenciaNave.y + 50));
        nave3.getArrayFiguras().add(new figura("Rectangulo", 2, Color.GRAY, Color.black));
        nave3.getArrayFiguras().get(2).addPunto(new punto(preferenciaNave.x + 20, preferenciaNave.y + 15));
        nave3.getArrayFiguras().get(2).addPunto(new punto(preferenciaNave.x + 30, preferenciaNave.y + 40));
        nave3.getArrayFiguras().add(new figura("Rectangulo", 3, Color.LIGHT_GRAY, Color.black));
        nave3.getArrayFiguras().get(3).addPunto(new punto(preferenciaNave.x + 15, preferenciaNave.y));
        nave3.getArrayFiguras().get(3).addPunto(new punto(preferenciaNave.x + 35, preferenciaNave.y + 15));
        arrayDibujos.add(nave3);

        preferenciaNave = new punto(-460, -40);

        nave4.getArrayFiguras().add(new figura("Triangulo", 0, Color.GRAY, Color.blue));
        nave4.getArrayFiguras().get(0).addPunto(new punto(preferenciaNave.x, preferenciaNave.y));
        nave4.getArrayFiguras().get(0).addPunto(new punto((preferenciaNave.x + 100), preferenciaNave.y + 40));
        nave4.getArrayFiguras().get(0).addPunto(new punto(preferenciaNave.x, preferenciaNave.y + 80));
        nave4.getArrayFiguras().add(new figura("Rectangulo", 1, Color.DARK_GRAY, Color.black));
        nave4.getArrayFiguras().get(1).addPunto(new punto(preferenciaNave.x + 10, preferenciaNave.y + 25));
        nave4.getArrayFiguras().get(1).addPunto(new punto(preferenciaNave.x + 40, preferenciaNave.y + 50));
        nave4.getArrayFiguras().add(new figura("Rectangulo", 2, Color.GRAY, Color.black));
        nave4.getArrayFiguras().get(2).addPunto(new punto(preferenciaNave.x + 20, preferenciaNave.y + 15));
        nave4.getArrayFiguras().get(2).addPunto(new punto(preferenciaNave.x + 30, preferenciaNave.y + 40));
        nave4.getArrayFiguras().add(new figura("Rectangulo", 3, Color.LIGHT_GRAY, Color.black));
        nave4.getArrayFiguras().get(3).addPunto(new punto(preferenciaNave.x + 15, preferenciaNave.y));
        nave4.getArrayFiguras().get(3).addPunto(new punto(preferenciaNave.x + 35, preferenciaNave.y + 15));
        arrayDibujos.add(nave4);

        preferenciaNave = new punto(-20, 100);
        Tie1.getArrayFiguras().add(new figura("Triangulo", 0, Color.gray, Color.ORANGE));
        Tie1.getArrayFiguras().get(0).addPunto(new punto(preferenciaNave.x, preferenciaNave.y - 20));
        Tie1.getArrayFiguras().get(0).addPunto(new punto((preferenciaNave.x + 100), preferenciaNave.y - 10));
        Tie1.getArrayFiguras().get(0).addPunto(new punto(preferenciaNave.x, preferenciaNave.y));
        Tie1.getArrayFiguras().add(new figura("Circulo", 0, Color.orange, Color.darkGray, 20));
        Tie1.getArrayFiguras().get(1).addPunto(new punto(preferenciaNave.x, preferenciaNave.y));
        Tie1.getArrayFiguras().add(new figura("Triangulo", 0, Color.gray, Color.ORANGE));
        Tie1.getArrayFiguras().get(2).addPunto(new punto(preferenciaNave.x, preferenciaNave.y));
        Tie1.getArrayFiguras().get(2).addPunto(new punto((preferenciaNave.x + 100), preferenciaNave.y + 10));
        Tie1.getArrayFiguras().get(2).addPunto(new punto(preferenciaNave.x, preferenciaNave.y + 20));
        Tie1.setEje(new punto(-20, 80));
        arrayDibujos.add(Tie1);

        preferenciaNave = new punto(-200, 100);
        halcon.getArrayFiguras().add(new figura("Circulo", 0, Color.WHITE, Color.WHITE, 50));
        halcon.getArrayFiguras().get(0).addPunto(new punto(preferenciaNave.x, preferenciaNave.y));
        halcon.getArrayFiguras().add(new figura("Triangulo", 0, Color.WHITE, Color.LIGHT_GRAY));
        halcon.getArrayFiguras().get(1).addPunto(new punto(preferenciaNave.x, preferenciaNave.y + 10));
        halcon.getArrayFiguras().get(1).addPunto(new punto((preferenciaNave.x + 75), preferenciaNave.y + 30));
        halcon.getArrayFiguras().get(1).addPunto(new punto(preferenciaNave.x, preferenciaNave.y + 50));
        halcon.getArrayFiguras().add(new figura("Triangulo", 0, Color.WHITE, Color.white));

        halcon.getArrayFiguras().get(2).addPunto(new punto(preferenciaNave.x, preferenciaNave.y - 10));
        halcon.getArrayFiguras().get(2).addPunto(new punto((preferenciaNave.x + 75), preferenciaNave.y - 30));
        halcon.getArrayFiguras().get(2).addPunto(new punto(preferenciaNave.x, preferenciaNave.y - 50));

        halcon.getArrayFiguras().add(new figura("Elipse", 0, Color.WHITE, Color.GRAY, 15, 5));
        halcon.getArrayFiguras().get(3).addPunto(new punto(preferenciaNave.x + 25, preferenciaNave.y + 45));

        halcon.getArrayFiguras().add(new figura("Circulo", 0, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 10));
        halcon.getArrayFiguras().get(4).addPunto(new punto(preferenciaNave.x, preferenciaNave.y));

        halcon.getArrayFiguras().add(new figura("Rectangulo", 1, Color.WHITE, Color.white));
        halcon.getArrayFiguras().get(5).addPunto(new punto(preferenciaNave.x, preferenciaNave.y - 20));
        halcon.getArrayFiguras().get(5).addPunto(new punto(preferenciaNave.x + 75, preferenciaNave.y - 30));

        halcon.getArrayFiguras().add(new figura("Rectangulo", 1, Color.WHITE, Color.white));
        halcon.getArrayFiguras().get(6).addPunto(new punto(preferenciaNave.x, preferenciaNave.y + 20));
        halcon.getArrayFiguras().get(6).addPunto(new punto(preferenciaNave.x + 75, preferenciaNave.y + 30));

        halcon.getArrayFiguras().add(new figura("Circulo", 0, Color.GRAY, Color.GRAY, 4));
        halcon.getArrayFiguras().get(7).addPunto(new punto(preferenciaNave.x + 40, preferenciaNave.y + 45));

        halcon.getArrayFiguras().add(new figura("Circulo", 0, Color.BLUE, Color.BLUE, 4));
        halcon.getArrayFiguras().get(8).addPunto(new punto(preferenciaNave.x + 45, preferenciaNave.y));

        halcon.getArrayFiguras().add(new figura("Circulo", 0, Color.GRAY, Color.GRAY, 4));
        halcon.getArrayFiguras().get(9).addPunto(new punto(preferenciaNave.x + 30, preferenciaNave.y + 10));

        halcon.getArrayFiguras().add(new figura("Circulo", 0, Color.GRAY, Color.GRAY, 4));
        halcon.getArrayFiguras().get(10).addPunto(new punto(preferenciaNave.x + 30, preferenciaNave.y - 10));

        halcon.getArrayFiguras().add(new figura("Circulo", 0, Color.BLUE, Color.BLUE, 4));
        halcon.getArrayFiguras().get(11).addPunto(new punto(preferenciaNave.x + 75, preferenciaNave.y - 22));

        halcon.getArrayFiguras().add(new figura("Circulo", 0, Color.BLUE, Color.BLUE, 4));
        halcon.getArrayFiguras().get(12).addPunto(new punto(preferenciaNave.x + 75, preferenciaNave.y + 22));

        arrayDibujos.add(halcon);

        disparo3.getArrayFiguras().add(new figura("Circulo", 0, Color.GREEN, Color.GREEN, 4));
        disparo3.getArrayFiguras().get(0).addPunto(new punto(-840, 400));
        arrayDibujos.add(disparo3);

        disparo1.getArrayFiguras().add(new figura("Circulo", 0, Color.RED, Color.ORANGE, 4));
        disparo1.getArrayFiguras().get(0).addPunto(new punto(halcon.getArrayFiguras().get(11).puntos.get(0).x, halcon.getArrayFiguras().get(11).puntos.get(0).y));
        arrayDibujos.add(disparo1);

        disparo2.getArrayFiguras().add(new figura("Circulo", 0, Color.RED, Color.ORANGE, 4));
        disparo2.getArrayFiguras().get(0).addPunto(new punto(halcon.getArrayFiguras().get(12).puntos.get(0).x, halcon.getArrayFiguras().get(12).puntos.get(0).y));
        arrayDibujos.add(disparo2);

        preferenciaNave = new punto(-820, 100);
        Tie2.getArrayFiguras().add(new figura("Triangulo", 0, Color.gray, Color.ORANGE));
        Tie2.getArrayFiguras().get(0).addPunto(new punto(preferenciaNave.x, preferenciaNave.y - 20));
        Tie2.getArrayFiguras().get(0).addPunto(new punto((preferenciaNave.x + 100), preferenciaNave.y - 10));
        Tie2.getArrayFiguras().get(0).addPunto(new punto(preferenciaNave.x, preferenciaNave.y));
        Tie2.getArrayFiguras().add(new figura("Circulo", 0, Color.orange, Color.darkGray, 20));
        Tie2.getArrayFiguras().get(1).addPunto(new punto(preferenciaNave.x, preferenciaNave.y));
        Tie2.getArrayFiguras().add(new figura("Triangulo", 0, Color.gray, Color.ORANGE));
        Tie2.getArrayFiguras().get(2).addPunto(new punto(preferenciaNave.x, preferenciaNave.y));
        Tie2.getArrayFiguras().get(2).addPunto(new punto((preferenciaNave.x + 100), preferenciaNave.y + 10));
        Tie2.getArrayFiguras().get(2).addPunto(new punto(preferenciaNave.x, preferenciaNave.y + 20));
        Tie2.setEje(new punto(-20, 80));
        arrayDibujos.add(Tie2);

        preferenciaNave = new punto(-820, 100);
        Tie3.getArrayFiguras().add(new figura("Triangulo", 0, Color.gray, Color.ORANGE));
        Tie3.getArrayFiguras().get(0).addPunto(new punto(preferenciaNave.x, preferenciaNave.y - 20));
        Tie3.getArrayFiguras().get(0).addPunto(new punto((preferenciaNave.x + 100), preferenciaNave.y - 10));
        Tie3.getArrayFiguras().get(0).addPunto(new punto(preferenciaNave.x, preferenciaNave.y));
        Tie3.getArrayFiguras().add(new figura("Circulo", 0, Color.orange, Color.darkGray, 20));
        Tie3.getArrayFiguras().get(1).addPunto(new punto(preferenciaNave.x, preferenciaNave.y));
        Tie3.getArrayFiguras().add(new figura("Triangulo", 0, Color.gray, Color.ORANGE));
        Tie3.getArrayFiguras().get(2).addPunto(new punto(preferenciaNave.x, preferenciaNave.y));
        Tie3.getArrayFiguras().get(2).addPunto(new punto((preferenciaNave.x + 100), preferenciaNave.y + 10));
        Tie3.getArrayFiguras().get(2).addPunto(new punto(preferenciaNave.x, preferenciaNave.y + 20));
        arrayDibujos.add(Tie3);

        preferenciaNave = new punto(-820, -240);
        eje = new punto(0, -15);
        arwing.eje = new punto(eje.x, eje.y);

        arwing.getArrayFiguras().add(new figura("Circulo", 0, Color.WHITE, Color.WHITE, 1));
        arwing.getArrayFiguras().get(0).addPunto(new punto(preferenciaNave.x, preferenciaNave.y));

        arwing.getArrayFiguras().add(new figura("Triangulo", 0, Color.WHITE, Color.WHITE));
        arwing.getArrayFiguras().get(1).addPunto(new punto(preferenciaNave.x - 0, preferenciaNave.y - 15));
        arwing.getArrayFiguras().get(1).addPunto(new punto((preferenciaNave.x + 50), preferenciaNave.y + 0));
        arwing.getArrayFiguras().get(1).addPunto(new punto(preferenciaNave.x - 0, preferenciaNave.y + 15));

        arwing.getArrayFiguras().add(new figura("Triangulo", 0, Color.WHITE, Color.WHITE));
        arwing.getArrayFiguras().get(2).addPunto(new punto(preferenciaNave.x + 0, preferenciaNave.y - 15));
        arwing.getArrayFiguras().get(2).addPunto(new punto((preferenciaNave.x - 25), preferenciaNave.y + 0));
        arwing.getArrayFiguras().get(2).addPunto(new punto(preferenciaNave.x + 0, preferenciaNave.y + 15));

        arwing.getArrayFiguras().add(new figura("Triangulo", 0, Color.WHITE, Color.WHITE));
        arwing.getArrayFiguras().get(3).addPunto(new punto(preferenciaNave.x - 10, preferenciaNave.y + 0));
        arwing.getArrayFiguras().get(3).addPunto(new punto((preferenciaNave.x + 0), preferenciaNave.y + 50));
        arwing.getArrayFiguras().get(3).addPunto(new punto(preferenciaNave.x + 10, preferenciaNave.y + 0));

        arwing.getArrayFiguras().add(new figura("Triangulo", 0, Color.WHITE, Color.WHITE));
        arwing.getArrayFiguras().get(4).addPunto(new punto(preferenciaNave.x - 10, preferenciaNave.y - 0));
        arwing.getArrayFiguras().get(4).addPunto(new punto((preferenciaNave.x - 0), preferenciaNave.y - 50));
        arwing.getArrayFiguras().get(4).addPunto(new punto(preferenciaNave.x + 10, preferenciaNave.y - 0));

        arwing.getArrayFiguras().add(new figura("Triangulo", 0, Color.BLUE, Color.BLUE));
        arwing.getArrayFiguras().get(5).addPunto(new punto(preferenciaNave.x + 0, preferenciaNave.y - 20));
        arwing.getArrayFiguras().get(5).addPunto(new punto((preferenciaNave.x + 30), preferenciaNave.y - 25));
        arwing.getArrayFiguras().get(5).addPunto(new punto(preferenciaNave.x - 0, preferenciaNave.y - 30));

        arwing.getArrayFiguras().add(new figura("Triangulo", 0, Color.BLUE, Color.BLUE));
        arwing.getArrayFiguras().get(6).addPunto(new punto(preferenciaNave.x + 0, preferenciaNave.y - 20));
        arwing.getArrayFiguras().get(6).addPunto(new punto((preferenciaNave.x - 30), preferenciaNave.y - 25));
        arwing.getArrayFiguras().get(6).addPunto(new punto(preferenciaNave.x - 0, preferenciaNave.y - 30));

        arwing.getArrayFiguras().add(new figura("Triangulo", 0, Color.BLUE, Color.BLUE));
        arwing.getArrayFiguras().get(7).addPunto(new punto(preferenciaNave.x + 0, preferenciaNave.y + 20));
        arwing.getArrayFiguras().get(7).addPunto(new punto((preferenciaNave.x + 30), preferenciaNave.y + 25));
        arwing.getArrayFiguras().get(7).addPunto(new punto(preferenciaNave.x - 0, preferenciaNave.y + 30));

        arwing.getArrayFiguras().add(new figura("Triangulo", 0, Color.BLUE, Color.BLUE));
        arwing.getArrayFiguras().get(8).addPunto(new punto(preferenciaNave.x + 0, preferenciaNave.y + 20));
        arwing.getArrayFiguras().get(8).addPunto(new punto((preferenciaNave.x - 30), preferenciaNave.y + 25));
        arwing.getArrayFiguras().get(8).addPunto(new punto(preferenciaNave.x - 0, preferenciaNave.y + 30));

        arwing.getArrayFiguras().add(new figura("Triangulo", 0, Color.DARK_GRAY, Color.WHITE));
        arwing.getArrayFiguras().get(9).addPunto(new punto(preferenciaNave.x + 40, preferenciaNave.y + 4));
        arwing.getArrayFiguras().get(9).addPunto(new punto((preferenciaNave.x + 50), preferenciaNave.y + 0));
        arwing.getArrayFiguras().get(9).addPunto(new punto((preferenciaNave.x + 40), preferenciaNave.y - 4));

        arwing.getArrayFiguras().add(new figura("Triangulo", 0, Color.BLACK, Color.WHITE));
        arwing.getArrayFiguras().get(10).addPunto(new punto(preferenciaNave.x + 10, preferenciaNave.y + 7));
        arwing.getArrayFiguras().get(10).addPunto(new punto((preferenciaNave.x + 25), preferenciaNave.y + 0));
        arwing.getArrayFiguras().get(10).addPunto(new punto((preferenciaNave.x + 10), preferenciaNave.y - 7));

        arrayDibujos.add(arwing);
        disparo4.getArrayFiguras().add(new figura("Circulo", 0, Color.RED, Color.RED, 4));
        disparo4.getArrayFiguras().get(0).addPunto(new punto(560, 30));
        arrayDibujos.add(disparo4);

        preferenciaNave = new punto(560, 30);
        eje = new punto(0, -15);
        Xwing.eje = new punto(eje.x, eje.y);
        Xwing.getArrayFiguras().add(new figura("Circulo", 0, Color.WHITE, Color.WHITE, 1));
        Xwing.getArrayFiguras().get(0).addPunto(new punto(preferenciaNave.x, preferenciaNave.y));
        Xwing.getArrayFiguras().add(new figura("Triangulo", 0, Color.WHITE, Color.WHITE));
        Xwing.getArrayFiguras().get(1).addPunto(new punto(preferenciaNave.x - 0, preferenciaNave.y - 5));
        Xwing.getArrayFiguras().get(1).addPunto(new punto((preferenciaNave.x - 50), preferenciaNave.y + 0));
        Xwing.getArrayFiguras().get(1).addPunto(new punto(preferenciaNave.x - 0, preferenciaNave.y + 5));
        Xwing.getArrayFiguras().add(new figura("Elipse", 0, Color.WHITE, Color.WHITE, 30, 5));
        Xwing.getArrayFiguras().get(2).addPunto(new punto(preferenciaNave.x - 0, preferenciaNave.y));

        Xwing.getArrayFiguras().add(new figura("Triangulo", 0, Color.WHITE, Color.WHITE));
        Xwing.getArrayFiguras().get(3).addPunto(new punto(preferenciaNave.x - 10, preferenciaNave.y + 0));
        Xwing.getArrayFiguras().get(3).addPunto(new punto((preferenciaNave.x + 5), preferenciaNave.y + 20));
        Xwing.getArrayFiguras().get(3).addPunto(new punto(preferenciaNave.x + 0, preferenciaNave.y + 0));

        Xwing.getArrayFiguras().add(new figura("Triangulo", 0, Color.WHITE, Color.RED));
        Xwing.getArrayFiguras().get(4).addPunto(new punto(preferenciaNave.x - 5, preferenciaNave.y + 0));
        Xwing.getArrayFiguras().get(4).addPunto(new punto((preferenciaNave.x + 10), preferenciaNave.y + 20));
        Xwing.getArrayFiguras().get(4).addPunto(new punto(preferenciaNave.x + 5, preferenciaNave.y + 0));

        Xwing.getArrayFiguras().add(new figura("Triangulo", 0, Color.WHITE, Color.WHITE));
        Xwing.getArrayFiguras().get(5).addPunto(new punto(preferenciaNave.x - 10, preferenciaNave.y + 0));
        Xwing.getArrayFiguras().get(5).addPunto(new punto((preferenciaNave.x + 5), preferenciaNave.y - 20));
        Xwing.getArrayFiguras().get(5).addPunto(new punto(preferenciaNave.x + 0, preferenciaNave.y + 0));

        Xwing.getArrayFiguras().add(new figura("Triangulo", 0, Color.WHITE, Color.RED));
        Xwing.getArrayFiguras().get(6).addPunto(new punto(preferenciaNave.x - 5, preferenciaNave.y + 0));
        Xwing.getArrayFiguras().get(6).addPunto(new punto((preferenciaNave.x + 10), preferenciaNave.y - 20));
        Xwing.getArrayFiguras().get(6).addPunto(new punto(preferenciaNave.x + 5, preferenciaNave.y + 0));

        Xwing.getArrayFiguras().add(new figura("Triangulo", 0, Color.BLACK, Color.WHITE));
        Xwing.getArrayFiguras().get(7).addPunto(new punto(preferenciaNave.x - 10, preferenciaNave.y + 4));
        Xwing.getArrayFiguras().get(7).addPunto(new punto((preferenciaNave.x - 25), preferenciaNave.y + 0));
        Xwing.getArrayFiguras().get(7).addPunto(new punto((preferenciaNave.x - 10), preferenciaNave.y - 4));

        arrayDibujos.add(Xwing);

        hilo = new Thread(this);
        hilo.start();

    }

    public void putPixel(int x, int y, Color c, Graphics g) {

        buffer.setRGB(0, 0, c.getRGB());

        g.drawImage(buffer, x + 600, y + 400, this);
    }

    private void drawLine(int x1, int y1, int x2, int y2, Color c, Graphics g) {
        double dx, dy, xinc, yinc, x, y;
        int pasos;
        dx = x2 - x1;
        dy = y2 - y1;
        if (Math.abs(dx) > Math.abs(dy)) {
            pasos = (int) Math.abs(dx);
        } else {
            pasos = (int) Math.abs(dy);
        }
        xinc = dx / pasos;
        yinc = dy / pasos;
        x = x1;
        y = y1;

        for (int i = 1; i <= pasos; i++) {
            x = x + xinc;
            y = y + yinc;

            putPixel((int) Math.round(x), (int) Math.round(y), c, g);
        }
    }

    private void drawRectangle(double x1, double y1, double x2, double y2, Color relleno, Color borde, Graphics g, Boolean ConRelleno) {
        arrLineas.clear();

        drawLine((int) x1, (int) y1, (int) x1, (int) y2, borde, g);
        drawLine((int) x1, (int) y1, (int) x2, (int) y1, borde, g);
        drawLine((int) x2, (int) y1, (int) x2, (int) y2, borde, g);
        drawLine((int) x1, (int) y2, (int) x2, (int) y2, borde, g);
        arrLineas.add(new lineas(new punto(x1, y1), new punto(x1, y2), relleno));
        arrLineas.add(new lineas(new punto(x1, y1), new punto(x2, y1), relleno));
        arrLineas.add(new lineas(new punto(x2, y1), new punto(x2, y2), relleno));
        arrLineas.add(new lineas(new punto(x1, y2), new punto(x2, y2), relleno));
        if (ConRelleno) {
            RellenoRectangulo(relleno, g);
        }
    }

    private void drawTriangle(punto p1, punto p2, punto p3, Color relleno, Color borde, Graphics g, Boolean ConRelleno) {
        arrLineas.clear();
        drawLine((int) p1.x, (int) p1.y, (int) p2.x, (int) p2.y, borde, g);
        drawLine((int) p2.x, (int) p2.y, (int) p3.x, (int) p3.y, borde, g);
        drawLine((int) p1.x, (int) p1.y, (int) p3.x, (int) p3.y, borde, g);
        arrLineas.add(new lineas(p1, p2, relleno));
        arrLineas.add(new lineas(p2, p3, relleno));
        arrLineas.add(new lineas(p3, p1, relleno));
        if (ConRelleno) {
            Rellenotriangulo(relleno, g);
        }

    }

    private void drawElipse(punto origen, double radio1, double radio2, Color relleno, Color borde, Graphics g, Boolean ConRelleno) {

        int x = 0, y = 0;

        for (int i = 0; i < 90; i++) {
            x = (int) (origen.x + radio1 * Math.cos(i));
            y = (int) (origen.y + radio2 * Math.sin(i));
            drawLine((int) origen.x, (int) origen.y, x, y, relleno, g);
            if (ConRelleno) {
                putPixel(x, y, borde, g);
            }
            //  putPixel(x, -y, borde,g);
            //putPixel(-x, -y, borde,g);
            // putPixel(-x, y, borde,g);

        }

    }

    private void drawCircle(punto origen, double radio, Color relleno, Color borde, Graphics g, Boolean ConRelleno) {
        int xorigen = (int) origen.x;
        int yorigen = (int) origen.y;
        int rd1 = (int) radio;
        int x = rd1;
        int y = 0;
        int xpix, ypix;

        putPixel(x + xorigen, y + yorigen, borde, g);
        drawLine((int) origen.x, (int) origen.y, x + xorigen, y + yorigen, relleno, g);
        if (rd1 > 0) {
            putPixel(x + xorigen, y + yorigen, borde, g);
            drawLine((int) origen.x, (int) origen.y, x + xorigen, y + yorigen, relleno, g);
            putPixel(x + xorigen, -y + yorigen, borde, g);
            drawLine((int) origen.x, (int) origen.y, x + xorigen, -y + yorigen, relleno, g);
            putPixel(y + xorigen, x + yorigen, borde, g);
            drawLine((int) origen.x, (int) origen.y, y + xorigen, x + yorigen, relleno, g);
            putPixel(-y + xorigen, x + yorigen, borde, g);
            drawLine((int) origen.x, (int) origen.y, -y + xorigen, x + yorigen, relleno, g);
        }

        int P = 1 - rd1;
        while (x > y) {

            y++;

            if (P <= 0) {
                P = P + 2 * y + 1;
            } else {
                x--;
                P = P + 2 * y - 2 * x + 1;
            }

            if (x < y) {
                break;
            }
            putPixel(xorigen, yorigen, Color.GREEN, g);
            xpix = x + xorigen;
            ypix = y + yorigen;
            putPixel(xpix, ypix, borde, g);
            drawLine(xorigen, yorigen, xpix, ypix, relleno, g);
            drawLine(xpix, ypix, xorigen, yorigen, relleno, g);

            xpix = -x + xorigen;
            ypix = y + yorigen;

            putPixel(xpix, ypix, borde, g);
            drawLine(xorigen, yorigen, xpix, ypix, borde, g);

            xpix = x + xorigen;
            ypix = -y + yorigen;

            putPixel(xpix, ypix, borde, g);
            drawLine(xorigen, yorigen, xpix, ypix, borde, g);

            xpix = x + xorigen;
            ypix = -y + yorigen;
            putPixel((-x + xorigen), (-y + yorigen), borde, g);
            drawLine((int) origen.x, (int) origen.y, (-x + xorigen), (-y + yorigen), relleno, g);

            if (x != y) {

                putPixel((y + xorigen), (x + yorigen), borde, g);
                drawLine((int) origen.x, (int) origen.y, (y + xorigen), (x + yorigen), relleno, g);

                putPixel((-y + xorigen), (x + yorigen), borde, g);
                drawLine((int) origen.x, (int) origen.y, (-y + xorigen), (x + yorigen), relleno, g);

                putPixel(y + xorigen, -x + yorigen, borde, g);
                drawLine((int) origen.x, (int) origen.y, (y + xorigen), (-x + yorigen), relleno, g);

                putPixel(-y + xorigen, -x + yorigen, borde, g);
                drawLine((int) origen.x, (int) origen.y, (-y + xorigen), (-x + yorigen), relleno, g);

            }
        }
    }

    @Override
    public void paint(Graphics g) {

    }

    public void dibujar() {
        //  gra.drawRect(0, 0, WIDTH, HEIGHT);
        BufferStrategy bs = getBufferStrategy(); //Gets the buffer strategy our canvas is currently using
        if (bs == null) { //True if our canvas has no buffer strategy (should only happen once when we first start the game)
            createBufferStrategy(2); //Create a buffer strategy using two buffers (double buffer the canvas)
            return;
        }
        Graphics gra = bs.getDrawGraphics();
        gra = bs.getDrawGraphics();
        this.drawRectangle(-600, -400, 500, 500, Color.BLACK, Color.BLUE, gra, true);//Es el fondo

        gra.drawRect(0, 0, WIDTH, HEIGHT);

        this.setBackground(Color.WHITE);
        this.setForeground(Color.white);
        offScreenGraphics.clearRect(0, 0, WIDTH, HEIGHT);
        offScreenGraphics.drawRect(0, 0, WIDTH, HEIGHT);

        for (dibujo d : arrayDibujos) {

            for (figura Arrf : d.arrayFiguras) {

                switch (Arrf.figura) {
                    case "Triangulo":
                        this.drawTriangle(Arrf.puntos.get(0), Arrf.puntos.get(1), Arrf.puntos.get(2), Arrf.relleno, Arrf.borde, gra, d.ConRelleno);
                        break;
                    case "Rectangulo":
                        this.drawRectangle(Arrf.puntos.get(0).x, Arrf.puntos.get(0).y, Arrf.puntos.get(1).x, Arrf.puntos.get(1).y, Arrf.relleno, Arrf.borde, gra, d.ConRelleno);
                        break;
                    case "Circulo":
                        this.drawCircle(Arrf.puntos.get(0), Arrf.radio, Arrf.relleno, Arrf.borde, gra, d.ConRelleno);
                        break;
                    case "Elipse":

                        this.drawElipse(Arrf.puntos.get(0), Arrf.radio, Arrf.radio2, Arrf.relleno, Arrf.borde, gra, d.ConRelleno);
                        break;
                }
            }
        }

        gra.dispose();
        bs.show();
    }

    public void RellenoRectangulo(Color relleno, Graphics g) {
        int numLinea1 = 0, numLinea2 = 0, cont = 0;

        if (arrLineas.get(1).B.x > arrLineas.get(1).A.x) {//ENTONCES B DEBE SER MENOR, lo acomodamos
            punto aux = arrLineas.get(1).A;
            arrLineas.get(1).A = arrLineas.get(1).B;
            arrLineas.get(1).B = aux;
        }
        if (arrLineas.get(3).B.x > arrLineas.get(3).A.x) {//ENTONCES B DEBE SER MENOR, lo acomodamos
            punto aux = arrLineas.get(3).A;
            arrLineas.get(3).A = arrLineas.get(3).B;
            arrLineas.get(3).B = aux;
        }

        pendienteLo = (arrLineas.get(1).B.y - arrLineas.get(1).A.y) / (arrLineas.get(1).B.x - arrLineas.get(1).A.x);
        pendienteSeg = (arrLineas.get(3).B.y - arrLineas.get(3).A.y) / (arrLineas.get(3).B.x - arrLineas.get(3).A.x);
        double contadorPix = arrLineas.get(3).B.x;
        //Menor                                 //Mayor punto
        for (int i = (int) arrLineas.get(1).B.x; i < (int) arrLineas.get(1).A.x; i++) {

//Recorre cada pixel de la primera linea
            //Calcula los siguientes puntos
            double yaux = -1 * (pendienteLo * (arrLineas.get(1).B.x - i) - arrLineas.get(1).B.y);

            //Se calcula la pendiente 
            double yaux2 = -1 * (pendienteSeg * (arrLineas.get(3).B.x - contadorPix) - arrLineas.get(3).B.y);

            drawLine((int) i, (int) yaux, (int) contadorPix, (int) yaux2, relleno, g);  //este lo dibuja bien

            contadorPix++;
        }

    }

    public void Rellenotriangulo(Color relleno, Graphics g) {

        int numLinea1 = 0, numLinea2 = 0, cont = 0;

        double difx = arrLineas.get(1).A.x - arrLineas.get(1).B.x;
        double dify = arrLineas.get(1).A.y - arrLineas.get(1).B.y;

        if (Math.abs(difx) > Math.abs(dify)) {

            if (arrLineas.get(1).B.x > arrLineas.get(1).A.x) {//ENTONCES B DEBE SER MENOR, lo acomodamos
                punto aux = arrLineas.get(1).A;
                arrLineas.get(1).A = arrLineas.get(1).B;
                arrLineas.get(1).B = aux;
            }
            if (arrLineas.get(0).B.x > arrLineas.get(0).A.x) {//ENTONCES B DEBE SER MENOR, lo acomodamos
                punto aux = arrLineas.get(0).A;
                arrLineas.get(0).A = arrLineas.get(0).B;
                arrLineas.get(0).B = aux;
            }

            pendienteLo = (arrLineas.get(1).B.y - arrLineas.get(1).A.y) / (arrLineas.get(1).B.x - arrLineas.get(1).A.x);
            pendienteSeg = (arrLineas.get(0).B.y - arrLineas.get(0).A.y) / (arrLineas.get(0).B.x - arrLineas.get(0).A.x);

            double contadorPix = arrLineas.get(0).B.x;

            //Menor                                 //Mayor punto
            for (int i = (int) arrLineas.get(1).B.x; i < (int) arrLineas.get(1).A.x; i++) { //Recorre cada pixel de la primera linea
                //Calcula los siguientes puntos
                if (contadorPix > arrLineas.get(0).A.x) {
                    break;
                }
                double yaux = -1 * (pendienteLo * (arrLineas.get(1).B.x - i) - arrLineas.get(1).B.y);

                //Se calcula la pendiente 
                double yaux2 = -1 * (pendienteSeg * (arrLineas.get(0).B.x - contadorPix) - arrLineas.get(0).B.y);

                drawLine((int) i, (int) yaux, (int) contadorPix, (int) yaux2, relleno, g);  //este lo dibuja bien

                contadorPix++;

            }

        } else {

            if (arrLineas.get(1).B.y > arrLineas.get(1).A.y) {//ENTONCES B DEBE SER MENOR, lo acomodamos
                punto aux = arrLineas.get(1).A;
                arrLineas.get(1).A = arrLineas.get(1).B;
                arrLineas.get(1).B = aux;
            }
            if (arrLineas.get(0).B.y > arrLineas.get(0).A.y) {//ENTONCES B DEBE SER MENOR, lo acomodamos
                punto aux = arrLineas.get(0).A;
                arrLineas.get(0).A = arrLineas.get(0).B;
                arrLineas.get(0).B = aux;
            }

            pendienteLo = (arrLineas.get(1).B.y - arrLineas.get(1).A.y) / (arrLineas.get(1).B.x - arrLineas.get(1).A.x);
            pendienteSeg = (arrLineas.get(0).B.y - arrLineas.get(0).A.y) / (arrLineas.get(0).B.x - arrLineas.get(0).A.x);

            double contadorPix = arrLineas.get(0).B.y;

            //Menor                                 //Mayor punto
            for (int i = (int) arrLineas.get(1).B.y; i < (int) arrLineas.get(1).A.y; i++) { //Recorre cada pixel de la primera linea
                //Calcula los siguientes puntos
                if (contadorPix > arrLineas.get(0).A.y) {
                    break;
                }
                double xaux = -1 * (((arrLineas.get(1).B.y - i) / pendienteLo) - arrLineas.get(1).B.x);

                //Se calcula la pendiente 
                double xaux2 = -1 * ((arrLineas.get(0).B.y - contadorPix) / pendienteSeg - arrLineas.get(0).B.x);

                drawLine((int) xaux2, (int) i, (int) xaux, (int) i, relleno, g);
                contadorPix++;

            }

        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        Animacion e = new Animacion();
    }

    @Override
    public void run() {

        try {

            dibujar();
            EscalarDibujo(Xwing, 2, 2);
            sleep(60);
            dibujar();

            RotarDibujo(estrella, -45);
            EscalarDibujo(nave3, 1.2, 1.2);
            EscalarDibujo(nave4, 1.2, 1.2);
            EscalarDibujo(nave2, 1.2, 1.2);
            TrasladarDibujo(nave2, 50, 0);
            TrasladarDibujo(nave3, 50, 0);
            TrasladarDibujo(nave4, 50, 0);
            TrasladarDibujo(halcon, 100, 0);
            TrasladarDibujo(Tie1, 100, 0);
            TrasladarDibujo(disparo1, 150, 0);
            TrasladarDibujo(disparo2, 150, 0);

            sleep(60);
            dibujar();

            RotarDibujo(estrella, -45);

            TrasladarDibujo(nave2, 50, 0);
            TrasladarDibujo(nave3, 50, 0);
            TrasladarDibujo(nave4, 50, 0);
            TrasladarDibujo(disparo1, 40, 20);
            TrasladarDibujo(disparo2, 40, -20);

            sleep(60);
            dibujar();

            RotarDibujo(estrella, -45);

            RotarDibujo(estrella, -10);
            TrasladarDibujo(nave2, 150, -50);
            TrasladarDibujo(nave3, 150, 0);
            TrasladarDibujo(nave4, 150, 50);
            TrasladarDibujo(halcon, 200, 0);
            TrasladarDibujo(disparo1, 500, 20);
            TrasladarDibujo(disparo2, 500, -20);
            RotarDibujo(Tie1, 30);
            TrasladarDibujo(Tie1, 100, 100);

            sleep(60);
            dibujar();

            TrasladarDibujo(disparo3, 483, -278);
            EscalarDibujo(nave3, .5, .5);

            TrasladarDibujo(nave2, 150, 0);
            TrasladarDibujo(nave3, 150, 0);
            TrasladarDibujo(nave4, 150, 0);
            TrasladarDibujo(halcon, 250, 0);
            TrasladarDibujo(Tie1, 100, 100);
            TrasladarDibujo(arwing, 200, 0);
            TrasladarDibujo(Tie2, 200, 0);
            TrasladarDibujo(Tie3, 150, 150);

            sleep(60);
            dibujar();
            EscalarDibujo(nave4, .5, .5);
            EscalarDibujo(nave2, .5, .5);
            TrasladarDibujo(nave2, 150, 0);
            TrasladarDibujo(nave3, 150, 0);
            TrasladarDibujo(nave4, 150, 0);
            TrasladarDibujo(halcon, 250, 0);
            TrasladarDibujo(Tie1, 100, 100);
            TrasladarDibujo(disparo3, 200, -200);
            TrasladarDibujo(Tie2, 200, 0);
            TrasladarDibujo(Tie3, 200, 0);
            TrasladarDibujo(arwing, 200, 0);
            sleep(30);
            dibujar();
            TrasladarDibujo(nave2, 250, 0);
            TrasladarDibujo(nave3, 250, 0);
            RotarDibujo(halcon, 180);

            RotarDibujo(arwing, 65);
            TrasladarDibujo(nave4, 250, 0);
            TrasladarDibujo(disparo3, 200, -200);
            TrasladarDibujo(Tie2, 200, 0);
            TrasladarDibujo(Tie3, 200, 0);
            sleep(30);
            dibujar();
            EscalarDibujo(planeta, 1.2, 1.2);//Exploto planeta
            TrasladarDibujo(nave2, 150, 0);
            arwing.ConRelleno = false;
            TrasladarDibujo(nave3, 150, 0);
            TrasladarDibujo(nave4, 150, 0);
            TrasladarDibujo(disparo3, 500, -500);
            TrasladarDibujo(Tie2, 200, 0);
            TrasladarDibujo(Tie3, 200, 0);
            sleep(30);
            dibujar();
            EscalarDibujo(planeta, .5, .5);
            TrasladarDibujo(arwing, 400, 400);
            TrasladarDibujo(Tie2, 200, 0);
            TrasladarDibujo(Tie3, 200, 0);
            sleep(30);
            dibujar();
            EscalarDibujo(planeta, .8, .8);
            TrasladarDibujo(Tie2, 200, 0);
            RotarDibujo(arwing, -65);
            TrasladarDibujo(Tie3, 200, 0);
            sleep(30);
            dibujar();
            arwing.ConRelleno = true;
            TrasladarDibujo(Tie2, 200, 0);
            TrasladarDibujo(Tie3, 200, 0);
            EscalarDibujo(planeta, .7, .7);
            sleep(30);
            dibujar();
            TrasladarDibujo(Tie2, 200, 0);
            TrasladarDibujo(arwing, 420, 0);
            TrasladarDibujo(Tie3, 200, 0);
            EscalarDibujo(planeta, .3, .3);
            TrasladarDibujo(halcon, -250, -200);
            sleep(30);
            dibujar();
            EscalarDibujo(planeta, 0, 0);
            TrasladarDibujo(arwing, 50, 0);
            TrasladarDibujo(disparo4, -300, 100);
            TrasladarDibujo(Xwing, -300, 100);
            TrasladarDibujo(halcon, -150, -100);
            sleep(30);
            dibujar();
            TrasladarDibujo(halcon, -250, 0);
            TrasladarDibujo(disparo4, -200, 0);
            TrasladarDibujo(disparo4, -100, 0);
            TrasladarDibujo(Xwing, -200, 0);
            RotarDibujo(estrella, -20);
            RotarDibujo(arwing, -20);
            RotarDibujo(Xwing, 180);
            sleep(30);
            dibujar();
            TrasladarDibujo(halcon, -250, 0);//disparar
            TrasladarDibujo(disparo4, -250, 0);
            TrasladarDibujo(Xwing, 100, 0);
            sleep(30);
            dibujar();
            TrasladarDibujo(halcon, -50, 0);
            TrasladarDibujo(Xwing, 250, 0);
            TrasladarDibujo(disparo4, -100, 0);
            RotarDibujo(estrella, -15);

            sleep(30);
            dibujar();
            TrasladarDibujo(halcon, -50, 0);
            TrasladarDibujo(Xwing, 250, 0);
            TrasladarDibujo(disparo4, -600, 0);
            EscalarDibujo(estrella, 1.2, 1.2);

            sleep(30);
            dibujar();
            TrasladarDibujo(halcon, -150, 0);

            EscalarDibujo(estrella, .2, .2);

            sleep(30);
            dibujar();
            TrasladarDibujo(halcon, -350, 0);

            EscalarDibujo(estrella, 0, 0);

            sleep(30);
            dibujar();

        } catch (Exception e) {

        }

    }

    public void escalacion(punto T, double Sx, double Sy, int cont, punto Escalado1, punto pNoesc1, String figura) {
        //matrizp
        double diferenciaEscx, diferenciaEscy, diferenciaNoEscx, diferenciaNoEscy, aumx, aumy;
        p[0] = T.x;
        p[1] = T.y;
        if (figura == "Circulo") {
            T.radio = T.radio * Sx;

        }

        xprima = p[0] * Sx;
        yprima = p[1] * Sy;

        if (cont == 0) {
            Escalado1.x = xprima;
            Escalado1.y = yprima;
        } else {
            diferenciaEscx = xprima - Escalado1.x;
            diferenciaEscy = yprima - Escalado1.y;

            diferenciaNoEscx = T.x - pNoesc1.x;
            diferenciaNoEscy = T.y - pNoesc1.y;

            aumx = diferenciaEscx - diferenciaNoEscx;
            aumy = diferenciaEscy - diferenciaNoEscy;

            T.x = T.x + aumx;
            T.y = T.y + aumy;
        }

    }

    public void Traslacion(punto T, double incx, double incy) {
        p[0] = T.x;
        p[1] = T.y;

        xprima = p[0] + incx;
        yprima = p[1] + incy;
        T.x = (int) xprima;
        T.y = (int) yprima;

    }

    public void TrasladarDibujo(dibujo d, double x, double y) {
        for (figura Arrf : d.arrayFiguras) {
            for (punto p : Arrf.puntos) {
                Traslacion(p, x, y);
            }
        }
    }

    public void EscalarDibujo(dibujo d, double sx, double sy) {
        punto pEscalado1, pNoesc1 = new punto(0, 0);

        pEscalado1 = new punto(0, 0);
        int cont = 0;

        for (figura Arrf : d.arrayFiguras) {

            for (punto p : Arrf.puntos) {
                if (cont == 0) {
                    pNoesc1 = new punto(p.getX(), p.getY());
                }

                if (Arrf.figura == "Circulo") {
                    Arrf.radio = Arrf.radio * sx;

                }
                if (Arrf.figura == "Elipse") {
                    Arrf.radio = Arrf.radio * sx;
                    Arrf.radio2 = Arrf.radio2 * sx;
                }
                escalacion(p, sx, sy, cont, pEscalado1, pNoesc1, Arrf.figura);
                if (cont == 0) {

                }
                cont++;
            }
        }
    }

    public void rotacion(punto T, double a, punto Origen) {
        //matrizp
        this.angulo = a;

        p[0] = T.x - Origen.x;
        p[1] = T.y - Origen.y;

        rotacion[0][0] = (Math.cos(Math.toRadians(angulo)));
        rotacion[0][1] = (-Math.sin(Math.toRadians(angulo)));
        rotacion[0][2] = 0;

        rotacion[1][0] = (Math.sin(Math.toRadians(angulo)));
        rotacion[1][1] = (Math.cos(Math.toRadians(angulo)));
        rotacion[1][2] = 0;

        rotacion[2][0] = 0;
        rotacion[2][1] = 0;
        rotacion[2][2] = 1;

        xprima = p[0] * (rotacion[0][0]) + p[1] * (rotacion[0][1]);
        yprima = p[0] * (rotacion[1][0]) + p[1] * (rotacion[1][1]);

        T.x = xprima + Origen.x;
        T.y = yprima + Origen.y;

    }

    public void RotarDibujo(dibujo d, double angulo) {

        punto pOrigen = new punto(0, 0);
        int cont = 0;
        for (figura Arrf : d.arrayFiguras) {

            for (punto p : Arrf.puntos) {
                if (cont == 0) {
                    double xeje = 0, yeje = 0;
                    xeje = p.getX();
                    yeje = p.getY();

                    pOrigen = new punto(xeje, yeje);

                    if (Arrf.figura == "Circulo") {
                        pOrigen = new punto(p.getX(), p.getY());
                    }
                }
                rotacion(p, angulo, pOrigen);

                cont++;
            }
        }

    }
}
