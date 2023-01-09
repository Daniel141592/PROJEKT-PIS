import React, {useEffect, useState} from 'react'
import reactLogo from 'assets/react.svg'
import s from "./WarehousePage.module.scss"
import clsx from "clsx";
import {MagTask} from "../../components/MagTask";
import {MagLinkButton} from '../../components/MagLinkButton';
import {PATHS} from "../../config/paths";
import {TemplatePage} from "../../templates/TemplatePage";
import { MagButton } from 'components/MagButton';
import {sendRequestGET} from 'requests';


export const WarehousePage: React.FC = () => {
	const [magazines, setMagazines] = useState([]);

	useEffect(() => {
		sendRequestGET(
			'magazines/all'
		).then(async r => {
			let response = await r.json()
			setMagazines(response)
			console.log(magazines)
		})
	}, []);

	return (
		<TemplatePage>
			<div className={s.fullDiv}>
				<div className={s.transDiv}>
					<h1 className={s.headerUp}>MAGAZYN</h1>
				</div>
			</div>
			<div className={s.Panel}>
				<h1 className={s.headerTask}>Nasze magazyny:</h1>
				{magazines.map((item1, index1) => (
					<div className={s.mainDiv}>
						<div className={s.header}>
							<h1 className={s.headerTask2}>{item1.name}</h1>
							<h1 className={s.headerTask4}>Wymiary: {item1.length}m x {item1.width}m</h1>
						</div>
						<h1 className={s.headerTask3}>Sekcje: </h1>
						{item1.sections.map((item2, index2) => (
							<p className={s.Task}> {item2.name}</p>
						))}
						<h1><br/></h1>
					</div>
				))}
				<h1><br/></h1>
			</div>
		</TemplatePage>
	)
}