import React from "react";
import appLogo from "../../../assets/logos/courses-hub-logo.svg";
import "./SignIn.css";
import 'bootstrap/dist/css/bootstrap.min.css';
import { Link } from "react-router-dom";
import SigninForm from '../../../components/Auth/SignIn/Form';

const SignInScreen = () => {
  return (
    <div className="vh-100 d-flex flex-column bg-light">
      <div className="signin-banner">
        <Link to="/" className="signin-logo-link d-flex align-items-center text-white text-decoration-none">
          <img src={appLogo} alt="App Logo" />
          <span className="ms-2 h4">Course Hub</span>
        </Link>
        <SigninForm className="signin-form-card"/>
      </div>
    </div>
  );
};

export default SignInScreen;
