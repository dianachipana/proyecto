$.validator.setDefaults({
    submitHandler: function() {
        submit();
    }
});
$(function() {
    $("#formulario").validate({
        submitHandler: function(form){
            if(!this.wasSent){
                this.wasSent = true;
                $(':submit', form).val('Procesando...')
                    .attr('disabled', 'disabled')
                    .addClass('disabled');
                form.submit();
            } else {
                return false;
            }
        },
        rules: {
            time: {
                required: true,
                max: 23
            },
            agree: "required"
        },
        messages: {
            radio: "*",
            firstname: "Please enter your firstname",
            lastname: "Please enter your lastname",
            dni: {
                required: "Ingrese un DNI",
                minlength: "Deben ser 8 n�meros"
            }
        }
    });
});

function acceptNum(evt)
{	
    if(window.event){
    // IE
        keynum = evt.keyCode;
    }
    else
    {
        keynum = evt.which;
    }
    if((keynum>47 && keynum<58) || keynum==8 || keynum==0){
        return true;
    }
    else
    {
        return false;
    }
    var key = nav4 ? evt.which : evt.keyCode;	
    return (key == 0 || key == 8 || key <= 13 || (key >= 48 && key <= 57));
}
function acceptNumDecimal(evt)
{	
    if(window.event){
    // IE
        keynum = evt.keyCode;
    }
    else
    {
        keynum = evt.which;
    }
    if((keynum>47 && keynum<58) || keynum==46 || keynum==8 || keynum==0){
        return true;
    }
    else
    {
        return false;
    }
    var key = nav4 ? evt.which : evt.keyCode;	
    return (key == 0 || key==46 || key == 8 || key <= 13 || (key >= 48 && key <= 57));
}