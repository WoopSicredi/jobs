import React, { useState } from 'react'
import './login.scss';

function Login() {
  document.title = "Dragons :: Login";
  
  const [username, setUsername] = useState('dragons');
  const [password, setPassword] = useState('dragons');

  function handleSubmit(e) {
    e.preventDefault();
    localStorage.setItem('loggedIn', (username && password === 'dragons'));

    window.location.reload();
  }

  return (
    <div className="login">
      <form className="login-box" onSubmit={ handleSubmit }>
        <h3 className="login-box--title">Login</h3>
        <div className="login-box--group">
          <label htmlFor="username">Usuário:</label>
          <input
            type="text"
            name="username"
            id="username"
            required
            value={username}
            onChange={e => setUsername(e.target.value)}
          />
        </div>
        <div className="login-box--group">
          <label htmlFor="password">Senha:</label>
          <input
            type="password"
            name="password"
            id="password"
            required
            value={password}
            onChange={e => setPassword(e.target.value)}
          />
        </div>
        <div className="login-box--group">
          <button type="submit">Login</button>
        </div>
      </form>
      <div className="login-info">
        <p><strong>Nota:</strong> Usuário e Senha para os testes </p>
        <p><strong>Usuário:</strong> dragons</p>
        <p><strong>Senha:</strong> dragons</p>
      </div>
    </div>
  );
}

export default Login;
