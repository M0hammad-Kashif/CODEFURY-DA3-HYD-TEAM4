// Mock data: replace with actual data retrieval logic
const assignedProjects = [
    { id: 1, name: "Project A", status: "in-progress" },
    { id: 2, name: "Project B", status: "completed" },
    { id: 3, name: "Project C", status: "in-progress" }
];


window.onload = function() {
    const projectDropdown = document.getElementById("projectName");
    assignedProjects.forEach(project => {
        if (project.status === "in-progress") {
            const option = document.createElement("option");
            option.value = project.name;
            option.text = project.name;
            projectDropdown.add(option);
        }
    });
};

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
    const createdBy = "tester123"; 
    const createdOn = new Date().toLocaleString();

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
