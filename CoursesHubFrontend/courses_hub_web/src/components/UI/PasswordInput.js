import { useState } from "react";
import { Form } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faEye, faEyeSlash } from "@fortawesome/free-solid-svg-icons";

const PasswordInput = ({
  value,
  onChange,
  label = "Password:",
  placeholder = "Enter your password",
  controlId = "formPassword"
}) => {
  const [showPassword, setShowPassword] = useState(false);

  const toggleShowPassword = () => {
    setShowPassword((prev) => !prev);
  };

  return (
    <Form.Group controlId={controlId} className="mt-2">
      <Form.Label className="fw-medium">{label}</Form.Label>
      <div style={{ position: "relative" }}>
        <Form.Control
          type={showPassword ? "text" : "password"}
          placeholder={placeholder}
          className="rounded-3 shadow-none custom-input"
          value={value}
          onChange={onChange}
        />
        <button
          type="button"
          onClick={toggleShowPassword}
          tabIndex={-1}
          aria-label={showPassword ? "Hide password" : "Show password"}
          style={{
            position: "absolute",
            top: "50%",
            right: "12px",
            transform: "translateY(-50%)",
            border: "none",
            background: "transparent",
            padding: 0,
            margin: 0,
            cursor: "pointer",
            color: "#6c757d"
          }}
        >
          <FontAwesomeIcon icon={showPassword ? faEyeSlash : faEye} />
        </button>
      </div>
    </Form.Group>
  );
};

export default PasswordInput;