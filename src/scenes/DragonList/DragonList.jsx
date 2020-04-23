import React, { useEffect, useState } from 'react';
import { useHistory, useLocation } from 'react-router-dom';

import DragonService from 'services';

import { DragonCard } from 'components';

import './DragonList.scss';

export const DragonList = () => {
  const _history = useHistory();
  const _location = useLocation();
  const [_dragons, _setDragons] = useState([]);

  const _shouldReload = !!_location.state && _location.state.reload;

  useEffect(() => {
    _loadDragons();
  }, []);

  useEffect(() => {
    if (_shouldReload) {
      _history.replace('', null);
      _loadDragons();
    }
  }, [_shouldReload]);

  const _loadDragons = () => {
    DragonService.findAll()
      .then((result) => {
        let dragons = result.dragons.sort((a, b) => {
          return a.name.localeCompare(b.name);
        });
        _setDragons(dragons);
      })
      .catch((error) => {
        window.alert(error.message);
      });
  };

  const _deleteDragon = (id) => {
    if (
      window.confirm(
        'Deseja continuar? Este dragão será enviado para um cemitério de dragões.'
      )
    ) {
      DragonService.delete(id)
        .then(() => _loadDragons())
        .catch((error) => {
          window.alert(error.message);
        });
    }
  };

  const _renderDragons = () => {
    return _dragons.map((dragon) => (
      <DragonCard
        key={dragon.id}
        name={dragon.name}
        image={dragon.image}
        onClick={() => {
          _history.push(`/detalhes/${dragon.id}`);
        }}
        onEdit={() => {
          _history.push(`/editar/${dragon.id}`, { background: _location });
        }}
        onDelete={() => {
          _deleteDragon(dragon.id);
        }}
      />
    ));
  };

  return (
    <div className='dragon-list'>
      <div className='dragon-list__content'>{_renderDragons()}</div>
    </div>
  );
};
