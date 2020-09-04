import * as React from "react";
import { render } from "react-dom";
import { HashRouter, Switch, Route } from "react-router-dom";
import { ThemeProvider } from "react-jss";

import { Theme, GlobalCss } from "./Theme";

import { Login } from "./login";

import { Shell } from "./shell";

const container = document.createElement("div");
container.classList.add("app-container");
document.body.appendChild(container);

render(
    <React.StrictMode>
        <HashRouter>
            <ThemeProvider theme={Theme}>
                <GlobalCss/>
                <Switch>
                    <Route exact path="/login">
                        <Login/>
                    </Route>
                    <Route path="/">
                        <Shell></Shell>
                    </Route>
                </Switch>
            </ThemeProvider>
        </HashRouter>
    </React.StrictMode>,
 container);