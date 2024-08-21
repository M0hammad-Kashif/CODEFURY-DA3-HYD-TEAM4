// Simulate the user database with a JSON structure
let userDatabase = [];

// Show Login Form and Hide Registration Form
function showLogin() {
  document.getElementById("loginBox").style.display = "block";
  document.getElementById("registerBox").style.display = "none";
}

// Show Registration Form and Hide Login Form
function showRegister() {
  document.getElementById("loginBox").style.display = "none";
  document.getElementById("registerBox").style.display = "block";
}

// Initialize the page with the login form visible
window.onload = function () {
  showLogin();
};

// Handle Registration Form Submission
document
  .getElementById("registrationForm")
  .addEventListener("submit", function (event) {
    event.preventDefault();

    const name = document.getElementById("name").value;
    const email = document.getElementById("email").value;
    const role = document.getElementById("role").value;
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    // Simple validation
    if (password.length < 6) {
      alert("Password should be at least 6 characters long.");
      return;
    }

    const newUser = {
      name,
      email,
      role,
      username,
      password,
    };

    // Add new user to the userDatabase
    userDatabase.push(newUser);
    alert("Registration successful! You can now log in.");
    showLogin();
  });

// Handle Login Form Submission
document
  .getElementById("loginForm")
  .addEventListener("submit", function (event) {
    event.preventDefault();

    const username = document.getElementById("loginUsername").value;
    const password = document.getElementById("loginPassword").value;

    // Check if the user exists in the userDatabase
    const user = userDatabase.find(
      (u) => u.username === username && u.password === password
    );

    if (user) {
      alert(`Welcome back, ${user.name}! Redirecting to your dashboard...`);

      // Store user data in localStorage
      localStorage.setItem("loggedInUser", JSON.stringify(user));

      // Redirect based on the user's role
      switch (user.role) {
        case "Project Manager":
          window.location.href = "project_manager_main.html"; // Redirect to Project Manager main page
          break;
        case "Developer":
          window.location.href = "developer_main.html"; // Redirect to Developer main page
          break;
        case "Tester":
          window.location.href = "tester_main.html"; // Redirect to Tester main page
          break;
        default:
          alert("Invalid role.");
      }
    } else {
      alert("Invalid login credentials. Please try again.");
    }
  });
