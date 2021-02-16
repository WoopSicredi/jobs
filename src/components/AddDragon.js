import { useState } from 'react'

import '../css/addDragon.scss';


const AddDragon = ({ onAdd }) => {
    const [name, setName] = useState('')
    const [type, setType] = useState('')

    const onSubmit = (e) => {
        e.preventDefault()

        if (!name) {
            alert('Please add task')
            return
        }

        onAdd({ name, type })

        setName('')
        setType('')
    }

    return (
        <form className='add-form col-12 py-0' onSubmit={onSubmit} >
            <div className="input-group">

                <div className="add-group col-12 col-lg-6 pl-0 pr-0 pr-lg-1 pl-lg-0 mb-3">
                    <label htmlFor="add-nome-dragao">Nome </label>
                    <input type="text" id="add-nome-dragao" value={name} onChange={(e) => setName(e.target.value)} />
                </div>
                <div className="add-group col-12 col-lg-6 pl-0 pr-0 pl-lg-1 pr-lg-0">
                    <label htmlFor="add-tipo-dragao">Tipo</label>
                    <input type="text" id="add-tipo-dragao" value={type} onChange={(e) => setType(e.target.value)} />
                </div>
            </div>
            <input className='btn save-btn' type="submit" value='SALVAR' />
        </form>
    )
}

export default AddDragon
