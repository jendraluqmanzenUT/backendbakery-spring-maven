package com.jendra.Zen_Roti.exception;

import java.sql.SQLIntegrityConstraintViolationException;

public class DuplicateEmailException extends SQLIntegrityConstraintViolationException {
    public DuplicateEmailException(String msg){
        super(msg);
    }
}
