import React from "react";
import { useNavigate } from "react-router-dom";
import "./Navbar.css";

export default function Navbar() {
	const navigate = useNavigate();
	const openLibrary = () => {
		navigate("/library");
	};
	const openHomePage = () => {
		navigate("/");
	};
	const openLoginPage = () => {
		navigate("/login");
	};

	return (
		<div class="navbar-body">
			<div class="navbar-left">
				<a id="home" onClick={openHomePage}>
					Home
				</a>
				<a id="library" onClick={openLibrary}>
					Library
				</a>
			</div>
			<div class="navbar-right">
				<button id="loginbtn" onClick={openLoginPage}>
					Log In
				</button>
				<button id="signupbtn">Sign Up</button>
			</div>
		</div>
	);
}
