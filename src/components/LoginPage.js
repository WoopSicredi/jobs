import { useState } from 'react';
import '../css/loginPage.scss';
import { HiMail } from 'react-icons/hi';
import { RiLockPasswordFill } from 'react-icons/ri';


const LoginPage = ({ Login, loginError }) => {
    const [details, setDetails] = useState({ email: "", password: "" })

    const submitHandler = (e) => {
        e.preventDefault()

        Login(details)
    }


    return (
        <form className="col-10 col-md-6 col-lg-4 py-5" id="login-page" onSubmit={submitHandler}>
            <div className="form-inner">
                <h2>Login</h2>
                <div className="form-group">
                    <label htmlFor="email">E-mail</label>
                    <div className="input-group">
                        <input type="email" name="email" id="email"
                            onChange={e => setDetails({ ...details, email: e.target.value })}
                            value={details.email} />
                        <div className="input-icon"><HiMail /></div>
                    </div>
                </div>
                <div className="form-group">
                    <label htmlFor="password">Senha</label>
                    <div className="input-group">

                        <input type="password" name="password" id="password"
                            onChange={e => setDetails({ ...details, password: e.target.value })}
                            value={details.password} />
                        <div className="input-icon"><RiLockPasswordFill /></div>

                    </div>
                </div>
                <p className="error-text">{loginError}</p> 
                <div className="btn-wrapper">
                    <input type="submit" value="ENTRAR" className="btn btn-primary btn-login" />
                    <button className="btn btn-primary btn-signup">CADASTRAR</button>
                </div>
            </div>
        </form>
    )
}

export default LoginPage
