import { FaTimes, FaInfoCircle, FaEdit, FaSave } from 'react-icons/fa'
import { HiOutlineInformationCircle, HiMail } from 'react-icons/hi'
import { RiLockPasswordFill } from 'react-icons/ri';


import { Link } from 'react-router-dom'
import { useState } from 'react'

import '../css/dragoes.scss';

const Dragoes = ({ dragons, onDelete, editDragon, dragonEditing, saveDragon, modifyDragon }) => {
    const dragonInfo = (id) => {
        console.log(id);
        <Link to="/dragao">About</Link>
    }

    const clear = (id) => {
        setName('')
        setType('')
        setEmail('')
        setPassword('')
        editDragon(id)
    }

    const [name, setName] = useState('')
    const [type, setType] = useState('')
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')

    const onSubmit = (e) => {
        e.preventDefault()


        // if (!name) {
        //     alert('Please add task')
        //     return
        // }

        // onAdd({ name, type })

        // setName('')
        // setType('')
    }


    return (
        <>
            <div className="d-none d-lg-block">
                <form onSubmit={onSubmit}>
                    <table className="w-100">
                        <thead>
                            <tr>
                                <th className="first-row"></th>
                                <th>Nome</th>
                                <th>Tipo</th>
                                <th>Email</th>
                                <th>Senha</th>
                            </tr>
                        </thead>
                        <tbody>
                            {dragons.map((dragon) => (

                                <tr key={dragon.id} >
                                    <td>
                                        {dragonEditing !== dragon.id ?
                                            <div className="icon-text first-row tooltip-icon">
                                                <span className="tooltiptext">Editar</span>
                                                <FaEdit className="icons" onClick={() => clear(dragon.id)} />
                                            </div> :
                                            <div className="icon-text first-row icon-save tooltip-icon">
                                                <span className="tooltiptext">Salvar</span>
                                                <FaSave className="icons" onClick={() => modifyDragon(name, type, email, password, dragon.id)} />
                                            </div>}
                                    </td>
                                    <td>{dragonEditing !== dragon.id ? dragon.name :
                                        <input
                                            type="text"
                                            value={name}
                                            onChange={(e) => setName(e.target.value)}
                                        ></input>}
                                    </td>
                                    <td>{dragonEditing !== dragon.id ? dragon.type :
                                        <input
                                            type="text"
                                            value={type}
                                            onChange={(e) => setType(e.target.value)}
                                        ></input>}
                                    </td>
                                    <td>{dragonEditing !== dragon.id ? dragon.email :
                                        <input
                                            type="text"
                                            value={email}
                                            onChange={(e) => setEmail(e.target.value)}
                                        ></input>}
                                    </td>
                                    <td>{dragonEditing !== dragon.id ? dragon.password :
                                        <input
                                            type="text"
                                            value={password}
                                            onChange={(e) => setPassword(e.target.value)}
                                        ></input>}
                                    </td>
                                    <td className="icon-td">
                                        <div className="info-icon icon-text tooltip-icon">
                                            <span className="tooltiptext">Informações</span>
                                            <Link to={`/dragao/${dragon.id}`} ><HiOutlineInformationCircle className="icons" /></Link>
                                        </div>
                                        <div>
                                            <div className="icon-text tooltip-icon">
                                                <span className="tooltiptext">Excluir</span>
                                                <FaTimes className="icons" onClick={() => onDelete(dragon.id)} />
                                            </div>
                                        </div>
                                    </td>
                                </tr>

                            ))}
                        </tbody>
                    </table>
                </form>
            </div>
            <div className="d-block d-lg-none">

                <form onSubmit={onSubmit}>
                    <table className="w-100 table-mobile">
                        <tbody>
                            {dragons.map((dragon) => (

                                <tr key={dragon.id} >
                                    <td className="pl-3 first-col">
                                        <div className="w-100">
                                            {dragonEditing !== dragon.id ?
                                                <div className="dragon-name"> {dragon.name}</div> :
                                                <div className="d-flex flex-column">
                                                    <label className="list-label" htmlFor="list-name">Nome</label>
                                                    <input
                                                        className="list-input"
                                                        id="list-name"
                                                        type="text"
                                                        value={name}
                                                        onChange={(e) => setName(e.target.value)}
                                                    ></input>
                                                </div>}
                                            {dragonEditing !== dragon.id ?
                                                <div className="dragon-infos">{dragon.type}</div> :
                                                <div className="d-flex flex-column">
                                                    <label className="list-label" htmlFor="list-type">Tipo</label>
                                                    <input
                                                        className="list-input"
                                                        id="list-type"
                                                        type="text"
                                                        value={type}
                                                        onChange={(e) => setType(e.target.value)}
                                                    ></input>
                                                </div>}
                                            {dragonEditing !== dragon.id ?
                                                <div className="dragon-infos">{dragon.email && <HiMail className="mr-2" />}{dragon.email}</div> :
                                                <div className="d-flex flex-column">
                                                    <label className="list-label" htmlFor="list-mail">E-mail</label>
                                                    <input
                                                        className="list-input"
                                                        id="list-mail"
                                                        type="text"
                                                        value={email}
                                                        onChange={(e) => setEmail(e.target.value)}
                                                    ></input>
                                                </div>}
                                            {dragonEditing !== dragon.id ?
                                                <div className="dragon-infos">{dragon.password && <RiLockPasswordFill className="mr-2" />}{dragon.password}</div> :
                                                <div className="d-flex flex-column">
                                                    <label className="list-label" htmlFor="list-password">Senha</label>
                                                    <input
                                                        className="list-input"
                                                        id="list-password"
                                                        type="text"
                                                        value={password}
                                                        onChange={(e) => setPassword(e.target.value)}
                                                    ></input>
                                                </div>}
                                        </div>

                                        <div className="icon-td right-icons">
                                            {dragonEditing !== dragon.id ?
                                                <div className="icon-text first-row tooltip-icon">
                                                    <span className="tooltiptext">Editar</span>
                                                    <FaEdit className="icons" onClick={() => clear(dragon.id)} />
                                                </div> :
                                                <div className="icon-text first-row icon-save tooltip-icon">
                                                    <span className="tooltiptext">Salvar</span>
                                                    <FaSave className="icons" onClick={() => modifyDragon(name, type, email, password, dragon.id)} />
                                                </div>}
                                            <div className="info-icon icon-text tooltip-icon">
                                                <span className="tooltiptext">Informações</span>
                                                <Link to={`/dragao/${dragon.id}`} ><HiOutlineInformationCircle className="icons" /></Link>
                                            </div>
                                            <div>
                                                <div className="icon-text tooltip-icon">
                                                    <span className="tooltiptext">Excluir</span>
                                                    <FaTimes className="icons" onClick={() => onDelete(dragon.id)} />
                                                </div>
                                            </div>
                                        </div>
                                    </td>
                                </tr>

                            ))}
                        </tbody>
                    </table>
                </form>
            </div>
        </>
    )
}

export default Dragoes