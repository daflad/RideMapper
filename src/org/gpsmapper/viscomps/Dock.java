/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gpsmapper.viscomps;

import org.gpsmapper.core.GPSMapper;
import org.gpsmapper.core.RouteList;
import org.gpsmapper.viscomps.interfaces.DockInterface;

/**
 *
 * @author StephenJohnRussell
 * @version 0.1
 */
public final class Dock implements DockInterface {

    Bang recordRoute;
    Bang bangRoute;
    Bang bangElev;
    Bang routeOff;
    Bang routeList;
    Bang dockBase;
    String loadPath;
    boolean warningLoad = false;
    boolean warningDraw = false;
    GPSMapper p;
    int[] xs;
    int[] ys;

    public Dock(GPSMapper p) {
        this.p = p;
        rectButSetup(((p.screenWidth - 900) / 2), 10, 80, 40);
        recordRoute = new Bang(xs, ys, 4, p);
        rectButSetup(((p.screenWidth - 500) / 2), 10, 80, 40);
        bangElev = new Bang(xs, ys, 4, p);
        rectButSetup(((p.screenWidth - 300) / 2), 10, 80, 40);
        routeOff = new Bang(xs, ys, 4, p);
        rectButSetup(((p.screenWidth - 700) / 2), 10, 80, 40);
        bangRoute = new Bang(xs, ys, 4, p);
    }

    @Override
    public void drawDock(RouteList routes) {
        p.stroke(2);
        p.fill(125, 135, 197, 50);
        rectButSetup(((p.screenWidth - 1000) / 2), -20, 1000, 90);
        dockBase = new Bang(xs, ys, 4, p);
        dockBase.roundedButton(((p.screenWidth - 1000) / 2), -20, 1000, 90, 20, 20);
        addButtonToDock(recordRoute, 900, "RECORD", "ROUTE", p.drawRoute);
        addButtonToDock(bangRoute, 700, "DRAW", "ROUTE", p.drawRoute);
        addButtonToDock(bangElev, 500, "DRAW", "ELEVATION", p.drawRoute);
        addButtonToDock(routeOff, 300, "HIDE", "ALL", p.drawRoute);
        loadedRoutes(routes);
        buttonBehaviour();
    }

    @Override
    public void addButtonToDock(Bang but, int d, String lineOne, String lineTwo, boolean bool) {
        if (but.mouseMoved(p.mouseX, p.mouseY)) {
            p.fill(125, 135, 255, 100);
            but.roundedButton(((p.screenWidth - d) / 2), 10, 80, 40, 20, 20);
        } else {
            but.roundedButton(((p.screenWidth - d) / 2), 10, 80, 40, 20, 20);
        }
        //Labels
        p.fill(0);
        p.text(lineOne, ((p.screenWidth - d) / 2), 17, 80, 40);
        p.text(lineTwo, ((p.screenWidth - d) / 2), 32, 80, 40);
        //Reset colour
        p.fill(125, 135, 197, 100);
    }

    @Override
    public void buttonBehaviour() {
        if (bangRoute.mousePressed(p.mouseX, p.mouseY)) {
            p.drawRoute = true;
        }
        if (bangElev.mousePressed(p.mouseX, p.mouseY)) {
            p.elevation = true;
        }
        if (recordRoute.mousePressed(p.mouseX, p.mouseY)) {
            p.drawRoute = false;
            p.elevation = false;
//      recordRoute = true;
        }
        if (routeOff.mousePressed(p.mouseX, p.mouseY)) {
            p.drawRoute = false;
            p.elevation = false;
        }
    }

    @Override
    public void loadedRoutes(RouteList routes) {
        int divider = 0;
        int xCor = 10;
        int rowCounter = 0;
        for (int i = 0; i < routes.getRoutes().size(); i++) {
            if (rowCounter == 10) {
                xCor += 45;
                divider = 0;
                rowCounter = 0;
            }
            rectButSetup(((p.screenWidth - 100) / 2) + divider, xCor, 40, 40);
            routeList = new Bang(xs, ys, 4, p);
            if (routeList.mouseMoved(p.mouseX, p.mouseY)) {
                p.fill(125, 135, 255, 100);
                routeList.roundedButton(((p.screenWidth - 100) / 2) + divider, xCor, 40, 40, 20, 20);
            } else {
                routeList.roundedButton(((p.screenWidth - 100) / 2) + divider, xCor, 40, 40, 20, 20);
            }
            if (routeList.mouseMoved(p.mouseX, p.mouseY) && p.mousePressed) {
                routes.setCurrentRoute(routes.getRoute(i));
            }
            //Labels
            p.fill(0);
            p.text("Route", ((p.screenWidth - 100) / 2) + divider, xCor + 7, 40, 40);
            p.text("" + (i + 1), ((p.screenWidth - 100) / 2) + divider, xCor + 22, 40, 40);
            divider += ((p.screenWidth - 300) / 2) / routes.getRoutes().size();
            //Reset colour
            p.fill(125, 135, 197, 100);
            rowCounter++;
        }
    }

    public void rectButSetup(int xPos, int yPos, int w, int h) {
        xs = new int[]{xPos, xPos + w, xPos + w, xPos};
        ys = new int[]{yPos, yPos, yPos + h, yPos + h};
    }
}
