package com.hsbc.services;

import com.hsbc.exceptions.EmployeeDoesNotExistException;
import com.hsbc.exceptions.IncorrectUsernameOrPasswordException;
import com.hsbc.exceptions.WeakPasswordException;

public interface CredentialsService {
    public void addCredentials(String username, String password) throws EmployeeDoesNotExistException, WeakPasswordException;
    public boolean verifyCredentials(String username, String password) throws IncorrectUsernameOrPasswordException;
}
