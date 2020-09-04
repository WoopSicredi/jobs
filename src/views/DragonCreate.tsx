import * as React from "react";
import { createUseStyles } from "react-jss";

import { newDragon } from "../services/dragon";
import { Input } from "../components/Input";

const useStyles = createUseStyles({
    container: {
        marginTop: "10px"
    }
});

export function DragonCreate() {
    const css = useStyles();

    const inputName = React.useRef<HTMLInputElement>(null);
    const inputType = React.useRef<HTMLInputElement>(null);
    const inputHistory = React.useRef<HTMLTextAreaElement>(null);

    function createDragon() {
        newDragon({
            createdAt: new Date().toISOString(),
            histories: [],
            name: inputName.current.value,
            type: inputType.current.value,
            history: inputHistory.current.value,
        })
        .then((response) => {
            alert("Dragão criado");
        });
    }

    return (
        <>
            <form className={css.container}>
                <Input label={"Nome"} ref={inputName} />
                <Input label={"Tipo"} ref={inputType} />
                <Input label={"História"} ref={inputHistory} />
                <button type="button" onClick={() => { createDragon(); }}>
                    Criar
                </button>
            </form>
        </>
    );
}