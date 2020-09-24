import * as React from "react";
import { useHistory } from "react-router-dom";
import {createUseStyles} from "react-jss";

import { login } from "./services/login";
import { Input } from "./components/Input";
import { reusableClasses } from "./Theme";

const useStyles = createUseStyles({
    container: {
        display: "flex",
        flexDirection: "column",
        justifyContent: "center",
        height: "100vh",
    }
});

export function Login() {
    const history = useHistory();
    const [isLoading, setIsLoading] = React.useState(false);
    const reusableCss = reusableClasses();
    const css = useStyles();

    function handleLogin() {
        setIsLoading(true);
        login("fake", "fake")
        .then(() => {
            history.push("/");
        })
        .catch((err) => {
            console.error(err);
        })
    }

    return (
        <div className={`${css.container} ${reusableCss.container}`}>
            <form>
                <Input label="Login" placeholder="email or username" />
                <Input label="Password" type="password" />
                <div className={reusableCss.textRight}>
                    <button type="button" onClick={handleLogin} disabled={isLoading}>Entrar</button>
                </div>
            </form>
        </div>
    );
}