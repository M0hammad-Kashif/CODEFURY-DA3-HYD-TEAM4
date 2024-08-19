
# CODEFURY-DA3-HYD-TEAM4
Bug Tracking System developed under CoderFury Competition 2024.


## UML

```mermaid
classDiagram
    class Employee {
        -int employeeId
        -String name
        -String email
        -String role
        +getEmployeeId()
        +getName()
        +getEmail()
        +getRole()
        +setName(String)
        +setEmail(String)
        +setRole(String)
    }

    class Developer {
        -List~Bug~ assignedBugs
        +getAssignedBugs()
        +resolveBug(Bug)
    }

    class Tester {
        -List~Project~ assignedProjects
        +getAssignedProjects()
        +reportBug(Project, Bug)
    }

    class ProjectManager {
        -List~Project~ managedProjects
        +getManagedProjects()
        +createProject(Project)
        +assignTeamMember(Project, Employee)
        +viewProjectBugs(Project)
        +closeBug(Bug)
        +assignBugToDeveloper(Bug, Developer)
    }

    class Project {
        -int projectId
        -String projectName
        -Date startDate
        -String status
        -List~Employee~ teamMembers
        -List~Bug~ bugs
        +getProjectId()
        +getProjectName()
        +getStartDate()
        +getStatus()
        +getTeamMembers()
        +getBugs()
        +addTeamMember(Employee)
        +addBug(Bug)
    }

    class Bug {
        -int bugId
        -String title
        -String description
        -String severityLevel
        -Date createdOn
        -String status
        -Employee reportedBy
        +getBugId()
        +getTitle()
        +getDescription()
        +getSeverityLevel()
        +getCreatedOn()
        +getStatus()
        +getReportedBy()
        +setStatus(String)
    }

    class Credentials {
        -int credentialId
        -String username
        -String password
        +getCredentialId()
        +getUsername()
        +setPassword(String)
        +verifyPassword(String)
    }

    Employee <|-- Developer
    Employee <|-- Tester
    Employee <|-- ProjectManager
    Employee "1" -- "1" Credentials
    ProjectManager "1" -- "*" Project : manages
    Project "*" -- "*" Employee : has team members
    Project "1" -- "*" Bug : contains
    Developer "1" -- "*" Bug : assigned to
    Tester "1" -- "*" Bug : reports
    Tester "*" -- "*" Project : assigned to
```
## Team Members
    1. Dayala Siddartha Naidu (Team Lead)
    2. Mohammad Kashif
    3. Shantanu Dash
    4. Maddirala Sai Srinivas Sujan
    5. Agampreet Kaur
    6. Preeti Poddar