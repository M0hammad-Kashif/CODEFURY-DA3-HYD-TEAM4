package com.hsbc.services;

import com.hsbc.dao.CredentialDao;
import com.hsbc.exceptions.EmployeeDoesNotExistException;
import com.hsbc.exceptions.IncorrectUsernameOrPasswordException;
import com.hsbc.exceptions.WeakPasswordException;
import com.hsbc.utilities.CredentialDaoFactory;

public class CredentialsServiceImpl implements CredentialsService {

    private CredentialDao credentialDao;

    public CredentialsServiceImpl() {
        this.credentialDao = CredentialDaoFactory.getCredentialDao();
    }

    @Override
    public void addCredentials(String username, String password) throws EmployeeDoesNotExistException, WeakPasswordException {
        credentialDao.add(username, password);
    }

    @Override
    public boolean verifyCredentials(String username, String password) throws IncorrectUsernameOrPasswordException {
        return credentialDao.verify(username, password);
    }
}
