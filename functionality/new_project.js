document.addEventListener('DOMContentLoaded', () => {
    const accessCheckDiv = document.getElementById('accessCheck');
    const projectManagerName = 'John Doe'; // Name of the Project Manager

    // Create an input field to ask for the manager's name
    const nameInputLabel = document.createElement('label');
    nameInputLabel.textContent = 'Enter your name to access the project creation page:';
    const nameInput = document.createElement('input');
    nameInput.type = 'text';
    nameInput.id = 'managerNameInput';
    
    const submitNameButton = document.createElement('button');
    submitNameButton.textContent = 'Submit';

    accessCheckDiv.appendChild(nameInputLabel);
    accessCheckDiv.appendChild(nameInput);
    accessCheckDiv.appendChild(submitNameButton);

    submitNameButton.addEventListener('click', () => {
        const enteredName = nameInput.value.trim();
        
        if (enteredName === projectManagerName) {
            accessCheckDiv.style.display = 'none';
            document.getElementById('formContainer').style.display = 'block';
        } else {
            accessCheckDiv.innerHTML = '<h2>Access Denied</h2><p>You are not authorized to view this page.</p>';
        }
    });

    const form = document.getElementById('projectForm');
    const startDateInput = document.getElementById('startDate');
    const today = new Date();
    const minStartDate = new Date(today.getTime() + (2 * 24 * 60 * 60 * 1000));
    
    // Set minimum start date to 2 days from today
    startDateInput.setAttribute('min', minStartDate.toISOString().split('T')[0]);

    form.addEventListener('submit', (event) => {
        event.preventDefault();
        
        // Get form values
        const projectName = document.getElementById('projectName').value;
        const startDate = document.getElementById('startDate').value;
        const teamMembers = Array.from(document.querySelectorAll('#teamMembers input'))
            .map(input => ({ name: input.name, role: input.value }));
        
        // Validate and create project
        if (validateProject(projectName, startDate, teamMembers)) {
            // Simulate unique ID generation and project creation
            const uniqueId = `P${Date.now()}`;
            console.log(`Project Created: ID: ${uniqueId}, Name: ${projectName}, Start Date: ${startDate}, Team Members: ${JSON.stringify(teamMembers)}`);
            alert(`Project Created Successfully with ID: ${uniqueId}`);
        }
    });
    
    function validateProject(name, startDate, members) {
        // Add the validation logic here
        // For example, checking assignment limits and other rules
        return true;
    }
    
    const teamMembersDiv = document.getElementById('teamMembers');
    const roles = ['Developer', 'Tester'];
    roles.forEach(role => {
        const label = document.createElement('label');
        label.textContent = `${role}:`;
        const input = document.createElement('input');
        input.type = 'text';
        input.name = role.toLowerCase();
        label.appendChild(input);
        teamMembersDiv.appendChild(label);
    });
});
