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
            var responseJson = xmlResponse.responseJSON;

            console.log(responseJson);

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
                $('#cartItemCount').empty();
                $('#cartItemCount').append(cart.itemCount);
                $('#cartTotalAmount').empty();
                $('#cartTotalAmount').append(cart.totalAmount);
            },
            error: function (xmlResponse) {
                var responseJson = xmlResponse.responseJSON;

                $(responseJson.fieldErrors).each(function (err, index) {
                   showNotification("danger", err.fieldName + ' ' + 'must not be blank');
                });
                console.log(responseJson);
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

    $('#btnPostReview').click(function(event){
        event.preventDefault();

        let pid = $(this)[0].dataset.pid;
        let title = $('#inputReviewTitle').val();
        let msg = $('#inputReviewMessage').val();

        let reviewObj = {
            title: title,
            message: msg
        };
        let data = JSON.stringify(reviewObj);

        $.ajax({
            type: 'POST',
            url: '/buyer/addreview/' + pid,
            data: data,
            contentType: 'application/json',
            dataType: 'json',
            success: function(response){
                console.log("SUCCESS");
                if(response.respStatus === 'SUCCESS'){
                    showNotification('success', response.message)
                }else {
                    showNotification('danger', response.message)
                }

            },
            error: function (xmlResponse) {
                console.log("FAIL");
                console.log(xmlResponse);
                showNotification('danger', "Could not Post the Review")
            }

        });

    });

});


function showNotification(theme, message) {
    switch (theme) {
        case "success":
            $.jnoty(message, {
                header: 'Success',
                sticky: false,
                theme: 'jnoty-' + theme,
                icon: 'fa fa-check-circle'
            });
            break;
        case "warning":
            $.jnoty(message, {
                sticky: false,
                header: 'Warning',
                theme: 'jnoty-' + theme,
                icon: 'fa fa-info-circle'
            });
            break;
        case "info":
            $.jnoty(message, {
                sticky: false,
                header: 'Information',
                theme: 'jnoty-' + theme,
                icon: 'fa fa-info-circle'
            });
            break;
        case "danger":
            $.jnoty(message, {
                sticky: false,
                header: 'Danger',
                theme: 'jnoty-' + theme,
                icon: 'fa fa-info-circle'
            });
            break;
        default:
            $.jnoty(message, {
                sticky: false,
                header: 'Information',
                theme: 'jnoty-' + theme,
                icon: 'fa fa-info-circle'
            });
    }
}

($.jnoty("Notification Message Here.", {
    closer: true,
    life: 3000 // 3 seconds

}))();