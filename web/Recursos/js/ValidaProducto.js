function ModificaProducto(Codigo)
{
    var f = document.formulario;
    f.id.value = Codigo;
    f.action = "actualizarProducto.jsp";
    f.submit();
}

function AddCarrito(Codigo)
{
    var f = document.formulario;
    f.id.value = Codigo;
    f.action = "agregarCarrito.jsp";
    f.submit();
}