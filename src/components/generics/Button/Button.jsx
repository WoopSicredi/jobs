import React from 'react';
import PropTypes from 'prop-types';

import './Button.scss';

export const Button = ({ primary, type, size, text, onClick }) => {
  return (
    <div className='button'>
      <button
        className={`button-content ${
          primary ? 'primary' : 'secondary'
        } ${size}`}
        type={type}
        onClick={onClick}>
        {text}
      </button>
    </div>
  );
};

Button.propTypes = {
  primary: PropTypes.bool.isRequired,
  type: PropTypes.oneOf(['button', 'reset', 'submit']).isRequired,
  size: PropTypes.oneOf(['huge', 'big', 'medium', 'small']).isRequired,
  text: PropTypes.string.isRequired,
  onClick: PropTypes.func,
};
