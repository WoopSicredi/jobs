import * as React from "react";
import { createUseStyles } from "react-jss";


export const Theme = {
    breakpoints: {
        desktop: 960
    },
    colors: {
        main: "#ff8b8b8c",
        mainDark: "#b30000"
    }
}

export const reusableClasses = createUseStyles<typeof Theme>((theme) => {
    return {
        container: {
            marginLeft: "10px",
            marginRight: "10px",
        },
        textRight: {
            textAlign: "right"
        },
        [`@media (min-width: ${theme.breakpoints.desktop}px)`]: {
            container: {
                marginLeft: "auto",
                marginRight: "auto",
                maxWidth: "740px"
            }
        }
    }
});

const useStyles = createUseStyles({
    '@global': {
        body: {
            margin: 0,
            fontFamily: "Verdana, Geneva, Tahoma, sans-serif",
            height: "100vh"
        },
        "*": {
            boxSizing: "border-box"
        },
        "a": {
            color: Theme.colors.mainDark
        }
    },
});


export function GlobalCss() {
    const css = useStyles();
    return (<></>);
}