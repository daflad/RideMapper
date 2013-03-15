/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gpsmapper.viscomps.interfaces;

/**
 *
 * @author StephenJohnRussell
 */
public interface ButtonInterface {

    /**
     * Draw Button.
     */
    public void drawMe();
    
    /**
     * Return true if mouse inside button.
     * 
     * @param x
     * @param y
     * @return 
     */
    public boolean mouseMoved(int x, int y);

    /**
     * Return true if mouse pressed.
     * 
     * @param x
     * @param y
     * @return 
     */
    public boolean mousePressed(int x, int y);
    
    /**
     * Return true if double clicked.
     * 
     * @return 
     */
    public boolean doubleClick();

    /**
     * No rounded rectangle in processing 1.5 (Unfolding no work with 2.*)
     * 
     * @param x
     * @param y
     * @param w
     * @param h
     * @param rx
     * @param ry 
     */
    public void roundedButton(float x, float y, float w, float h, float rx, float ry);
    
}
