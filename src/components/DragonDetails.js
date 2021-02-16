import { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'


import '../css/dragonDetails.scss';


const DragonDetails = (props) => {
    useEffect(() => {
        const getDragon = async () => {
            const dragonFromAPI = await props.fetchDragon(props.match.params.id)
            props.setSelectedDragon(dragonFromAPI)
        }
        getDragon()
    }, [])

    return (
        <div className="w-100 px-3 pt-1 pb-1">
            <div className="go-back">

                <Link to="/">&lt; Voltar</Link>
            </div>
            <h2>{props.selectedDragon.name}</h2>
            <p className="details-text"><b>Tipo:</b> {props.selectedDragon.type}</p>
            <p className="details-text"><b>Criado em:</b> {props.selectedDragon.createdAt}</p>
        </div>
    )
}

export default DragonDetails
