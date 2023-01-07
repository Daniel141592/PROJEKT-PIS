import React, {useState} from 'react'
import reactLogo from 'assets/react.svg'
import s from "./EmployeePage.module.scss"
import clsx from "clsx";
import {MagTask} from "../../components/MagTask";
import {MagLinkButton} from '../../components/MagLinkButton';
import {PATHS} from "../../config/paths";
import {TemplatePage} from "../../templates/TemplatePage";
import {sendRequestGET} from 'requests';

export const EmployeePage: React.FC = () => {
	const [issues, setIssues] = useState([]);

	let response = sendRequestGET(
		'issues/all'
	).then(async r => {
		let response = await r.json()
		setIssues(response)
	})

	return (
		<TemplatePage>
			<div className={s.fullDiv}>
				<div className={s.transDiv}>
					<h1 className={s.headerUp}>PANEL PRACOWNIKA</h1>
					<a href={PATHS.warehouse} className={s.link}>
						<button className={s.button}>Magazyn</button>
					</a>
				</div>
			</div>
			<div className={s.Panel}>
				<h3 className={s.headerTask}>Dzisiejsze zadania: </h3>
				{issues.map((data, idx) => {
					return <MagTask name={data.name} description={data.description} status={data.status}/>
				})}
				{/* // <MagTask name='Skończyć ten jebany pis' description='TBD' status='Bliskie pierdolnięcia'/> */}
			</div>
		</TemplatePage>
	)
}