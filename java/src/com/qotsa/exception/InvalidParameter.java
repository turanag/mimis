/*
 * InvalidParameter.java
 *
 * Created on 11 de Outubro de 2007, 10:53
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.qotsa.exception;

/**
 * Exception to throw when any parameter is invalid.
 *
 * @author Francisco
 */
public class InvalidParameter extends Exception {
    
    private static final String defaultMessage = "Invalid Parameter";
    
    /**
     * Creates a new instance of NegativeValueException
     * @param message Message to print in the stack.
     */
    public InvalidParameter(String message) {
        
        super(message);
        
    }
    
    /**
     * Creates a new instance of NegativeValueException with the default message
     */
    public InvalidParameter() {
        
        super(defaultMessage);
        
    }
    
}
