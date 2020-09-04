import * as React from "react";
import { Link } from "react-router-dom";
import { createUseStyles } from "react-jss";

import { Input } from "./Input";

import { updateDragon as serviceUpdateDragon, removeDragon, Dragon } from "../services/dragon";

import { Theme } from "../Theme";

const useStyles = createUseStyles({
    card: {
        border: "1px solid #ffffff",
        borderRadius: "15px",
        boxShadow: "0 0 4px " + Theme.colors.main,
        padding: "12px",
        marginBottom: "30px",
        display: "flex",
        flexDirection: "column",
        "& input, & textarea": {
            display: "block",
            width: "100%",
            padding: "6px",
            border: "none",
            borderBottom: "1px solid #0000007d",
            marginBottom: "11px",
        }
    },
    cardTitle: {
        margin: "0 0 10px"
    },
    cardSubTitle: {
        flexBasis: "60px",
        flexGrow: 1
    },
    cardFooter: {
        display: "flex",
        marginTop: "8px"
    },
    cardFooterBtnGroup: {
        textAlign: "right",
        flexGrow: 1,
    }
});

export function DragonCard(props) {
    const css = useStyles();

    const [isEditMode, setIsEditMode] = React.useState(false);
    const [isDeleted, setIsdeleted] = React.useState(false);

    const [dragonName, setDragonName] = React.useState(props.name);
    const [dragonType, setDragonType] = React.useState(props.type);
    const [dragonHistory, setDragonHistory] = React.useState(props.history);

    const inputName = React.useRef<HTMLInputElement>(null);
    const inputType = React.useRef<HTMLInputElement>(null);
    const inputHistory = React.useRef<HTMLTextAreaElement>(null);


    function updateDragon() {
        setIsEditMode(false);

        setDragonName(inputName.current.value);
        setDragonType(inputType.current.value);
        setDragonHistory(inputHistory.current.value);

        serviceUpdateDragon({
            histories: [],
            name: inputName.current.value,
            type: inputType.current.value,
            history: inputHistory.current.value,
            id: props.id
        })
        .then((response) => {
            alert("Dragão atualizado");
        });
    }

    function deleteDragon() {
        removeDragon(props.id)
        .then((response) => {
            alert("Dragão removido");
            setIsdeleted(true);
        });
    }

    function parseDate(date: string) {
        const d = new Date();
        d.setTime(Date.parse(date));

        return d.toLocaleDateString();
    }

    function renderEdit(){
        return (
            <form>
                <hr/>
                <Input disabled value={props.id} label={"ID"} />
                <Input disabled value={props.createdAt} label={"Data de criação"} />
                <Input label={"Nome"} required ref={inputName} onChange={ (event) => setDragonName(event.target.value)} defaultValue={dragonName} />

                <Input label={"Tipo"} ref={inputType} onChange={ (event) => setDragonType(event.target.value)} defaultValue={dragonType} />
                <Input label={"História"} multiline ref={inputHistory} onChange={ (event) => setDragonHistory(event.target.value)} defaultValue={dragonHistory} />
                <div className={css.cardFooter}>
                    <div className={css.cardFooterBtnGroup}>
                        <button type="button" onClick={updateDragon}>Salvar</button>
                        <button type="button" onClick={() => setIsEditMode(false)}>Cancelar</button>
                    </div>
                </div>
            </form>
        );
    }

    if (isDeleted) {
        return <></>;
    }

    return (
        <div className={`${css.card} ${props.className}`}>
            <h2 className={css.cardTitle}>{dragonName? dragonName : "Sem nome..."}</h2>
            <div className={css.cardSubTitle}>
                Tipo: <span>{dragonType? dragonType : "Sem tipo..."}</span>
            </div>
            <div>
                Data de criação: <span>{parseDate(props.createdAt)}</span>
            </div>
            <div className={css.cardFooter}>
                <Link to={`/detail/${props.id}`}>Detail page</Link>
                <div className={css.cardFooterBtnGroup}>
                    <button onClick={() => setIsEditMode(true)}>Edit</button>
                    <button onClick={ deleteDragon }>Remove</button>
                </div>
            </div>
            {isEditMode? renderEdit() : <></>}
        </div>
    );
}