import React from 'react';
import RecipeList from './RecipeList';

function App() {
  return (
    <RecipeList fooditem={sample} />
  );
}

const sample = [{
  id: 1,
  name: 'Plain',
  serving: 4,
  CookTime: '1:45',
  Instruction: '1. Put salt and water\n2. Put water again\n3. Hand wash.'
}, {
  id: 2,
  name: 'Simple Chicken',
  serving: 2,
  CookTime: '2:45',
  Instruction: '1. Salt and water\n2. Water again\n3. Wash.'
  },
  {
    id: 3,
    name: 'Plain Chicken',
    serving: 3,
    CookTime: '1:45',
    Instruction: '1. Put salt and water\n2. Put water again\n3. Hand wash.'
  }
]

export default App;