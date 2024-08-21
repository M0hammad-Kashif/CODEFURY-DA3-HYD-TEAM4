// Sample data for project details
const projectDetails = {
  id: 1,
  name: "Project Alpha",
  startDate: "2024-07-01",
  projectManager: "John Doe",
  teamMembers: [
    { name: "Alice", role: "Developer" },
    { name: "Bob", role: "Tester" },
    { name: "Charlie", role: "Developer" },
  ],
  bugs: [
    {
      id: 101,
      description: "UI not responsive",
      status: "Open",
      assignedTo: "Alice",
    },
    {
      id: 102,
      description: "Login issue",
      status: "In Progress",
      assignedTo: "Bob",
    },
    {
      id: 103,
      description: "Data not saved",
      status: "Closed",
      assignedTo: "Charlie",
    },
  ],
};

// Function to display project details
function displayProjectDetails() {
    document.getElementById('projectName').textContent = projectDetails.name;
    document.getElementById('startDate').textContent = projectDetails.startDate;
    document.getElementById('projectManager').textContent = projectDetails.projectManager;

    const teamList = document.getElementById('teamList');
    projectDetails.teamMembers.forEach(member => {
        const card = document.createElement('div');
        card.className = 'team-card';

        // Placeholder image for team members
        const img = document.createElement('img');
        img.src = 'https://via.placeholder.com/80';
        img.alt = `${member.name}`;

        const name = document.createElement('h3');
        name.textContent = member.name;

        const role = document.createElement('p');
        role.textContent = member.role;

        card.appendChild(img);
        card.appendChild(name);
        card.appendChild(role);

        teamList.appendChild(card);
    });
}


// Function to populate the bugs table
function populateBugsTable() {
  const bugTableBody = document.querySelector("#bugTable tbody");

  // Clear the existing rows to avoid duplication
  bugTableBody.innerHTML = "";

  projectDetails.bugs.forEach((bug) => {
    const row = document.createElement("tr");

    const idCell = document.createElement("td");
    idCell.textContent = bug.id;

    const descCell = document.createElement("td");
    descCell.textContent = bug.description;

    const statusCell = document.createElement("td");
    statusCell.textContent = bug.status;

    const assignedToCell = document.createElement("td");
    assignedToCell.textContent = bug.assignedTo;

    const actionsCell = document.createElement("td");
    actionsCell.innerHTML = `
            <button onclick="assignBug(${bug.id})">Assign</button>
            <button onclick="closeBug(${bug.id})">Close</button>
        `;

    row.appendChild(idCell);
    row.appendChild(descCell);
    row.appendChild(statusCell);
    row.appendChild(assignedToCell);
    row.appendChild(actionsCell);

    bugTableBody.appendChild(row);
  });
}

// Function to sort the bugs table
function sortTable(columnIndex) {
  const table = document.getElementById("bugTable");
  let rows,
    switching,
    i,
    x,
    y,
    shouldSwitch,
    dir,
    switchCount = 0;
  switching = true;
  dir = "asc";

  while (switching) {
    switching = false;
    rows = table.rows;
    for (i = 1; i < rows.length - 1; i++) {
      shouldSwitch = false;
      x = rows[i].getElementsByTagName("TD")[columnIndex];
      y = rows[i + 1].getElementsByTagName("TD")[columnIndex];
      if (dir === "asc") {
        if (x.textContent.toLowerCase() > y.textContent.toLowerCase()) {
          shouldSwitch = true;
          break;
        }
      } else if (dir === "desc") {
        if (x.textContent.toLowerCase() < y.textContent.toLowerCase()) {
          shouldSwitch = true;
          break;
        }
      }
    }
    if (shouldSwitch) {
      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
      switching = true;
      switchCount++;
    } else {
      if (switchCount === 0 && dir === "asc") {
        dir = "desc";
        switching = true;
      }
    }
  }
}

// Function to filter bugs by search input
function filterBugs() {
  const input = document.getElementById("searchInput");
  const filter = input.value.toLowerCase();
  const table = document.getElementById("bugTable");
  const rows = table.getElementsByTagName("tr");

  for (let i = 1; i < rows.length; i++) {
    const cells = rows[i].getElementsByTagName("td");
    let rowContainsFilter = false;
    for (let j = 0; j < cells.length; j++) {
      if (cells[j].textContent.toLowerCase().indexOf(filter) > -1) {
        rowContainsFilter = true;
        break;
      }
    }
    rows[i].style.display = rowContainsFilter ? "" : "none";
  }
}

// Function to assign a bug to a developer
function assignBug(bugId) {
  const developer = prompt("Enter the developer's name to assign this bug:");
  if (developer) {
    const bug = projectDetails.bugs.find((b) => b.id === bugId);
    bug.assignedTo = developer;
    populateBugsTable();
  }
}

// Function to close a bug
function closeBug(bugId) {
  const bug = projectDetails.bugs.find((b) => b.id === bugId);
  bug.status = "Closed";
  populateBugsTable();
}

// Call the functions to display the project details and populate the bugs table on page load
window.onload = function () {
  displayProjectDetails();
  populateBugsTable();
};
