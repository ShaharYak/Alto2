import React, { useState } from 'react';
import './App.css';
import axios from 'axios';
import Search from './componenets/Search';
import RepositoriesDisplay from './componenets/RepositoriesDisplay';

function App() {
	const higherBound = 5;
	const [repositories, setRepositories] = useState([]);

	const onClickSearch = payload => {
		// CR: No error handling?
		axios.post(`http://localhost:8080/search/`, payload).then(response => setRepositories(response.data));
	};

	return (
		<div className="App">
			<Search onClick={onClickSearch} />
			<h2>{repositories.length ? 'Top Results:' : ''}</h2>
			<RepositoriesDisplay repos={repositories} last={higherBound} />

			<h2>{repositories.length ? 'More Results:' : ''}</h2>
			<RepositoriesDisplay repos={repositories} first={higherBound + 1} />
		</div>
	);
}

export default App;
