import * as React from "react";
import { useParams, useHistory } from "react-router-dom";

import { getDragon, Dragon } from "../services/dragon";

export function DragonDetail() {
    const [ dragon, setDragon ] = React.useState<Dragon>(null);
    const { id } = useParams<{id: string}>();
    const history = useHistory();
    
    React.useEffect(() => {

        getDragon(id)
        .then((dragon) => {
            setDragon(dragon);
        })
        .catch((err) => {
            console.error(err);
        });

    }, []);

    function renderDragon() {
        return (
            <div>
                <h2>{dragon.name}</h2>
                <div>
                    Type: <span>{ dragon.type }</span>
                </div>
                <div>
                    Data de criação: {dragon.createdAt}
                </div>
                <p>{dragon.history}</p>
                {dragon.histories.map((history, index) => {
                    return (
                        <p key={index}>{history}</p>
                    )
                })}
            </div>
        );
    }

    return (
        <>
            {dragon? renderDragon()
            :
            "Loading..."
            }
            <button onClick={history.goBack}>Voltar</button>
        </>
    );
}