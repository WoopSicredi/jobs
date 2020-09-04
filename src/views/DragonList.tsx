import * as React from "react";
import { createUseStyles } from "react-jss";

import { DragonCard } from "../components/DragonCard";
import { getDragons, Dragon } from "../services/dragon";

function sortDragonsArray(array: Dragon[]) {
    return array.sort((a, b) => {
        return a.name.localeCompare(b.name)
    });
}

const useStyles = createUseStyles({
    container: {
        display: "flex",
        flexWrap: "wrap",
        alignItems: "baseline",
    },
    containerChild: {
        flexGrow: 1,
        flexBasis: "47%",
        margin: "10px 10px",
    }
});

export function DragonList() {
    const css = useStyles();
    const [ dragons, setDragons ] = React.useState<Dragon[]>(null);
    
    React.useEffect(() => {

        getDragons()
        .then((dragons) => {
            setDragons( sortDragonsArray(dragons) );
        })
        .catch((err) => {
            alert(err);
            console.error(err);
        });

    }, []);

    function renderDragonsList() {
        return (
        <div className={css.container}>
            {dragons.map((dragon, index) => {
                return (
                    <DragonCard 
                        key={index}
                        className={css.containerChild}
                        histories={dragon.histories} 
                        name={dragon.name} 
                        type={dragon.type} 
                        createdAt={dragon.createdAt} 
                        history={dragon.history}
                        id={dragon.id} ></DragonCard>
                );
            })}
        </div>
        );
    }

    return (
        <>
            {dragons? renderDragonsList()
            :
            "Loading..."
            }
        </>
    );
}