import {
	Button,
	Card,
	CardActions,
	CardContent,
	Typography,
} from "@mui/material";
import React, { useEffect, useState } from "react";
import Navbar from "./Navbar";

function BookList() {
	const [bookList, setBookList] = useState([]);
	const [availableBooks, setAvailableBooks] = useState([]);

	// const changeIssueStatus = (bookId) => {
	// 	const responseData = null;
	// 	fetch("localhost:8082/api/v1/book/updateStatus", {
	// 		method: "PUT",
	// 		body: JSON.stringify({
	// 			bookId: bookId,
	// 		}),
	// 	})
	// 		.then((response) => response.json())
	// 		.then((data) => {
	// 			responseData = data;
	// 		})
	// 		.catch(
	// 			new Error(`Error: ${response.status} ${response.statusText}`)
	// 		);
	// 	return responseData;
	// };

	const fetchBookList = async () => {
		await fetch("http://localhost:8082/api/v1/book/retriveList")
			.then((response) => response.json())
			.then((data) => {
				setBookList(data.bookList);
				console.log(data.bookList);
				const filteredBooks = data.bookList.filter(
					(book) => !book.issued
				);
				setAvailableBooks(filteredBooks);
				console.log(filteredBooks);
			})
			.catch((error) => console.error("Error fetching user: ", error));
	};

	useEffect(() => {
		fetchBookList();
	}, []);

	return (
		<div class="min-h-screen bg-indigo-300">
			<Navbar />
			<div
				id="BookList"
				class="p-3 text-pretty text-center text-2xl text-slate-800 font-mono"
			>
				Available Books
			</div>
			<div id="cardContainer" class="flex-wrap grid grid-cols-6 gap-1">
				{availableBooks.map((book) => {
					console.log(book.isIssued);
					return (
						<div id="bookCard" class="place-content-center">
							<Card sx={{ maxWidth: 260 }}>
								<CardContent>
									<Typography gutterBottom variant="h6">
										{book.bookName}
									</Typography>
									<Typography
										variant="body2"
										color="text.secondary"
									>
										{book.authorName}
									</Typography>
								</CardContent>
								<CardActions>
									<Button
										variant="outlined"
										size="small"
										className="border border-slate-800"
									>
										Issue
									</Button>
								</CardActions>
							</Card>
						</div>
					);
				})}
			</div>
		</div>
	);
}

export default BookList;
