function QuitarCarrito(Codigo)
{
    var f = document.formulario;
    if (confirm('¿Desea eliminar este registro?')) {
        f.Codigo.value = Codigo;
        f.accion.value = "QuitarCarrito";
        f.submit();
    }
    else {
        return;
    }


}