import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
  faFacebookF,
  faDribbble,
  faTwitter,
  faGooglePlusG
} from '@fortawesome/free-brands-svg-icons';
import {
  faHome,
  faEnvelope,
  faPhone,
  faPrint
} from '@fortawesome/free-solid-svg-icons';

const Footer = () => {
  return (
    <footer className="text-white text-center text-lg-start bg-dark">
      <div className="container p-4">
        <div className="row mt-4">
          {/* About company */}
          <div className="col-lg-4 col-md-12 mb-4 mb-md-0">
            <h5 className="text-uppercase mb-4">About company</h5>
            <p>
              At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium
              voluptatum deleniti atque corrupti.
            </p>
            <p>
              Blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas
              molestias.
            </p>
            <div className="mt-4">
              <button type="button" className="btn btn-floating btn-dark btn-lg me-2">
                <FontAwesomeIcon icon={faFacebookF} />
              </button>
              <button type="button" className="btn btn-floating btn-dark btn-lg me-2">
                <FontAwesomeIcon icon={faDribbble} />
              </button>
              <button type="button" className="btn btn-floating btn-dark btn-lg me-2">
                <FontAwesomeIcon icon={faTwitter} />
              </button>
              <button type="button" className="btn btn-floating btn-dark btn-lg">
                <FontAwesomeIcon icon={faGooglePlusG} />
              </button>
            </div>
          </div>

          {/* Useful links */}
          <div className="col-lg-2 col-md-3 col-xl-2 mx-auto mb-4 mb-md-0">
            <h6 className="text-uppercase fw-bold mb-4">Useful links</h6>
            <p><a href="#!" className="text-reset text-decoration-none">Pricing</a></p>
            <p><a href="#!" className="text-reset text-decoration-none">Settings</a></p>
            <p><a href="#!" className="text-reset text-decoration-none">Orders</a></p>
            <p><a href="#!" className="text-reset text-decoration-none">Help</a></p>
          </div>

          {/* Search */}
          <div className="col-lg-4 col-md-6 mb-4 mb-md-0">
            <h5 className="text-uppercase mb-4 pb-1">Search something</h5>
            <div className="form-floating mb-4">
              <input type="text" id="searchInput" className="form-control" placeholder="Search" />
              <label htmlFor="searchInput">Search</label>
            </div>

            <ul className="fa-ul" style={{ marginLeft: '1.65em' }}>
              <li className="mb-3">
                <span className="fa-li"><FontAwesomeIcon icon={faHome} /></span>
                <span className="ms-2">Ho Chi Minh City, HCMC 00700, VietNam</span>
              </li>
              <li className="mb-3">
                <span className="fa-li"><FontAwesomeIcon icon={faEnvelope} /></span>
                <span className="ms-2">nhat.nd283@gmail.com</span>
              </li>
              <li className="mb-3">
                <span className="fa-li"><FontAwesomeIcon icon={faPhone} /></span>
                <span className="ms-2">+84 961 305 387</span>
              </li>
              <li className="mb-3">
                <span className="fa-li"><FontAwesomeIcon icon={faPrint} /></span>
                <span className="ms-2">+ 01 234 567 89</span>
              </li>
            </ul>
          </div>
        </div>
      </div>

      <div className="text-center p-3 bg-dark bg-opacity-25">
        © 2025 Copyright:
        <a className="text-white text-decoration-none" href="https://github.com/apelion283/CoursesHub">
          {' '}Courses Hub
        </a>
      </div>
    </footer>
  );
};

export default Footer;
