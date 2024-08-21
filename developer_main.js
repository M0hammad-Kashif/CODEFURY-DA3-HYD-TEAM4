// Sample data for developer (replace this with actual data retrieval)
const developerData = {
  username: "dev123",
  email: "dev@example.com",
  projects: [
    {
      projectName: "Project A",
      manager: "Manager1",
      startDate: "2023-01-01",
      members: [
        { name: "Dev A", role: "Developer" },
        { name: "Tester B", role: "Tester" },
      ],
      bugs: [
        { id: 1, title: "Login issue", status: "Open" },
        { id: 2, title: "UI glitch", status: "Open" },
      ],
    },
  ],
};


// Check if user is assigned to any projects
if (developerData.projects.length > 0) {
  document.getElementById("noProjectMessage").style.display = "none";
  const projectsContainer = document.getElementById("projectsContainer");

  developerData.projects.forEach((project) => {
    const projectDiv = document.createElement("div");
    projectDiv.classList.add("project-item");
    projectDiv.innerHTML = `
            <h4>${project.projectName}</h4>
            <p>Manager: ${project.manager}</p>
            <p>Start Date: ${project.startDate}</p>
            <h5>Members:</h5>
            <ul>${project.members
              .map((member) => `<li>${member.name} (${member.role})</li>`)
              .join("")}</ul>
        `;
    projectsContainer.appendChild(projectDiv);

    // Display bugs and option to mark for closing
    const bugList = document.createElement("ul");
    project.bugs.forEach((bug) => {
      const bugItem = document.createElement("li");
      bugItem.innerHTML = `
                Bug ID: ${bug.id} - ${bug.title}
                <button onclick="markBugClosed(${bug.id})">Mark as Closed</button>
            `;
      bugList.appendChild(bugItem);
    });
    const bugContainer = document.getElementById("bugListContainer");
    bugContainer.appendChild(bugList);
  });
}

// Mark bug as closed
function markBugClosed(bugId) {
  alert(`Bug with ID ${bugId} has been marked as closed!`);
}

// On page load, display user information
window.onload = function() {
    // Retrieve the logged-in user from localStorage
    const loggedInUser = JSON.parse(localStorage.getItem('loggedInUser'));

    // Display the user's name, email, and role dynamically
    if (loggedInUser) {
        document.getElementById('userName').textContent = loggedInUser.name;
        document.getElementById('userEmail').textContent = loggedInUser.email;
        document.getElementById('userRole').textContent = loggedInUser.role;
    } else {
        alert('No user data found. Please log in.');
        window.location.href = 'login_register.html'; // Redirect to login page
    }
};

