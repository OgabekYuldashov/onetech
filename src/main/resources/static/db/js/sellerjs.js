$(document).ready(function () {
    $('.item').click(function () {
        let OrderItem = {};

        let value = $(this).val();
        let val1 = value.split("/", 1).pop();
        let val2 = value.split("/", 2).pop();
        console.log(value);
        console.log(val1);
        console.log(val2);
        // OrderItem.id = $(this).val();
        if (val1 === 's') {
            OrderItem.id = val2;
            let newData = JSON.stringify(OrderItem);
            console.log(JSON.stringify(OrderItem));
            $.ajax({
                type: 'post',
                url: '/OrderItemStatusUpdate',
                data: newData,
                datatype: 'JSON',
                contentType: 'application/json',
                success: function (item) {
                    console.log("successs " + item);
                    $('#' + 'd' + item.id).html(item.orderItemStatus)
                },
                error: function () {
                    console.log("error");
                }

            })
        } else {

            OrderItem.id = val2;
            let newData = JSON.stringify(OrderItem);
            console.log(JSON.stringify(OrderItem));
            $.ajax({
                type: 'post',
                url: '/OrderItemStatusCancelled',
                data: newData,
                datatype: 'JSON',
                contentType: 'application/json',
                success: function (item) {
                    console.log("successs " + item);
                    $('#' + 'd' + item.id).html(item.orderItemStatus);
                },
                error: function () {
                    console.log("error");
                }

            })


        }

    });

    $('.item_mark').click(function () {
        let product = {};
        product.id = $(this).val();
        let newData = JSON.stringify(product);
        console.log(newData);
        $.ajax({
            type: 'post',
            url: '/productDelete',
            data: newData,
            datatype: 'JSON',
            contentType: 'application/json',
            success: function (item) {
                console.log("successs" + item.id);
                    if(item.type==='ordered'){
                       $('#msginfo').html(item.type)
                    }else{
                        $('#' + item.id).remove();
                    }



            },
            error: function () {
                console.log("errorrrrr");


            }

        })

    })
});
