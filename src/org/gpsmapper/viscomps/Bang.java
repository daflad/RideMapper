/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gpsmapper.viscomps;

import org.gpsmapper.viscomps.interfaces.ButtonInterface;
import processing.core.*;

/**
 * Class defining GUI buttons.
 * 
 * @author StephenJohnRussell
 * @version 0.1
 */
public class Bang extends java.awt.Polygon implements ButtonInterface {

    private PApplet p;

    public Bang(int[] x, int[] y, int n, PApplet p) {
        //call the java.awt.Polygon constructor
        super(x, y, n);
        this.p = p;
    }

    @Override
    public void drawMe() {
        p.noStroke();
        p.beginShape();
        for (int i = 0; i < npoints; i++) {
            p.vertex(xpoints[i], ypoints[i]);
        }
        p.endShape(PApplet.CLOSE);
    }

    @Override
    public void roundedButton(float x, float y, float w, float h, float rx, float ry) {

        p.noStroke();
        p.beginShape();
        p.vertex(x, y + ry); //top of left side 
        p.bezierVertex(x, y, x, y, x + rx, y); //top left corner

        p.vertex(x + w - rx, y); //right of top side 
        p.bezierVertex(x + w, y, x + w, y, x + w, y + ry); //top right corner

        p.vertex(x + w, y + h - ry); //bottom of right side
        p.bezierVertex(x + w, y + h, x + w, y + h, x + w - rx, y + h); //bottom right corner

        p.vertex(x + rx, y + h); //left of bottom side
        p.bezierVertex(x, y + h, x, y + h, x, y + h - ry); //bottom left corner

        p.endShape(PApplet.CLOSE);
    }

    @Override
    public boolean mouseMoved(int x, int y) {
        return contains(x, y);
    }

    @Override
    public boolean mousePressed(int x, int y) {
        if (p.mousePressed) {
            return contains(x, y);
        } else {
            return false;
        }
    }

    @Override
    public boolean doubleClick() {
        if (p.mouseEvent.getClickCount() == 2) {
            return true;
        } else {
            return false;
        }
    }
}
