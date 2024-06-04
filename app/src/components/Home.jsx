import React from "react";
import Navbar from "./Navbar";
import "./Home.css";

export default function Home() {
	return (
		<div>
			<div>
				<Navbar />
			</div>
			<div class="home-body">
				<h1 class="home-content">This is Home Page</h1>
			</div>
		</div>
	);
}
