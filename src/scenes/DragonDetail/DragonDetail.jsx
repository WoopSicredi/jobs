import React, { useState, useEffect } from 'react';
import { useParams, useHistory } from 'react-router-dom';

import DragonService from 'services';

import dragonReplacerIcon from 'assets/icons/dragon-replacer.svg';
import editIcon from 'assets/icons/edit-primary.svg';
import deleteIcon from 'assets/icons/delete-primary.svg';

import './DragonDetail.scss';

export const DragonDetail = () => {
  const _history = useHistory();
  const { id } = useParams();
  const [_dragon, _setDragon] = useState({});

  useEffect(() => {
    const _loadDragon = () => {
      DragonService.find(id)
        .then((result) => _setDragon(result))
        .catch((error) => {
          window.alert(error.message);
          _history.push('/');
        });
    };
    _loadDragon();
  }, [id]);

  const _deleteDragon = () => {
    if (
      window.confirm(
        'Deseja continuar? Este dragão será enviado para um cemitério de dragões.'
      )
    ) {
      DragonService.delete(id)
        .then(() => _history.replace('/'))
        .catch((error) => {
          window.alert(error.message);
        });
    }
  };

  const _goToEditPage = () => {
    _history.push(`/editar/${id}`);
  };

  const _getFormattedDate = (date) => {
    return new Date(date).toLocaleDateString('pt-BR');
  };

  return (
    <div className='dragon-detail'>
      <div className='dragon-detail__image'>
        <img
          src={_dragon.image ? _dragon.image : dragonReplacerIcon}
          alt={_dragon.name}
        />
      </div>
      <div className='dragon-detail__info'>
        <div className='dragon-detail__info__header'>
          <div className='dragon-info'>
            <p className='dragon-name'>{_dragon.name}</p>
            <hr className='divider' />
          </div>
          <div className='actions'>
            <button
              className='btn-action__edit'
              onClick={() => {
                _goToEditPage();
              }}>
              <img src={editIcon} alt='Editar' />
            </button>
            <button
              className='btn-action__delete'
              onClick={() => {
                _deleteDragon();
              }}>
              <img src={deleteIcon} alt='Excluir' />
            </button>
          </div>
        </div>
        <p className='dragon-type'>
          <span className='attribute'>Tipo:</span>{' '}
          <span className='value'>{_dragon.type}</span>
        </p>
        <p className='dragon-created-at'>
          <span className='attribute'>Criado em:</span>{' '}
          <span className='value'>{_getFormattedDate(_dragon.createdAt)}</span>
        </p>
        <p className='dragon-histories'>{_dragon.histories}</p>
      </div>
    </div>
  );
};
