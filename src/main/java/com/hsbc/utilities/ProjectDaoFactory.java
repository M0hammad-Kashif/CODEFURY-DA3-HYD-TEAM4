package com.hsbc.utilities;

import com.hsbc.dao.ProjectDao;
import com.hsbc.dao.ProjectDaoImpl;

public class ProjectDaoFactory {
    public ProjectDao getProjectDao(){
        ProjectDao projectDao = new ProjectDaoImpl();
        return projectDao;
    }
}
