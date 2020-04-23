import React from 'react';
import dragon from 'assets/icons/dragon.gif';
import './Loader.scss';

export const Loader = () => {
  return (
    <div className='loader'>
      <img src={dragon} alt='loader' />
    </div>
  );
};
