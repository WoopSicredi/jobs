import React, { useState } from 'react';
import moment from 'moment';

import './components.scss';

function CreateDragon({ onSubmit }) {

    const [name, setName] = useState('');
    const [type, setType] = useState('');
    const [histories, setHistories] = useState('');

    async function handleSubmit(e) {
        e.preventDefault();
        
        await onSubmit({
            name,
            type,
            createdAt: moment().toISOString(),
            histories
        });

        setName('');
        setType('');
        setHistories('');
    }

    return (
        <div className="create-dragon">
            <form onSubmit={ handleSubmit } className="form">
                <div className="form-group">
                    <label htmlFor="">Nome:</label>
                    <input
                        type="text"
                        name="name"
                        id="name"
                        required
                        value={name}
                        onChange={ e => setName(e.target.value) }
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
                        onChange={ e => setType(e.target.value) }
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="histories">Histórias:</label>
                    <textarea
                        name="histories"
                        id="histories"
                        rows="10"
                        onChange={e => setHistories(e.target.value)}
                        defaultValue={ histories }
                    ></textarea>
                </div>
                <div className="form-group">
                    <button type="submit">Enviar</button>
                </div>
            </form>
        </div>
    );
}

export default CreateDragon;