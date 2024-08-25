package com.hsbc.testing;

import com.hsbc.beans.Bug;
import com.hsbc.dao.BugDao;
import com.hsbc.exceptions.BugDoesNotExistException;
import com.hsbc.services.BugServiceImpl;
import com.hsbc.utilities.BugDaoFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BugServiceImplTest {

    private BugServiceImpl bugService;
    private BugDao bugDao;

    @Before
    public void setUp() {
        bugDao = BugDaoFactory.getBugDao(); // Assuming this provides an in-memory DAO
        bugService = new BugServiceImpl();
    }

    @Test
    public void testCreateBug() {
        int bugId = bugService.createBug(1, "Bug Title", "Bug Description", "High", 101);

        Bug bug = null;
        try {
            bug = bugService.getBugById(bugId);
        } catch (BugDoesNotExistException e) {
            fail("Bug should exist but was not found.");
        }
        assertNotNull(bug);
        assertEquals("Bug Title", bug.getTitle());
        assertEquals("Bug Description", bug.getDescription());
    }

    @Test
    public void testGetBugById() {
        int bugId = bugService.createBug(1, "Bug Title", "Bug Description", "High", 101);

        Bug result = null;
        try {
            result = bugService.getBugById(bugId);
        } catch (BugDoesNotExistException e) {
            fail("Bug should exist but was not found.");
        }

        assertNotNull(result);
        assertEquals("Bug Title", result.getTitle());
    }

    @Test
    public void testGetBugByIdThrowsException() {
        try {
            bugService.getBugById(999); // Assuming 999 is an ID that doesn't exist
            fail("Expected BugDoesNotExistException to be thrown");
        } catch (BugDoesNotExistException e) {
            // Test passes if exception is thrown
        }
    }

    @Test
    public void testGetBugByName() {
        bugService.createBug(1, "Bug Title", "Bug Description", "High", 101);

        Bug result = null;
        try {
            result = bugService.getBugByName("Bug Title");
        } catch (BugDoesNotExistException e) {
            fail("Bug should exist but was not found.");
        }

        assertNotNull(result);
        assertEquals("Bug Description", result.getDescription());
    }

    @Test
    public void testGetBugByNameThrowsException() {
        try {
            bugService.getBugByName("Non-Existent Bug");
            fail("Expected BugDoesNotExistException to be thrown");
        } catch (BugDoesNotExistException e) {
            // Test passes if exception is thrown
        }
    }

    @Test
    public void testCloseBug() {
        int bugId = bugService.createBug(1, "Bug Title", "Bug Description", "High", 101);

        try {
            bugService.closeBug(bugId);
        } catch (BugDoesNotExistException e) {
            fail("Bug should exist but was not found.");
        }

        Bug closedBug = null;
        try {
            closedBug = bugService.getBugById(bugId);
        } catch (BugDoesNotExistException e) {
            fail("Bug should exist but was not found.");
        }

        assertNotNull(closedBug);
        assertEquals("Closed", closedBug.getStatus());
    }
}
