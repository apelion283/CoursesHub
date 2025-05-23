import { useState } from "react";
import { Form } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import "./Form.css";
import { Link } from "react-router-dom";
import PasswordInput from '../../UI/PasswordInput';

const LoginForm = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");


  return (
    <Form className="p-4 shadow bg-white card signin-form-card">
      <div className="card-body">
        <h4 className="text-center mb-3 fw-medium">Welcome To CourseHub</h4>
        <p className="text-center sub-title">Login to Continue</p>
      </div>

      <Form.Group controlId="formUsername">
        <Form.Label className="fw-medium">Username:</Form.Label>
        <Form.Control
          type="text"
          placeholder="Enter your username"
          className="rounded-3 shadow-none custom-input"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
      </Form.Group>

      <PasswordInput value={password} onChange={(e) => setPassword(e.target.value)} label="Password:" />

      <div className="d-flex align-items-center justify-content-between mt-3">
        <Link to="/forgot-password" className="text-decoration-none forgot-password">Forgot your password?</Link>
        <Link to="/">
        <button className="fw-medium ms-1    px-5 py-2 text-dark signin-button">
          Sign In
        </button></Link>
      </div>

      <div className="d-flex align-items-center justify-content-center mt-4">
        <p className="mb-0 me-2">New User?</p>
        <Link to="/sign-up" className="text-decoration-none create-account">Create an account</Link>
      </div>
    </Form>
  );
};

export default LoginForm;
