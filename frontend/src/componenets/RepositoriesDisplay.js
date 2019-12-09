import React from 'react';
import ReposList from './ReposList';

const RepositoriesDisplay = props => {
	return (
		<>
			<ReposList
				repos={props.repos}
				first={!props.first ? 0 : props.first}
				last={!props.last ? props.repos.length : props.last}
			/>
			<h3> {!props.repos.length ? 'Nothing to show' : ''} </h3>
		</>
	);
};

export default RepositoriesDisplay;
