import { Route, BrowserRouter as Router, Routes } from "react-router-dom";
import "./App.css";
import BookList from "./components/BookList";
import Login from "./components/Login";

function App() {
	return (
		<Router>
			<Routes>
				<Route path="/login" element={<Login />} />
				<Route exact path="/books" element={<BookList />} />
			</Routes>
		</Router>
	);
}

export default App;
