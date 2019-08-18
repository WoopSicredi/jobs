import React, { useState, useEffect } from "react";
import { connect } from "react-redux";
import * as actions from "../../store/actions/";
import NewDragon from "../new-dragon/new-dragon";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPlus, faDragon } from "@fortawesome/free-solid-svg-icons";
import "./dragonList.css";

const DragonsList = props => {

  const [isAddingNew, setIsAddingNew] = useState(false)

  useEffect(() => {
    initDragonsList();
  }, []);

  useEffect(() => {
    if(props.wasCreated) {
      setIsAddingNew(false);
      props.onGetDragonsList();
    }
  },[[props.wasCreated]])

  const initDragonsList = () => {
    props.onGetDragonsList();
  };

  const onClickDragonHandler = event => {
    props.history.push({ pathname: `/dragon/${event.id}`, state: event });
  };

  const addNewDragonHandler = () => {
    setIsAddingNew(!isAddingNew);
  }



  let list = <p>Loading</p>;
  if (!props.loading) {
    list = props.dragonsList.map(dragon => (
      <div key={dragon.id} className="dragon">
        <FontAwesomeIcon icon={faDragon} /> 
        <li onClick={onClickDragonHandler.bind(this, dragon)}>
          {dragon.name}
        </li>
      </div>
    ));
  }

  return (
    <div className="DragonList">
      <div className="Logo">
        <FontAwesomeIcon icon={faDragon} size="5x" />
      </div>
      <ul>{list}</ul>
      <div>
        <hr/>
        <span className="Command">
          <FontAwesomeIcon icon={faPlus} style={{marginRight: 2}}/>
          <span onClick={addNewDragonHandler}>Add New Dragon</span>
        </span>
      </div>
      <div>
        { isAddingNew && <NewDragon />}
      </div>
      <div>
          {props.error && <p>{props.error}</p>} 
      </div>
    </div>
  );
};

const mapStateToProps = state => {
  return {
    dragonsList: state.dragonList.dragons,
    loading: state.dragonList.loading,
    error: state.dragonList.error,
    wasCreated: state.dragon.wasCreated
  };
};

const mapDispatchToProps = dispatch => {
  return {
    onGetDragonsList: () => dispatch(actions.getDragonsList())
  };
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(DragonsList);
