import React, { useEffect, useState } from 'react'

import PageHeader from '../../components/PageHeader';
import CreateDragonForm from '../../components/CreateDragonForm';

import api from '../../services/api';
import moment from 'moment';

import edit from '../../assets/img/edit.svg';
import remove from '../../assets/img/remove.svg';

import './dragons.scss'
import { Link } from 'react-router-dom';

function Dragons() {
    document.title = "Dragons :: List";
    
    const [dragons, setDragons] = useState([]);
    const [openCreate, setOpenCreate] = useState(false);
    
    const title = `Dragões Cadastrados (${dragons.length})`;

    useEffect(() => {
        async function getDragons() {
            const response = await api.get('/');
            const data = response.data.sort(function(a,b) {
                if (a.name > b.name) {
                    return 1;
                }
                if (a.name < b.name) {
                    return -1;
                }
                return 0;
            });
            setDragons(data);
        }
        getDragons();
    }, []);

    async function handleAddDragon(data) {
        const response = await api.post('/', data);
        setDragons([...dragons, response.data]);
        setOpenCreate(false);
    }

    async function removeDragon(id) {
        await api.delete(`/${id}`);
        window.location.reload();
    }

    return (
        <div className="dragons">
            <PageHeader title={ title } />
            <button className="button" onClick={ () => setOpenCreate(true)}>Adicionar Dragão</button>
            <table>
                <thead>
                    <tr>
                        <th>Nome</th>
                        <th>Tipo</th>
                        <th>Data</th>
                        <th>Ação</th>
                    </tr>
                </thead>
                <tbody>
                    {dragons.map(dragon => (
                        <tr key={ dragon.id }>
                            <td>{ dragon.name}</td>
                            <td>{ dragon.type}</td>
                            <td>{moment(dragon.createdAt).format('DD/MM/YY')}</td>
                            <td>
                                <Link to={ `/dragons/edit/${dragon.id}`}><img src={edit} alt="Edit button" /></Link>
                                <img src={remove} alt="Remove button" onClick={ () => removeDragon(dragon.id)} />
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
            
            <div className={`modal ${openCreate ? 'open' : null}`}>
                <div className="modal-box">
                    <div className="modal-box--close">
                        <h4>Adicionar Dragão</h4>
                        <img src={ remove } alt="Close modal button" onClick={() => setOpenCreate(false)} />
                    </div>
                    <div className="modal-box--body">
                        <CreateDragonForm onSubmit={handleAddDragon} />
                    </div>
                </div>
            </div>
            
        </div>
    )
}

export default Dragons;