import React from 'react';
import PropTypes from 'prop-types';

import './Textarea.scss';

export const Textarea = ({
  name,
  required,
  rows,
  maxLength,
  value,
  onChange,
  text,
}) => {
  return (
    <div className='textarea'>
      <textarea
        className={`textarea-content ${value ? 'not-empty' : 'empty'}`}
        id={name}
        name={name}
        required={required}
        maxLength={maxLength}
        rows={rows}
        value={value}
        onChange={onChange}
      />
      <span className='highlight' />
      <span className='bar' />
      <label htmlFor={name}>{text}</label>
    </div>
  );
};

Textarea.propTypes = {
  name: PropTypes.string.isRequired,
  required: PropTypes.bool.isRequired,
  rows: PropTypes.number,
  maxLength: PropTypes.number,
  value: PropTypes.string,
  onChange: PropTypes.func.isRequired,
  text: PropTypes.string.isRequired,
};
