import React from 'react';
import PropTypes from 'prop-types';

import dragonReplacerIcon from 'assets/icons/dragon-replacer.svg';
import editIcon from 'assets/icons/edit.svg';
import deleteIcon from 'assets/icons/delete.svg';

import './DragonCard.scss';

export const DragonCard = ({ id, name, image, onClick, onEdit, onDelete }) => {
  return (
    <div className='dragon-card'>
      <div className='dragon-card__header' onClick={onClick}>
        <p className='dragon-name'>{name}</p>
      </div>
      <div className='dragon-card__content' onClick={onClick}>
        <img
          className='dragon-image'
          src={image ? image : dragonReplacerIcon}
          alt={name}
        />
      </div>
      <div className='dragon-card__footer'>
        <button className='btn-action__edit' onClick={onEdit}>
          <img src={editIcon} alt='Editar' />
        </button>
        <button className='btn-action__delete' onClick={onDelete}>
          <img src={deleteIcon} alt='Excluir' />
        </button>
      </div>
    </div>
  );
};

DragonCard.prototypes = {
  id: PropTypes.string.isRequired,
  name: PropTypes.string.isRequired,
  image: PropTypes.string,
  onClick: PropTypes.func,
  onEdit: PropTypes.func,
  onDelete: PropTypes.func,
};
