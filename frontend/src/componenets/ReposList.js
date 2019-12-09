import React from 'react';
import { FaLockOpen, FaLock } from 'react-icons/fa';

function ReposList(props) {
	const repos = props.repos.slice(props.first, props.last).map(repo => <Repo item={repo} />);

	return (
		<div className="container">
			<div className="row">
				<div className="col">Full Name</div>
				<div className="col">Url</div>
				<div className="col">Forks</div>
				<div className="col">Is Private</div>
				<div className="col">Description</div>
			</div>
			{repos}
		</div>
	);
}

function Repo(props) {
	return (
		<div className="row">
			<div className="col">{props.item.full_name}</div>
			<a href={props.item.html_url} className="col">
				Link
			</a>
			<div className="col">{props.item.forks}</div>
			<div className="col">{props.item.private ? <FaLock /> : <FaLockOpen />}</div>
			<div className="col">{props.item.description}</div>
		</div>
	);
}

export default ReposList;
