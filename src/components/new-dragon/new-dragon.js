import React, { useState } from "react";
import { connect } from 'react-redux';
import { updateObject } from "./../../shared/utility";
import * as actions from "../../store/actions/";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faSave } from "@fortawesome/free-solid-svg-icons";
import "./new-dragon.css";

const NewDragon = props => {
  const [newDragon, setNewDragon] = useState({
    name:"",
    type: "",
    createdAt: new Date().toString(),
    hitories: []
  });

  const submitHandler = event => {
    if(newDragon.name.length > 0 && newDragon.type.length > 0)
    {
      const creationDate = new Date().toString();
      const histories = [];
      const newDragonUpdate = updateObject(newDragon, {createdAt: creationDate, histories: histories})
      setNewDragon({...newDragonUpdate});
      props.createDragon(newDragon)
    }
    event.preventDefault();
  }

  const changeHandler = event => {
    const { id, value } = event.target;
    setNewDragon({ ...newDragon, [id]: value });
  };

  return (
    <div className="NewDragon" >
  
        <div>
          <label>Name</label>
          <input type="text" id="name" placeholder="Name" onChange={changeHandler}/>
        </div>
        <div>
          <label>Type</label>
          <input type="text" id="type" placeholder="Type" onChange={changeHandler}/>
        </div>
  
        <span onClick={submitHandler}>
          <FontAwesomeIcon icon={faSave} className="saveButton" size="2x" />
        </span>
        <div>
          {props.error && <p>{props.error}</p>} 
        </div>
    </div>

  );
}

const mapStateToProps = state => {
  return {
    error: state.dragon.error
  }
}

const mapDispatchToProps = dispatch => {
  return {
    createDragon: (newDragon) => dispatch(actions.createDragon(newDragon))
  }
}

export default connect(null,mapDispatchToProps)(NewDragon);
