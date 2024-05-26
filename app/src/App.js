import { Route, BrowserRouter as Router, Routes } from "react-router-dom";
import "./App.css";
import BookList from "./components/BookList";

function App() {
	return (
		<Router>
			<Routes>
				<Route exact path="/books" element={<BookList />} />
			</Routes>
		</Router>
	);
}

export default App;
