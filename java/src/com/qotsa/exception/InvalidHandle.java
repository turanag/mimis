/*
 * InvalidHandle.java
 *
 * Created on 9 de Outubro de 2007, 14:18
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.qotsa.exception;

/**
 * Exception to throw when Winamp Handle Fails.
 *
 * @author Francisco
 */
public class InvalidHandle extends Exception{
    
    private static final String defaultMessage = "Invalid Handle. Please Verify if Winamp is running.";
    
     /**
     * Creates a new instance of InvalidHandle
     * @param message Message to print in the stack.
     */
    public InvalidHandle(String message) {
        
        super(message);
        
    }
    
    /**
     * Creates a new instance of InvalidHandle with the default message
     */
    public InvalidHandle() {
        
        super(defaultMessage);
        
    }
    
}
