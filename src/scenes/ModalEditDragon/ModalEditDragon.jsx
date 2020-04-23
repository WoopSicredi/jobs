import React from 'react';
import { useHistory } from 'react-router-dom';

import { CreateOrEditDragon } from 'scenes';

import closeIcon from 'assets/icons/close-primary.svg';

import './ModalEditDragon.scss';

export const ModalEditDragon = () => {
  const _history = useHistory();

  const back = (e) => {
    e.stopPropagation();
    _history.goBack();
  };

  return (
    <div className='modal-edit-dragon'>
      <div className='modal-edit-dragon__content'>
        <button className='action action__close' onClick={back}>
          <img src={closeIcon} alt='Fechar' />
        </button>
        <div className='edit-dragon'>
          <CreateOrEditDragon />
        </div>
      </div>
    </div>
  );
};
