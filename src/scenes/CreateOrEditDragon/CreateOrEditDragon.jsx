import React, { useEffect, useState } from 'react';
import { useHistory, useLocation, useParams } from 'react-router-dom';

import DragonService from 'services';

import { Input, Textarea, Button } from 'components';

import dragonReplacerIcon from 'assets/icons/dragon-replacer.svg';

import './CreateOrEditDragon.scss';

export const CreateOrEditDragon = () => {
  const _history = useHistory();
  const _location = useLocation();
  const { id } = useParams();
  const [_isEditingDragon, _setIsEditingDragon] = useState(false);
  const [_dragon, _setDragon] = useState({
    name: '',
    type: '',
    histories: '',
    image: '',
  });

  const _hasBackgroundLocation =
    !!_location.state && !!_location.state.background;

  useEffect(() => {
    const _verifyIfIsEditingDragon = () => {
      if (!!id) {
        _loadDragon();
        _setIsEditingDragon(true);
      } else {
        _setDragon({
          name: '',
          type: '',
          histories: '',
          image: '',
        });
        _setIsEditingDragon(false);
      }
    };

    const _loadDragon = () => {
      DragonService.find(id)
        .then((result) => _setDragon(result))
        .catch((error) => {
          window.alert(error.message);
          _history.push('/');
        });
    };

    _verifyIfIsEditingDragon();
  }, [id]);

  const _createDragon = () => {
    DragonService.create(
      _dragon.name,
      _dragon.type,
      _dragon.histories,
      _dragon.image
    )
      .then(() => {
        _history.goBack();
      })
      .catch((error) => {
        window.alert(error.message);
      });
  };

  const _editDragon = () => {
    DragonService.update(
      id,
      _dragon.name,
      _dragon.type,
      _dragon.histories,
      _dragon.image
    )
      .then(() => {
        _hasBackgroundLocation
          ? _history.push('/', { reload: true })
          : _history.goBack();
      })
      .catch((error) => {
        window.alert(error.message);
      });
  };

  const _handleChange = (e) => {
    const target = e.target;
    e.persist();
    e.preventDefault();
    return _setDragon((values) => ({
      ...values,
      [target.name]: target.value,
    }));
  };

  function _handleSubmit(e) {
    e.preventDefault();
    _isEditingDragon ? _editDragon() : _createDragon();
  }

  return (
    <div className='create-or-edit-dragon'>
      <div className='create-or-edit-dragon__image'>
        <img
          src={_dragon.image ? _dragon.image : dragonReplacerIcon}
          alt='Dragão'
        />
      </div>
      <div className='create-or-edit-dragon__form'>
        <form method='post' onSubmit={_handleSubmit}>
          <Input
            name='name'
            type='text'
            required={true}
            value={_dragon.name}
            onChange={_handleChange}
            text='Nome'
          />
          <Input
            name='type'
            type='text'
            required={false}
            value={_dragon.type}
            onChange={_handleChange}
            text='Tipo'
          />
          <Input
            name='image'
            type='text'
            required={false}
            value={_dragon.image}
            onChange={_handleChange}
            text='Imagem (URL)'
          />
          <Textarea
            name='histories'
            required={false}
            rows={5}
            value={_dragon.histories}
            onChange={_handleChange}
            text='História'
          />
          <Button
            primary={true}
            type='submit'
            size='medium'
            text={_isEditingDragon ? 'Editar' : 'Criar'}
          />
        </form>
      </div>
    </div>
  );
};
