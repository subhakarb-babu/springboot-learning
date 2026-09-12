const form = document.getElementById("greetForm");
const message = document.getElementById("message");

form.addEventListener("submit", async (event) => {
    event.preventDefault();

    const name = document.getElementById("name").value;
    const age = Number(document.getElementById("age").value);

    const [greetResponse, thankResponse] = await Promise.all([
        fetch("/api/greet", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ name, age })
        }),
        fetch("/api/thank", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ name })
        })
    ]);

    if (!greetResponse.ok || !thankResponse.ok) {
        throw new Error("One of the API requests failed");
    }

    const [greetData, thankData] = await Promise.all([
        greetResponse.json(),
        thankResponse.json()
    ]);

    message.textContent = greetData.message;
    document.getElementById("endMessage").textContent = thankData.message;
});