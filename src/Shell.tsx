import * as React from "react";
import { Switch, Route, Link, Redirect } from "react-router-dom";
import { createUseStyles } from "react-jss";

import { reusableClasses } from "./Theme";

import { isUserAuthenticated } from  "./services/login";

import { DragonList } from "./views/DragonList";
import { DragonDetail } from "./views/DragonDetail";
import { DragonCreate } from "./views/DragonCreate";

const useStyles = createUseStyles({
    title: {
        fontSize: "1.4rem"
    },
    asideMenu: {
        position: "fixed",
        left: 0,
        top: "10%",
        "& a": {
            display: "block"
        }
    },
    header: {
        boxShadow: "0px 2px 3px 0px #0000003d"
    },
    headerContent: {
        display: "flex",
        justifyContent: "space-between",
        alignItems: "baseline",
    },
    main: {
        overflow: "auto"
    },
    NavMenuContainer: {
        position: "relative"
    },
    NavMenu: {
        position: "absolute",
        top: "20px",
        left: "-160px",
        right: 0,
        backgroundColor: "#ffffff",
        boxShadow: "0 0 3px #00000030",
    },
    NavMenuList: {
        listStyle: "none",
        padding: "10px",
        "& li": {
            transition: "background-color .5s",
            textAlign: "center",
            padding: "5px",
            fontSize: "1.3rem",
            "&:hover": {
                backgroundColor: "#f1f1f1"
            }
        },
    },
    Hide: {
        display: "none"
    },
    "@global": {
        ".app-container": {
            height: "100%",
            display: "flex",
            flexDirection: "column",
            "& main": {
                flexGrow: 1
            }
        }
    }
});

export function Shell() {
    const css = useStyles();
    const reusableCss = reusableClasses();

    const [isMenuOpen, setIsMenuOpen] = React.useState(false);

    if (!isUserAuthenticated()) {
        return <Redirect to="/login"></Redirect>
    }

    return (
        <>
            <header className={css.header}>
                <div className={`${css.headerContent} ${reusableCss.container}`}>
                    <h1 className={css.title}>
                        Teste compasso
                    </h1>
                    <div className={css.NavMenuContainer}>
                        <button onClick={() => setIsMenuOpen(!isMenuOpen)}>
                            Menu
                        </button>
                        <nav className={`${css.NavMenu} ${isMenuOpen? "" : css.Hide}`}>
                            <ul className={css.NavMenuList}>
                                <li><Link to="/login">Sair</Link></li>
                                <li><Link to="/">Listagem</Link></li>
                                <li><Link to="/create">Criar novo</Link></li>
                            </ul>
                        </nav>
                    </div>
                </div>
            </header>
            <main className={css.main}>
                <div className={reusableCss.container}>
                    <Switch>
                        <Route exact path="/">
                            <DragonList />
                        </Route>
                        <Route exact path="/create">
                            <DragonCreate />
                        </Route>
                        <Route exact path="/detail/:id">
                            <DragonDetail />
                        </Route>
                    </Switch>
                </div>
            </main>
            <footer>
                <div className={reusableCss.container}>
                    <hr/>
                    Made by: Jacksom Soares (jacksom.es.94@gmail.com)
                </div>
            </footer>
        </>
    );
}