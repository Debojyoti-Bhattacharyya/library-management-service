import FacebookIcon from "@mui/icons-material/Facebook";
import GitHubIcon from "@mui/icons-material/GitHub";
import GoogleIcon from "@mui/icons-material/Google";
import LinkedInIcon from "@mui/icons-material/LinkedIn";
import "./Login.css";
import React from "react";

function Login() {
	return (
		<div class="body">
			<div class="container" id="container">
				<div class="form-container sign-up">
					<form>
						<h1>Create Account</h1>
						<div class="social-icons">
							<a href="#" class="icon">
								<GoogleIcon />
							</a>
							<a href="#" class="icon">
								<FacebookIcon />
							</a>
							<a href="#" class="icon">
								<GitHubIcon />
							</a>
							<a href="#" class="icon">
								<LinkedInIcon />
							</a>
						</div>
						<span>or use your email for registration</span>
						<input type="text" placeholder="Name" />
						<input type="email" placeholder="Email" />
						<input type="password" placeholder="Password" />
						<a href="#">Forget Your Password</a>
						<button>Sign Up</button>
					</form>
				</div>
				<div class="form-container sign-in">
					<form>
						<h1>Sign In</h1>
						<div class="social-icons">
							<a href="#" class="icon">
								<GoogleIcon />
							</a>
							<a href="#" class="icon">
								<FacebookIcon />
							</a>
							<a href="#" class="icon">
								<GitHubIcon />
							</a>
							<a href="#" class="icon">
								<LinkedInIcon />
							</a>
						</div>
						<span>or use your email password</span>
						<input type="email" placeholder="Email" />
						<input type="password" placeholder="Password" />
						<a href="#">Forget Your Password</a>
						<button>Sign In</button>
					</form>
				</div>
				<div class="toggle-container">
					<div class="toggle">
						<div class="toggle-panel toggle-left">
							<h1>Welcome Back!</h1>
							<p>
								Enter your personal details to use all of site
								features
							</p>
							<button
								id="login"
								onClick={() =>
									document
										.getElementById("container")
										.classList.remove("active")
								}
							>
								Sign In
							</button>
						</div>
						<div class="toggle-panel toggle-right">
							<h1>Hello, Friend!</h1>
							<p>
								Register with your personal details to use all
								of site features
							</p>
							<button
								id="register"
								onClick={() =>
									document
										.getElementById("container")
										.classList.add("active")
								}
							>
								Sign Up
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	);
}

export default Login;
