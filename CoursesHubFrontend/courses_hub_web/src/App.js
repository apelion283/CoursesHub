import React from 'react';
import Home from './screens/Home/Home';
import SignIn from './screens/Auth/SignIn/SignIn';
import { BrowserRouter, Route, Routes } from 'react-router-dom';

const App = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<Home/>}></Route>
        <Route path='/sign-in' element={<SignIn/>}></Route>
      </Routes>
    </BrowserRouter>
  );
};

export default App;
