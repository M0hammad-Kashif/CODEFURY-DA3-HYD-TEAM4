// Sample data for tester (replace this with actual data retrieval)
const testerData = {
  username: "tester123",
  email: "tester@example.com",
  projects: [
    {
      projectName: "Project A",
      bugs: [
        { id: 1, title: "Login issue", createdBy: "tester123" },
        { id: 2, title: "UI glitch", createdBy: "tester123" },
      ],
    },
    {
      projectName: "Project B",
      bugs: [],
    },
  ],
};


// Check if user is assigned to any projects
if (testerData.projects.length > 0) {
  document.getElementById("noProjectMessage").style.display = "none";
  const projectsContainer = document.getElementById("projectsContainer");

  testerData.projects.forEach((project) => {
    const projectDiv = document.createElement("div");
    projectDiv.classList.add("project-item");
    projectDiv.innerHTML = `<h4>${project.projectName}</h4>`;

    // Display the list of bugs
    const bugList = document.createElement("ul");
    project.bugs.forEach((bug) => {
      if (bug.createdBy === testerData.username) {
        const bugItem = document.createElement("li");
        bugItem.textContent = `Bug ID: ${bug.id} - ${bug.title}`;
        bugList.appendChild(bugItem);
      }
    });

    if (bugList.children.length === 0) {
      bugList.textContent = "No bugs reported by you.";
    }

    projectDiv.appendChild(bugList);
    projectsContainer.appendChild(projectDiv);
  });
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

