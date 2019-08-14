
$(document).ready(function(){
    $('.verify').click(function (event) {
        // event.stopPropagation();
        // let data = JSON.stringify($("#verfication_table").serializeFormJSON());

        let sid = $(this)[0].dataset.sid;

        let row = $(this).closest("tr");
        $.ajax({
            type: 'POST',
            url: '/admin/verifySeller/'+ sid,
            data: data,
            contentType: 'application/json',
            dataType: 'json',
            success: function(jsonData){
                //append to category list
                let seller = jsonData.responseJSON;

                let statusTd = $(row).find('.td-status');
                $(statusTd).empty();
                $(statusTd).text("UPDATED!!!");

                $(statusTd).text(seller.credentials.verified);

            },
            error: function (xmlResponse) {
                // $('#categoryDetails').empty();
                var responseJson = xmlResponse.responseJSON;

                console.log(responseJson);

            }

        });
    })
});