(function ($) {
    $.fn.serializeFormJSON = function () {

        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };
})(jQuery);


function updateUserDetails(){
    $.ajax({
        type: 'POST',
        url: '/buyer/cartdetails',
        data: '',
        contentType: 'application/json',
        dataType: 'json',
        success: function(cart){
            //append to category list
            $('#cartItemCount').empty();
            $('#cartItemCount').append(cart.itemCount);
            $('#cartTotalAmount').empty();
            $('#cartTotalAmount').append(cart.totalAmount);
        },
        error: function (xmlResponse) {
            // $('#categoryDetails').empty();
            var responseJson = xmlResponse.responseJSON;

            console.log(responseJson);

            /*if(responseJson.errorType === 'ValidationError'){
                var errorList = responseJson.fieldErrors;

                $.each(errorList, function(index, error){
                    $('#categoryDetails').append('<p>' + error.fieldName + ', ' + error.message + '</p>');
                });

            }else{
                alert("other errors");
            }*/

        }

    });
}

$(document).ready(function(){
    updateUserDetails();

    $('#formAddToCart').submit(function(event){
        event.preventDefault();

        let pr_id = $(this)[0].dataset.pr_id;
        let quantity = $('#quantity_input')[0].value;

        let obj = {
            pid: pr_id,
            quantity: quantity
        };

        let data = JSON.stringify(obj);
        $.ajax({
            type: 'POST',
            url: '/buyer/cart/add',
            data: data,
            contentType: 'application/json',
            dataType: 'json',
            success: function(cart){
                //append to category list
                $('#cartItemCount').empty();
                $('#cartItemCount').append(cart.itemCount);
                $('#cartTotalAmount').empty();
                $('#cartTotalAmount').append(cart.totalAmount);
            },
            error: function (xmlResponse) {
                // $('#categoryDetails').empty();
                var responseJson = xmlResponse.responseJSON;

                console.log(responseJson);

                /*if(responseJson.errorType === 'ValidationError'){
                    var errorList = responseJson.fieldErrors;

                    $.each(errorList, function(index, error){
                        $('#categoryDetails').append('<p>' + error.fieldName + ', ' + error.message + '</p>');
                    });

                }else{
                    alert("other errors");
                }*/

            }

        });

    });

    $('#btnPlaceOrder').click(function(event){

        $.ajax({
            type: 'POST',
            url: '/buyer/place_order',
            data: '',
            contentType: 'application/json',
            dataType: 'json',
            success: function(responseModel){
                console.log("SUCCESS");
                console.log(responseModel);

                let nextUrl = responseModel.nextUrl;
                window.location = nextUrl;
            },
            error: function (xmlResponse) {
                console.log("FAIL");
                console.log(xmlResponse);

                let nextUrl = xmlResponse.nextUrl;
                window.location = nextUrl;
            }

        });

    });

});