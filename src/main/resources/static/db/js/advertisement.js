
$(document).ready(function(){
    $('.approve').click(function (event) {
        // event.stopPropagation();
        // let data = JSON.stringify($("#verfication_table").serializeFormJSON());

        let pid = $(this)[0].dataset.pid;

        let obj = {
            aid: 1,
            quantity: 1
        };


        let row = $(this).closest("tr");
        let data = JSON.stringify(obj);
        $.ajax({
            type: 'POST',
            url: '/admin//acceptPromotion/'+ pid,
            data: data,
            contentType: 'application/json',
            dataType: 'json',
            success: function(product){

                let statusTd1 = $(row).find('.td-status1');
                $(statusTd1).empty();
                if(product){
                    $(statusTd1).text("PROMOTED!!");
                }else{
                    $(statusTd).text("PENDING!!");
                }

            },
            error: function (xmlResponse) {
                // $('#categoryDetails').empty();
                var responseJson = xmlResponse.responseJSON;

                console.log(responseJson);
            }

        });
    })
});
