function VerificaLogeo()
{
    var f = document.formulario;
    if (f.nombre.value === "")
    {
        alert("Ingrese un Usuario válido"); 
        f.nombre.focus();
        return;
    }
    if (f.clave.value === "")
    {
        alert("Ingrese una Contraseña"); return;
        f.clave.focus();
    }
    f.submit();
}