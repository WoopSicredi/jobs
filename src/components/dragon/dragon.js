import React, { useState, useEffect } from "react";
import { connect } from 'react-redux';
import * as actions from "../../store/actions/";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faEdit,faDragon, faArrowCircleLeft, faSave, faTrashAlt } from "@fortawesome/free-solid-svg-icons";


import "./dragon.css";

const Dragon = props => {
  const [dragonDetails, setDragonDetails] = useState({});
  const [isEdit, setIsEdit] = useState(false);


  useEffect(()=>{
    display = setDisplayMode();
  },[isEdit])


  useEffect(() => {
    setDragonDetails({
      ...props.location.state
    });
  }, []);

  useEffect(() => {
    if(props.wasDeleted) {
      props.history.goBack();
    }
  },[props.wasDeleted])


  const formatDate = date => {
    return new Date(date).toDateString();
  };

  const changeHandler = (event) => {
    const { id, value } = event.target;
    setDragonDetails({ ...dragonDetails, [id]: value });
  };

  const goBackHandler = () => {
    props.history.goBack();
  };

  const saveDragonHandler = () => {
    props.saveDragon(dragonDetails);
    setIsEdit(false);
  }

  const deleteDragonHandler = () => {
    props.deleteDragon(dragonDetails.id);
  }
  const editDragonHandler = () => {
    setIsEdit(!isEdit);
  };
  const editMode = () => {
    return (
      <>
        <div>
          <input
            type="text"
            id="name"
            value={dragonDetails.name}
            onChange={changeHandler}
          />
        </div>
        <div>
          <input
            type="text"
            id="type"
            value={dragonDetails.type}
            onChange={changeHandler}
          />
        </div>
      </>
    );
  };
  const infoMode = () => {
    return (
        <>
          <div>
            <h2>{dragonDetails.name}</h2>
          </div>
          <div>
            <h3>{dragonDetails.type}</h3>
          </div>
        </>
    );
  };

  const setDisplayMode = () => {

    switch (isEdit) {
      case true:
        return editMode();
      case false:
        return infoMode();
    }
  };


  let display = (
    <div className="Dragon">
    <div className="first-row-info">
      <span className="id">{dragonDetails.id}</span>
      <span>{formatDate(dragonDetails.createdAt)}</span>
    </div>
    {setDisplayMode()}
  </div>
  )
  return (
    <div>
            <div className="Logo">
        <FontAwesomeIcon icon={faDragon} size="5x" />
      </div>
      {display}
      <div className="Commands">
        <span onClick={goBackHandler}>
          <FontAwesomeIcon icon={faArrowCircleLeft} size="2x" />
        </span>
        <span onClick={editDragonHandler}>
          <FontAwesomeIcon icon={faEdit} size="2x" />
        </span>
        { isEdit && <span onClick={saveDragonHandler}>
          <FontAwesomeIcon icon={faSave} size="2x" />  
        </span>}
        {!isEdit && <span onClick={deleteDragonHandler}>
          <FontAwesomeIcon icon={faTrashAlt} size="2x" />          
        </span>}
      </div>
      <div>
          {props.error && <p>{props.error}</p>} 
      </div>
    </div>
  );
};

const mapStateToProps = state => {
  return {
    successMessage: state.dragon.successMessage,
    error: state.dragon.error,
    wasDeleted: state.dragon.wasDeleted
  }
}

const mapDispatchToProps = dispatch => {
  return {
    saveDragon: (dragonDetails) => dispatch(actions.saveDragon(dragonDetails)),
    deleteDragon: (dragonId) => dispatch(actions.deleteDragon(dragonId))
  }
}


export default connect(mapStateToProps,mapDispatchToProps)(Dragon);
