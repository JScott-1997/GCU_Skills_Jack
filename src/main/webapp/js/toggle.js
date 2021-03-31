$(document).ready(function () {
    $('.detailsbutton').on('click', function (event) {
        $inputField = $(this).parent().siblings('td');
        $editConfirm = $(this).siblings('.editconfirm');
        
        //Get servlet path from hidden form field to post data
        $servletPath = $("#servletpath").val();
        
        //Check if field is text area or input field
        if ($inputField.attr('id') == 'tarea') {
            $actualInput = $inputField.children('textarea');
        } else {
            $actualInput = $inputField.children('input');
        }
        $editConfirm.css({visibility: 'hidden'});
        
        //If button has been clicked when toggled to 'edit' change to confirm and make field editable, otherwise (if confirm has been clicked) post field data
        if ($(this).text() === "Edit") {
            $(this).html("Confirm");
            $actualInput.attr('readonly', false);
            
            //Prevents button from completing normal form submit action
            event.preventDefault();
        } else {
            dataString = $("#detailsform").serialize();
            var parameter = $($actualInput).val();
            dataString = "&" + $actualInput.attr('name') + "=" + parameter;

            //Post data to servlet
            $.ajax({
                type: "POST",
                url: $servletPath,
                data: dataString,

                success: function (data) {
                    //Display success message
                    if (data === "Success") {
                        $editConfirm.css({visibility: 'visible'});
                        $editConfirm.css({animation: 'fadeOut ease 2s forwards'});
                        $editConfirm.css({color: 'green'});
                        $editConfirm.html('&check; updated.');
                    }
                    //Display error message
                    else {
                        $editConfirm.css({visibility: 'visible'});
                        $editConfirm.css({color: 'red'});
                        $editConfirm.html('&#x2716; error.');
                    }
                },

                //If there was no resonse from the server
                error: function (textStatus) {
                    $editConfirm.css({visibility: 'visible'});
                    $editConfirm.css({color: 'red'});
                    $editConfirm.html('&#x2716; ' + textStatus + '.');
                }
            });
            
            //Set field back to default readonly state and button back to 'Edit'
            $(this).html("Edit");
            $actualInput.attr('readonly', true);
            $editConfirm.css({animation: 'none'});
            
            
            //Prevents button from completing normal form submit action
            event.preventDefault();
        }
    });
});

