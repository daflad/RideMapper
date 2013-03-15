/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gpsmapper.viscomps;

import de.fhpotsdam.unfolding.geo.Location;
import org.gpsmapper.core.GPSMapper;

/**
 * Class to handle toolbox when right mouse button is pressed.
 *
 * @author StephenJohnRussell
 */
public final class ToolBox {

    Bang panUp;
    Bang panDown;
    Bang panLeft;
    Bang panRight;
    Bang closeTool;
    Bang homeBut;
    Bang decZoom;
    Bang incZoom;
    Bang elevBut;
    int r;
    int f;
    GPSMapper p;
    int[] xs;
    int[] ys;
    Location home;

    public ToolBox(int r, int f, Location home, GPSMapper p) {        
        this.home = home;
        this.p = p;
        this.r = r;
        this.f = f;

        // Button shapes
        triButSetup(r - 40, f - 80, 40, 'u');
        panUp = new Bang(xs, ys, 3, p);
        triButSetup(r - 40, f + 40, 40, 'd');
        panDown = new Bang(xs, ys, 3, p);
        triButSetup(r - 80, f - 40, 40, 'l');
        panLeft = new Bang(xs, ys, 3, p);
        triButSetup(r + 40, f - 40, 40, 'r');
        panRight = new Bang(xs, ys, 3, p);
        triButSetup(r + 20, f + 20, 30, 'p');
        closeTool = new Bang(xs, ys, 3, p);
        triButSetup(r + 20, f - 80, 30, 'h');
        homeBut = new Bang(xs, ys, 3, p);
        rectButSetup(r - 40, f + 15, 80, 25);
        decZoom = new Bang(xs, ys, 4, p);
        rectButSetup(r - 40, f - 40, 80, 25);
        incZoom = new Bang(xs, ys, 4, p);
        triButSetup(r - 80, f + 20, 30, 'e');
        elevBut = new Bang(xs, ys, 3, p);
    }

    public void drawToolBox(MasterMap theMap) {
        p.noStroke();
        //Draw Button Grid
        p.fill(255, 0, 0, 150);
        homeBut.drawMe();
        closeTool.drawMe();
        decZoom.drawMe();
        incZoom.drawMe();
        elevBut.drawMe();
        p.fill(0, 0, 0, 150);
        panUp.drawMe();
        panDown.drawMe();
        panLeft.drawMe();
        panRight.drawMe();
        lables();
        onBang(theMap);
    }

    public void lables() {
        //Labels
        if (closeTool.mouseMoved(p.mouseX, p.mouseY)) {
            p.fill(255, 0, 0, 100);
            closeTool.drawMe();
            p.fill(0);
            p.text("CLOSE", r - 40, f - 5, 80, 40);
            p.text("X", r + 50, f + 60, 25, 25);
        } else {
            p.fill(255);
            p.text("X", r + 50, f + 60, 25, 25);
        }

        if (homeBut.mouseMoved(p.mouseX, p.mouseY)) {
            p.fill(255, 0, 0, 100);
            homeBut.drawMe();
            p.fill(0);
            p.text("HOME", r - 40, f - 5, 85, 20);
            p.text("H", r + 50, f - 65, 20, 20);
        } else {
            p.fill(255);
            p.text("H", r + 50, f - 65, 20, 20);
        }

        if (decZoom.mouseMoved(p.mouseX, p.mouseY)) {
            p.fill(255, 0, 0, 100);
            decZoom.drawMe();
            p.fill(0);
            p.text("ZOOM", r - 40, f - 10, 85, 20);
            p.text("OUT", r - 40, f, 85, 20);
            p.text("-", r - 10, f + 20, 20, 20);
        } else {
            p.fill(255);
            p.text("-", r - 10, f + 20, 20, 20);
        }
        if (incZoom.mouseMoved(p.mouseX, p.mouseY)) {
            p.fill(255, 0, 0, 100);
            incZoom.drawMe();
            p.fill(0);
            p.text("ZOOM", r - 40, f - 10, 85, 20);
            p.text("IN", r - 40, f, 85, 20);
            p.text("+", r - 10, f - 30, 20, 20);
        } else {
            p.fill(255);
            p.text("+", r - 10, f - 30, 20, 20);
        }
        if (elevBut.mouseMoved(p.mouseX, p.mouseY)) {
            p.fill(255, 0, 0, 100);
            elevBut.drawMe();
            p.fill(0);
            p.text("SHOW", r - 40, f - 10, 85, 20);
            p.text("ELEV", r - 40, f, 85, 20);
            p.text("El", r - 70, f + 60, 20, 20);
        } else {
            p.fill(255);
            p.text("El", r - 70, f + 60, 20, 20);
        }
    }

    public void onBang(MasterMap theMap) {
        p.fill(0);
        if (panUp.mouseMoved(p.mouseX, p.mouseY)) {
            p.text("PAN", r - 40, f - 10, 85, 20);
            p.text("UP", r - 40, f, 85, 20);
            p.fill(195, 0, 0, 100);
            panUp.drawMe();
            if (p.mousePressed) {
                p.fill(125, 0, 0, 100);
                panUp.drawMe();
                theMap.pan(p.width / 2, p.height / 2, p.width / 2, p.height / 2 + 25);
            }
        } else if (panDown.mouseMoved(p.mouseX, p.mouseY)) {
            p.text("PAN", r - 40, f - 10, 85, 20);
            p.text("DOWN", r - 40, f, 85, 20);
            p.fill(195, 0, 0, 100);
            panDown.drawMe();
            if (p.mousePressed) {
                p.fill(125, 0, 0, 100);
                panDown.drawMe();
                theMap.pan(p.width / 2, p.height / 2, p.width / 2, p.height / 2 - 25);
            }
        } else if (panLeft.mouseMoved(p.mouseX, p.mouseY)) {
            p.text("PAN", r - 40, f - 10, 85, 20);
            p.text("LEFT", r - 40, f, 85, 20);
            p.fill(195, 0, 0, 100);
            panLeft.drawMe();
            if (p.mousePressed) {
                p.fill(125, 0, 0, 100);
                panLeft.drawMe();
                theMap.pan(p.width / 2, p.height / 2, p.width / 2 + 25, p.height / 2);
            }
        } else if (panRight.mouseMoved(p.mouseX, p.mouseY)) {
            p.text("PAN", r - 40, f - 10, 85, 20);
            p.text("RIGHT", r - 40, f, 85, 20);
            p.fill(195, 0, 0, 100);
            panRight.drawMe();
            if (p.mousePressed) {
                p.fill(125, 0, 0, 100);
                panRight.drawMe();
                theMap.pan(p.width / 2, p.height / 2, p.width / 2 - 25, p.height / 2);
            }
        }
        if (closeTool.mousePressed(p.mouseX, p.mouseY)) {
            p.toolBox = false;
        }
        if (homeBut.mousePressed(p.mouseX, p.mouseY)) {
            theMap.zoomAndPanTo(home, 11);
            p.toolBox = false;
        }
        if (decZoom.mousePressed(p.mouseX, p.mouseY)) {
            theMap.zoomOut();
        }
        if (incZoom.mousePressed(p.mouseX, p.mouseY)) {
            theMap.zoomIn();
        }
        if (elevBut.mousePressed(p.mouseX, p.mouseY)) {
            p.elevation = true;
        }
    }

    //d = down, u = up, l = left, r = right
    public void triButSetup(int xPos, int yPos, int s, char direction) {
        if (direction == 'u') {
            xs = new int[]{xPos + s, xPos + 2 * s, xPos};
            ys = new int[]{yPos, yPos + s, yPos + s};
        } else if (direction == 'd') {
            xs = new int[]{xPos, xPos + 2 * s, xPos + s};
            ys = new int[]{yPos, yPos, yPos + s};
        } else if (direction == 'r') {
            xs = new int[]{xPos, xPos + s, xPos};
            ys = new int[]{yPos, yPos + s, yPos + 2 * s};
        } else if (direction == 'l') {
            xs = new int[]{xPos + s, xPos + s, xPos};
            ys = new int[]{yPos, yPos + 2 * s, yPos + s};
        } else if (direction == 'p') {
            xs = new int[]{xPos + 2 * s, xPos + 2 * s, xPos};
            ys = new int[]{yPos, yPos + 2 * s, yPos + 2 * s};
        } else if (direction == 'e') {
            xs = new int[]{xPos + 2 * s, xPos, xPos};
            ys = new int[]{yPos + 2 * s, yPos + 2 * s, yPos};
        } else if (direction == 'h') {
            xs = new int[]{xPos, xPos + 2 * s, xPos + 2 * s};
            ys = new int[]{yPos, yPos, yPos + 2 * s};
        }
    }

    // width & height
    public void rectButSetup(int xPos, int yPos, int w, int h) {
        xs = new int[]{xPos, xPos + w, xPos + w, xPos};
        ys = new int[]{yPos, yPos, yPos + h, yPos + h};
    }
}
