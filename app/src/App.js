import { Route, BrowserRouter as Router, Routes } from "react-router-dom";
import "./App.css";
import BookList from "./components/BookList";
import Login from "./components/Login";
import Home from "./components/Home";

function App() {
	return (
		<Router>
			<Routes>
				<Route path="/login" element={<Login />} />
				<Route path="/" element={<Home />} />
				<Route path="/library" element={<BookList />} />
			</Routes>
		</Router>
	);
}

export default App;
