import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router';

import PageHeader from '../../components/PageHeader';

import api from '../../services/api';

import './edit-dragon.scss';

function EditDragon() {
    document.title = "Dragons :: Edit";

    const { dragonId } = useParams();
    const [dragon, setDragon] = useState({})
    const [name, setName] = useState('');
    const [type, setType] = useState('');
    const [histories, setHistories] = useState('');

    const title = `Editar Dragão: ${dragon.name}`;

    useEffect(() => {
        async function getDragon() {
            const response = await api.get(`/${dragonId}`);
            setDragon(response.data);
            setName(dragon.name);
            setType(dragon.type);
            setHistories(dragon.histories);
        }
        getDragon();
    }, [dragon.histories, dragon.name, dragon.type, dragonId]);

    async function handleUpdateDragon(e) {
        e.preventDefault();
        const data = { name, type, histories };
        const response = await api.put(`/${dragonId}`, data);
        setDragon(response.data);

        window.location.reload();
    }

    return (
        <div className="edit">
            <PageHeader title={title} />
            <div className="edit-form">
                <form className="form" onSubmit={ handleUpdateDragon }>
                <div className="form-group">
                    <label htmlFor="">Nome:</label>
                    <input
                            type="text"
                            name="name"
                            id="name"
                            required
                            value={name}
                            onChange={ e => setName(e.target.value)}
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="">Tipo:</label>
                    <input
                            type="text"
                            name="type"
                            id="type"
                            required
                            value={type}
                            onChange={ e => setType(e.target.value)}
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="histories">Histórias:</label>
                    <textarea
                            name="histories"
                            id="histories"
                            rows="10"
                            defaultValue={histories}
                            onChange={ e => setHistories(e.target.value)}
                    ></textarea>
                </div>
                <div className="form-group">
                    <button type="submit">Salvar</button>
                </div>
            </form>
            </div>
        </div>
    )
}

export default EditDragon;