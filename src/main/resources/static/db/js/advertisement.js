
$(document).ready(function(){
    $('.approve').click(function (event) {
        // event.stopPropagation();
        // let data = JSON.stringify($("#verfication_table").serializeFormJSON());

        let sid = $(this)[0].dataset.aid;

        let obj = {
            aid: 1,
            quantity: 1
        };


        let row = $(this).closest("tr");
        let data = JSON.stringify(obj);
        $.ajax({
            type: 'POST',
            url: '/admin//acceptPromotion/'+ aid,
            data: data,
            contentType: 'application/json',
            dataType: 'json',
            success: function(jsonData){
                //append to category list
                let product = jsonData.responseJSON;

                let statusTd = $(row).find('.td-status1');
                $(statusTd).empty();
                $(statusTd).text("PROMOTE!!!");

                // $(statusTd).text(seller.credentials.verified);
                $(statusTd).text();

            },
            error: function (xmlResponse) {
                // $('#categoryDetails').empty();
                var responseJson = xmlResponse.responseJSON;

                console.log(responseJson);

            }

        });
    })
});
