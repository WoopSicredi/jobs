import Button from './Button'
import '../css/edit.scss';


const Edit = ({ showAdd, onAdd, user, Logout }) => {
    return (
        <div id="edit-page">
            <div className="btn-wrapper">
                <h2>Dragões</h2>
                <Button
                    textColor={'white'}
                    weight={'600'}
                    color={showAdd ? '#2c045a' : '#CF4FF1'}
                    text={showAdd ? 'CANCELAR' : 'ADICIONAR'}
                    padding={'1.2rem'}
                    onClick={onAdd} />
            </div>
            {user.email != "" &&
                <Button
                    textColor={'white'}
                    color={'red'}
                    text={'SAIR'}
                    onClick={Logout}
                    color={'transparent'}
                    border={'2px solid rgb(207, 79, 241)'}
                />
            }
        </div>
    )
}

export default Edit
