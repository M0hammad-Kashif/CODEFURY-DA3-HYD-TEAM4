package com.hsbc.dao;

import com.hsbc.exceptions.EmployeeDoesNotExistException;
import com.hsbc.exceptions.IncorrectUsernameOrPasswordException;
import com.hsbc.exceptions.WeakPasswordException;

public interface CredentialDao {
    // set credentials on sign-up - username is equivalent to employee id
    public void add(String username, String password) throws EmployeeDoesNotExistException, WeakPasswordException;
    // verify credentials on sign-in
    public boolean verify(String username, String password) throws IncorrectUsernameOrPasswordException;
}
