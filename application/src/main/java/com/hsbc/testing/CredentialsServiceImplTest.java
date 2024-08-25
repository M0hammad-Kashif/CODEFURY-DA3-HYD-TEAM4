package com.hsbc.testing;

import com.hsbc.dao.CredentialDao;
import com.hsbc.exceptions.EmployeeDoesNotExistException;
import com.hsbc.exceptions.IncorrectUsernameOrPasswordException;
import com.hsbc.exceptions.WeakPasswordException;
import com.hsbc.services.CredentialsServiceImpl;
import com.hsbc.utilities.CredentialDaoFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CredentialsServiceImplTest {

    private CredentialsServiceImpl credentialsService;
    private CredentialDao credentialDao;

    @Before
    public void setUp() {
        credentialDao = CredentialDaoFactory.getCredentialDao(); // In-memory DAO
        credentialsService = new CredentialsServiceImpl();
    }

    @Test
    public void testAddCredentials() {
        try {
            credentialsService.addCredentials("username", "StrongPassword123");
        } catch (EmployeeDoesNotExistException | WeakPasswordException e) {
            fail("Exception should not have been thrown");
        }

        boolean verified = false;
        try {
            verified = credentialsService.verifyCredentials("username", "StrongPassword123");
        } catch (IncorrectUsernameOrPasswordException e) {
            fail("Exception should not have been thrown");
        }
        assertTrue(verified);
    }

    @Test
    public void testAddCredentialsThrowsWeakPasswordException() {
        try {
            credentialsService.addCredentials("username", "weak");
            fail("Expected WeakPasswordException to be thrown");
        } catch (WeakPasswordException e) {
            // Test passes if exception is thrown
        } catch (EmployeeDoesNotExistException e) {
            fail("Expected WeakPasswordException, but got EmployeeDoesNotExistException instead");
        }
    }

    @Test
    public void testVerifyCredentials() {
        try {
            credentialsService.addCredentials("username", "password");
        } catch (EmployeeDoesNotExistException | WeakPasswordException e) {
            fail("Exception should not have been thrown");
        }

        boolean result = false;
        try {
            result = credentialsService.verifyCredentials("username", "password");
        } catch (IncorrectUsernameOrPasswordException e) {
            fail("Exception should not have been thrown");
        }

        assertTrue(result);
    }

    @Test
    public void testVerifyCredentialsThrowsException() {
        try {
            credentialsService.verifyCredentials("username", "wrongpassword");
            fail("Expected IncorrectUsernameOrPasswordException to be thrown");
        } catch (IncorrectUsernameOrPasswordException e) {
            // Test passes if exception is thrown
        }
    }
}
