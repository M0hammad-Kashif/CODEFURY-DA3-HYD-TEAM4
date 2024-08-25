package com.hsbc.services;

import com.hsbc.beans.Bug;
import com.hsbc.dao.BugDao;
import com.hsbc.exceptions.BugAlreadyClosedException;
import com.hsbc.exceptions.BugDoesNotExistException;
import com.hsbc.exceptions.EmployeeDoesNotExistException;
import com.hsbc.utilities.BugDaoFactory;

import java.util.List;

public class DeveloperServiceImpl implements DeveloperService {

    private BugDao bugDao;

    public DeveloperServiceImpl() {
        this.bugDao = BugDaoFactory.getBugDao();
    }

//    @Override
//    public List<Bug> getAssignedBugs(int developerId) throws EmployeeDoesNotExistException {
//        // Retrieve bugs assigned to a developer
//        return bugDao.findById(developerId);
//    }

    @Override
    public void resolveBug(int bugId) throws BugDoesNotExistException, BugAlreadyClosedException {
        bugDao.resolve(bugId);
    }
}
