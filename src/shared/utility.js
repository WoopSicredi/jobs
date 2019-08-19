export const updateObject = (oldObject, updatedProperties) => {
  return {
    ...oldObject,
    ...updatedProperties
  }
}


export const sortList = list => {
  return list.sort((dragonA, dragonB)=> {
      let currentDragon = dragonA.name;
      let nextDragon = dragonB.name;
      if (currentDragon < nextDragon) {
          return -1;
      }
      if (currentDragon > nextDragon) {
          return 1;
      }
      return 0;
  })
}