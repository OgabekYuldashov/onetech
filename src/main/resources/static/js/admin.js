$(document).ready(function(){
    $('.verify').click(function (event) {
        event.stopPropagation();




        let sid = $(this)[0].dataset.sid;

        console.log($(this)[0].dataset.sid);

        let obj = {
            sellerId: sid,
            quantity: 1
        };

        let data = JSON.stringify(obj);
        $.ajax({
            type: 'POST',
            url: '/admin/verifySeller/sid',
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
    })
});