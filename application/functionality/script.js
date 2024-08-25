// Sample JSON data for projects
const projects = [
  {
    id: 1,
    name: "Project Alpha",
    status: "In Progress",
    lastUpdated: "2024-08-18",
  },
  {
    id: 2,
    name: "Project Beta",
    status: "Completed",
    lastUpdated: "2024-08-15",
  },
  {
    id: 3,
    name: "Project Gamma",
    status: "Not Started",
    lastUpdated: "2024-08-10",
  },
];

// Function to populate the project table
function populateProjectTable() {
  const projectTableBody = document.querySelector("#projectTable tbody");

  projects.forEach((project) => {
    const row = document.createElement("tr");

    const nameCell = document.createElement("td");
    const nameLink = document.createElement("a");
    nameLink.href = `project_details.html?id=${project.id}`;
    nameLink.textContent = project.name;
    nameCell.appendChild(nameLink);

    const statusCell = document.createElement("td");
    statusCell.textContent = project.status;

    const lastUpdatedCell = document.createElement("td");
    lastUpdatedCell.textContent = project.lastUpdated;

    row.appendChild(nameCell);
    row.appendChild(statusCell);
    row.appendChild(lastUpdatedCell);

    projectTableBody.appendChild(row);
  });
}



// On page load, display user information
window.onload = function() {
  populateProjectTable();
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
