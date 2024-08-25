package com.hsbc.services;

import com.hsbc.beans.Bug;
import com.hsbc.dao.BugDao;
import com.hsbc.exceptions.BugDoesNotExistException;
import com.hsbc.utilities.BugDaoFactory;

public class BugServiceImpl implements BugService {

    private BugDao bugDao;

    public BugServiceImpl() {
        this.bugDao = BugDaoFactory.getBugDao();
    }

    @Override
    public int createBug(int projectId, String title, String description, String severityLevel, int reportedBy) {
        return bugDao.create(projectId, title, description, severityLevel, reportedBy);
    }

    @Override
    public Bug getBugById(int bugId) throws BugDoesNotExistException {
        return bugDao.findById(bugId);
    }

    @Override
    public Bug getBugByName(String bugName) throws BugDoesNotExistException {
        return bugDao.findByName(bugName);
    }

    @Override
    public void closeBug(int bugId) throws BugDoesNotExistException {
        bugDao.close(bugId);
    }
}
