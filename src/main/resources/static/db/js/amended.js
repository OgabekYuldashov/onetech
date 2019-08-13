$(document).ready(function () {
    $('#rmvBtn').click(function () {
        let removedTtems = [];
        let product = {};
        product.id = 1;
        removedTtems.push(product);
        $.post("/productDelete", {
            products: JSON.stringify(removedTtems)
        }).done(displayMycart).fail(console.error("error"));

    })
    function displayMycart(item) {
            $("#item_"+item.id).remove();

    }

})