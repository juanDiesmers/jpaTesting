function toggleForms() {
    const loginForm = document.querySelector('.form-wrapper:not(.register-form)');
    const registerForm = document.querySelector('.register-form');
  
    if (loginForm.style.display === 'none') {
      loginForm.style.display = 'block';
      registerForm.style.display = 'none';
    } else {
      loginForm.style.display = 'none';
      registerForm.style.display = 'block';
    }
  }


  function registrarArrendador() {
    const nombre = document.getElementById('new-firstname').value;
    const apellido = document.getElementById('new-lastname').value;
    const correo = document.getElementById('new-email').value;
    const telefono = document.getElementById('new-phone').value;
    const contrasena = document.getElementById('new-password').value;
    const tipoUsuario = document.querySelector('input[name="user-type"]:checked').value;
  
    const arrendadorData = {
      nombre: nombre,
      apellido: apellido,
      correo: correo,
      telefono: telefono,
      contrasena: contrasena,
      tipoUsuario: tipoUsuario
    };
  
    fetch('/submit', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(arrendadorData),
    })
    .then(response => response.json())
    .then(data => {
      console.log('Arrendador agregado:', data);
      window.location.href = "Login";
    })
    .catch(error => {
      console.error('Error al agregar arrendador:', error);
    });
  }