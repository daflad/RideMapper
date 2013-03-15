package org.gpsmapper.core;

/**
 * GPSMapper.java
 *
 */
import codeanticode.glgraphics.GLConstants;
import de.fhpotsdam.unfolding.geo.Location;
import org.gpsmapper.viscomps.Dock;
import org.gpsmapper.viscomps.MasterMap;
import org.gpsmapper.viscomps.RouteDrawer;
import org.gpsmapper.viscomps.ToolBox;
import processing.core.*;

/**
 * Main class to define setup & draw for PApplet.
 * 
 * @author StephenJohnRussell
 * @version 0.1
 */
public class GPSMapper extends PApplet {

    /**
     * Booleans to control active GUI sections.
     */
    public boolean dock = true;
    public boolean drawRoute = false;
    public boolean elevation = false;
    public boolean flagElev = false;
    public boolean toolBox = false;
    public boolean recordRoute = false;
    public boolean map = false;
    
    /**
     * Default font.
     */
    private PFont font;
    
    private MasterMap theMap;
    private Dock theDock;
    private ToolBox theToolBox;
    private RouteList theList;
    private Location home;
    private RouteDrawer routeDrawer;
    
    /**
     * For passing mouse values into toolbox.
     */
    private int tempX = 0;
    private int tempY = 0;

    public static void main(String[] args) {
        // must match the name of your class ie packageName.className 
        PApplet.main(new String[]{"org.gpsmapper.core.GPSMapper"});
    }

    @Override
    public void setup() {
        //Full screen app using OPENGL renderer.
        size(screenWidth, screenHeight, GLConstants.GLGRAPHICS);
        smooth();
        strokeCap(ROUND);
        //A nice font for display
        font = loadFont("data/MyriadApple-Medium-24.vlw");
        textFont(font, 12);
        textAlign(CENTER);
        theDock = new Dock(this);
        home = new Location(53.15f, -4f);
        theMap = new MasterMap(this, home, true);
        theList = new RouteList(this, theMap, drawRoute);
        routeDrawer = new RouteDrawer();
    }

    @Override
    public void draw() {
        smooth();
        theMap.draw();
        booleanControl();
    }

    void booleanControl() {
        if (mousePressed && mouseButton == RIGHT) {
            //Store initial click of mouse so as to avoid toolbox following mouse!
            tempX = mouseX;
            tempY = mouseY;
            toolBox = true;
        }
        if (mouseY > 0 && mouseY < 80) {
            dock = true;
        } else {
            dock = false;
        }
        if (dock) {
            theDock.drawDock(theList);
        }
        if (drawRoute) {
            routeDrawer.drawRoute(this, theMap, theList.getCurrentRoute().getCoords());

        }
        if (elevation) {
            routeDrawer.drawElevation(this, theList.getCurrentRoute().getCoords());
            routeDrawer.drawMarker(this, theMap, drawRoute, theList.getCurrentRoute().getCoords());
        }
        if (toolBox) {
            theToolBox = new ToolBox(tempX, tempY, home, this);
            theToolBox.drawToolBox(theMap);
        }
    }
}