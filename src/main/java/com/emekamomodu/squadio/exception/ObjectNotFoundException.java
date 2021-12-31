package com.emekamomodu.squadio.exception;

/**
 * @author CMOMODU
 * @version 1.0
 * @date 12/31/21 12:05 AM
 */
public class ObjectNotFoundException extends RuntimeException{

    private static final long serialVersionUID = -6071562225469243083L;

    public ObjectNotFoundException(String objectName, Object nameOrId) {
        super(objectName + " Object with Name/ID " + nameOrId + " not found");
    }

}
