package com.hsbc.utilities;

import com.hsbc.dao.CredentialDao;
import com.hsbc.dao.CredentialDaoImpl;

public class CredentialDaoFactory {
    public CredentialDao getCredentialDao(){
        CredentialDao credentialDao = new CredentialDaoImpl();
        return credentialDao;
    }
}
