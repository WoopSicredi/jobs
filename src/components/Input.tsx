import * as React from "react";
import { createUseStyles } from "react-jss";

const useStyles = createUseStyles({
    label: {
        display: "block",
        boxShadow: "0 0 3px #0000005c",
        borderRadius: "4px",
        padding: "10px",
        marginBottom: "10px"
    },
    input: {
        width: "100%",
        border: "none",
        padding: "10px 10px",
        fontSize: ".9rem",
    }
});

export const Input = React.forwardRef<any, any>((props, ref) => {
    const css = useStyles();

    return (
        <label className={css.label}>
            {props.label}
            {props.multiline?
                <textarea className={css.input} value={props.value} defaultValue={props.defaultValue} required={props.required} ref={ref} onChange={props.onChange} disabled={props.disabled}></textarea>
                :
                <input className={css.input} placeholder={props.placeholder} type={props.type} value={props.value} defaultValue={props.defaultValue} required={props.required} ref={ref} onChange={props.onChange} disabled={props.disabled} />
            }
        </label>
    );
});