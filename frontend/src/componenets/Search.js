import React, { useState, useEffect } from 'react';
import axios from 'axios';

export default function Search(props) {
	const [minStarsCount, setMinStarsCount] = useState('');
	const [languages, setLanguages] = useState([]);
	const [language, setLanguage] = useState('');

	useEffect(() => {
		// CR: No error handling
		axios.get(`http://localhost:8080/languages/`).then(response => {
			const list = response.data.map(language => (
				<option key={language} value={language}>
					{language}
				</option>
			));

			setLanguages(list);
		});
	}, []);

	return (
		<div>
			Search:
			<div>
				<div>
					<label>Minimum Stars: </label>
					<input
						type="number"
						min="0"
						value={minStarsCount}
						onChange={e => setMinStarsCount(e.target.value)}
					/>
				</div>

				<div>
					<label> Languages: </label>
					<select value={language} onChange={e => setLanguage(e.target.value)}>
						{languages}
					</select>
				</div>
			</div>
			<button onClick={() => props.onClick({ minStarsCount, language })}>Search</button>
		</div>
	);
}
