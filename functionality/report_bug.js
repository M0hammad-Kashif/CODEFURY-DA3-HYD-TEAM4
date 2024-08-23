
// Mock data
const assignedProjects = [
    { id: 1, name: "Project Alpha", status: "in-progress" },
    { id: 2, name: "Project Beta", status: "completed" },
    { id: 3, name: "Project Gamma", status: "in-progress" }
];

// Simulated tester information
const testerName = "tester123"; 
const testerRole = "tester"; 

// Authentication check
window.onload = function() {
    authenticateTester();
};

function authenticateTester() {
    // Simple prompt for tester name (in a real app, this would be more secure)
    const enteredName = prompt("Please enter your name:");
    if (enteredName === testerName && testerRole === "tester") {
        document.getElementById("bugForm").style.display = "block";
        populateProjects();
    } else {
        alert("Access Denied: Only testers can report bugs.");
        document.body.innerHTML = "<h1>Access Denied</h1>";
    }
}

function populateProjects() {
    const projectDropdown = document.getElementById("projectName");
    assignedProjects.forEach(project => {
        if (project.status === "in-progress") {
            const option = document.createElement("option");
            option.value = project.name;
            option.text = project.name;
            projectDropdown.add(option);
        }
    });
}

function submitBug() {
    const projectName = document.getElementById("projectName").value;
    const title = document.getElementById("title").value;
    const description = document.getElementById("description").value;
    const securityLevel = document.getElementById("securityLevel").value;

    if (!projectName || !title || !description || !securityLevel) {
        alert("Please fill in all fields.");
        return;
    }

    const bugId = generateUniqueId();
    const createdBy = testerName;
    const createdOn = new Date().toLocaleString();

    // Mock bug submission 
    console.log("Bug Submitted:", {
        bugId,
        projectName,
        title,
        description,
        securityLevel,
        createdBy,
        createdOn
    });

    document.getElementById("message").innerText = `Bug ${bugId} reported successfully by ${createdBy} on ${createdOn}.`;
    document.getElementById("bugForm").reset();
}

function generateUniqueId() {
    return 'BUG-' + Math.random().toString(36).substr(2, 9).toUpperCase();
}
