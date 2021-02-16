import { useState, useEffect } from 'react'
import { BrowserRouter as Router, Route } from 'react-router-dom'
import dragon from './img/Dragon01.svg'

import './css/main.scss';
import Dragoes from './components/Dragoes'
import Edit from './components/Edit'
import AddDragon from './components/AddDragon'
import DragonDetails from './components/DragonDetails'
import LoginPage from './components/LoginPage'
import Button from './components/Button';

function App() {
  const [showAddDragon, setShowAddDragon] = useState(false)
  const [dragons, setDragons] = useState([])
  const [selectedDragon, setSelectedDragon] = useState({})
  const [dragonEditing, setDragonEditing] = useState('')
  const [user, setUser] = useState({ email: "" })
  const [loginError, setLoginerror] = useState("")

  const Login = (details) => {
    console.log(details)

    if (details.email == adminUser.email && details.password == adminUser.password) {
      setUser({
        email: details.name
      })
    } else {
      setLoginerror('Usuário ou senha inválidos')
      setTimeout(() => { setLoginerror('') }, 2000);

    }
  }

  const Logout = () => {
    setUser({ email: "" })
  }

  const adminUser = {
    email: "admin@admin.com",
    password: "admin"
  }

  useEffect(() => {
    const getDragons = async () => {
      const dragonsFromAPI = await fetchDragons()

      // setDragons(dragonsFromAPI)
      setDragons(dragonsFromAPI.sort((a, b) => a.name.localeCompare(b.name)))
    }

    getDragons()
  }, [])


  // Fetch dragons
  const fetchDragons = async () => {
    const res = await fetch('http://5c4b2a47aa8ee500142b4887.mockapi.io/api/v1/dragon')
    const data = await res.json()

    return data
  }

  // Fetch Dragon
  const fetchDragon = async (id) => {
    const res = await fetch(`http://5c4b2a47aa8ee500142b4887.mockapi.io/api/v1/dragon/${id}`)
    const data = await res.json()

    return data
  }

  // Add dragon
  const addDragon = async (dragon) => {
    const res = await fetch('http://5c4b2a47aa8ee500142b4887.mockapi.io/api/v1/dragon', {
      method: 'POST',
      headers: {
        'Content-type': 'application/json'
      },
      body: JSON.stringify(dragon),
    })

    const data = await res.json()

    setDragons([...dragons, data])
  }

  // Delete dragon
  const deleteDragon = async (id) => {
    await fetch(`http://5c4b2a47aa8ee500142b4887.mockapi.io/api/v1/dragon/${id}`, {
      method: 'DELETE'
    })

    setDragons(dragons.filter((dragons) => dragons.id !== id))
  }

  // Edit Dragon
  const editDragon = (id) => {
    setDragonEditing(id)
  }

  // Modify Dragon
  const modifyDragon = async (name, type, email, password, id) => {
    const dragonToChange = await fetchDragon(id)
    const updDragon = {
      ...dragonToChange,
      name: name,
      type: type,
      email: email,
      password: password
    }

    const res = await fetch(`http://5c4b2a47aa8ee500142b4887.mockapi.io/api/v1/dragon/${id}`, {
      method: 'PUT',
      headers: {
        'Content-type': 'application/json'
      },
      body: JSON.stringify(updDragon)
    })

    const data = await res.json()

    setDragons(
      dragons.map((dragon) =>
        dragon.id === id ? {
          ...dragon, name: name,
          type: type,
          email: email,
          password: password
        } : dragon
      )
    )
    setDragonEditing('')
  }

  return (
    <Router>
      <div className="app-container container" >
        <h1 className="app-title mt-5">Listagem de dragões</h1>

        <div id="frame" className="p-3 shadow ">
          {/* <img id="dragon-logo" src={dragon} alt="Dragon logo"></img> */}
          <Route path='/' exact render={(props) => (
            <div className="w-100">
              {(user.email != "") ?
                <div className="frame-wrapper frame-list">
                  <Edit
                    onAdd={() => setShowAddDragon(!showAddDragon)}
                    showAdd={showAddDragon}
                    user={user}
                    Logout={Logout} />
                  {showAddDragon && <AddDragon onAdd={addDragon} />}
                  <Dragoes
                    dragons={dragons}
                    onDelete={deleteDragon}
                    editDragon={editDragon}
                    dragonEditing={dragonEditing}
                    modifyDragon={modifyDragon}
                  />
                </div>
                : <div className="frame-wrapper"><LoginPage Login={Login} loginError={loginError} /></div>}
            </div>
          )} />

          <Route path='/dragao/:id' exact render={(props) => (
            <>{(user.email != "") ?
              <DragonDetails
                {...props}
                fetchDragon={fetchDragon}
                setSelectedDragon={setSelectedDragon}
                selectedDragon={selectedDragon} />
              : <div className="w-100"> <div className="frame-wrapper"><LoginPage Login={Login} loginError={loginError} /></div></div>
            }
            </>
          )} />
        </div>

      </div>
    </Router>
  );
}

export default App;
