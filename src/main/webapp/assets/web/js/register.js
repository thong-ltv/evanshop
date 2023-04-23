const passwordInput = document.getElementById('password');
const confirmPasswordInput = document.getElementById('confirmPassword');

function validatePassword() {
  if (passwordInput.value !== confirmPasswordInput.value) {
    confirmPasswordInput.setCustomValidity("Passwords don't match");
  } else {
    confirmPasswordInput.setCustomValidity('');
  }
}

passwordInput.addEventListener('change', validatePassword);
confirmPasswordInput.addEventListener('keyup', validatePassword);
