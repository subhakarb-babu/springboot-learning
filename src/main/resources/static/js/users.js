const getUserButton = document.getElementById("getUserBtn");
const createUserButton = document.getElementById("createUserBtn");
const userMessage = document.getElementById("userMessage");

// GET USER
getUserButton.addEventListener("click", async function () {

const name = document.getElementById("name").value;

try {

    const response = await fetch(`/api/users/${name}`);

    if (!response.ok) {
        throw new Error(`Request failed: ${response.status}`);
    }

    const data = await response.text();

    userMessage.textContent = data;

} catch (error) {

    console.error(error);

    userMessage.textContent = "Failed to get user.";
}


});

// CREATE USER
createUserButton.addEventListener("click", async function () {

const name = document.getElementById("name").value;
const age = Number(document.getElementById("age").value);
const email = document.getElementById("email").value;

const requestBody = {
    name: name,
    email: email,
    age: age
};

try {

    const response = await fetch("/api/users", {

        method: "POST",

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify(requestBody)
    });

    if (!response.ok) {
        throw new Error(`Request failed: ${response.status}`);
    }

    const data = await response.json();

    userMessage.textContent = data.message;

} catch (error) {

    console.error(error);

    userMessage.textContent = "Failed to create user.";
}

});
