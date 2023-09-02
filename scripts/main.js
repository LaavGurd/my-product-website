document.addEventListener("DOMContentLoaded", function () {
    const productContainers = document.querySelectorAll(".product");
    const completeOrderBtn = document.getElementById("complete-order-btn");

    let selectedProducts = [];

    productContainers.forEach((product) => {
        const minusButton = product.querySelector(".minus-btn");
        const plusButton = product.querySelector(".plus-btn");
        const quantityElement = product.querySelector(".quantity");

        let quantity = 0;

        minusButton.addEventListener("click", () => {
            if (quantity > 0) {
                quantity--;
                updateQuantity();
            }
        });

        plusButton.addEventListener("click", () => {
            quantity++;
            updateQuantity();
        });

        function updateQuantity() {
            const button = event.target;
            const productContainer = button.closest(".product");
            const productName = productContainer.querySelector("img").alt;
            quantityElement.textContent = quantity;
            const existingProduct = selectedProducts.find(prod => prod.name === productName);
            if (existingProduct) {
                existingProduct.quantity = quantity;
            } else {
                selectedProducts.push({ name: productName, quantity: quantity });
            }
        }
    });

    completeOrderBtn.addEventListener("click", () => {
        const orderData = selectedProducts.map((product) => ({
            name: product.name,
            quantity: product.quantity,
            timestamp: new Date().toISOString()
        }));

        const jsonData = JSON.stringify(orderData);

         //initialize the "save" button
           const mainButton = window.Telegram.WebApp.MainButton;
           mainButton.text = "Save Preferences";
           mainButton.enable();
           mainButton.show();
           // and make it send the "foods" object (as JSON string) back to the backend
           mainButton.onClick(function(){
             window.Telegram.WebApp.sendData(jsonData);
           })

//        // Send the JSON data to the backend using the fetch API
//        fetch("http://127.0.0.1:8080/process", {
//            method: "POST",
//            headers: {
//                "Content-Type": "application/json"
//            },
//            body: jsonData
//        })
//        .then(response => response.json())
//        .then(data => {
//            // Handle the response from the backend if needed
//        })
//        .catch(error => {
//            console.error("Error sending order data:", error);
//        });
    });
});