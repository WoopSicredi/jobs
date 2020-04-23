import React from 'react';
import PropTypes from 'prop-types';

import './Input.scss';

export const Input = ({
  name,
  type,
  required,
  maxLength,
  value,
  onChange,
  text,
}) => {
  return (
    <div className='input'>
      <input
        className={`input-content ${value ? 'not-empty' : 'empty'}`}
        id={name}
        name={name}
        type={type}
        required={required}
        maxLength={maxLength}
        value={value}
        onChange={onChange}
      />
      <span className='highlight' />
      <span className='bar' />
      <label htmlFor={name}>{text}</label>
    </div>
  );
};

Input.propTypes = {
  name: PropTypes.string.isRequired,
  type: PropTypes.oneOf([
    'date',
    'datetime-local',
    'email',
    'month',
    'number',
    'password',
    'tel',
    'text',
    'time',
  ]).isRequired,
  required: PropTypes.bool.isRequired,
  maxLength: PropTypes.number,
  value: PropTypes.string,
  onChange: PropTypes.func.isRequired,
  text: PropTypes.string.isRequired,
};
