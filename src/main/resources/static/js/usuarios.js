// Call the dataTables jQuery plugin
$(document).ready(function() {
  cargarUsuarios();
  $('#usuarios').DataTable();
});

async function cargarUsuarios(){
  const request = await fetch('api/usuarios', { // await espera el resultado
    method: 'GET',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
  });
  const usuarios = await request.json();
  console.log(usuarios);

  let listaHtml = '';
  for (let usuario of usuarios){
    let btnEliminar = '<a href="#" onclick="eliminarUsuario('+ usuario.id + ')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>'
    let telefono = usuario.telefono == null ? '-' : usuario.telefono;
    let usuarioHtml = '<tr><td>' + usuario.id + '</td><td>'+ usuario.nombre +' '+ usuario.apellido + '</td><td>'+ usuario.email +'</td><td>'+ telefono +'</td><td>'+ btnEliminar +'</td></tr>';
    listaHtml += usuarioHtml;    
  }
  
  document.querySelector('#usuarios tbody').outerHTML = listaHtml;

}

async function eliminarUsuario(id){
  if(confirm('¿Desea eliminar este usuario?')){ // muestra ventana dialogo SI / NO
    const request = await fetch('api/usuario/' + id, { // await espera el resultado
      method: 'DELETE',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
    });
    location.reload();
  }
  
}