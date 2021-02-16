import PropTypes from 'prop-types'

const Button = ({ text, color, onClick, textColor, weight, border, padding }) => {
    return <button onClick={onClick} style={{ backgroundColor: color, color: textColor, fontWeight: weight, border: border, paddingLeft: padding, paddingRight: padding }} className="btn">{text}</button>
}

export default Button
