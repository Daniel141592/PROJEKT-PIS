import React, {useEffect, useState} from 'react'
import s from "./WarehousePage.module.scss"
import {TemplatePage} from "../../templates/TemplatePage";
import {sendRequestGET} from 'requests';
import {API_URL} from "../../config/apiUrl";

const BASE_URL = API_URL != "" ? API_URL : 'http://localhost:8080/';

export const WarehousePage: React.FC = () => {
	const [magazines, setMagazines] = useState([]);

	useEffect(() => {
		sendRequestGET(
			'magazines/all'
		).then(async r => {
			let response = await r.json()
			setMagazines(response)
		})
	}, []);

	return (
		<TemplatePage>
			<div className={s.fullDiv}>
				<div className={s.transDiv}>
					<h1 className={s.headerUp}>MAGAZYNY</h1>
				</div>
			</div>
			<div className={s.Panel}>
				<h1 className={s.headerTask}>Nasze magazyny:</h1>
				{magazines.map((item1: any) => (
					<div className={s.mainDiv}>
						<div className={s.header}>
							<h1 className={s.headerTask2}>{item1.name}</h1>
							<h1 className={s.headerTask4}>Wymiary: {item1.length}m x {item1.width}m</h1>
						</div>
						<div className={s.header2}>
							<h1 className={s.headerTask3}>Sekcje: </h1>
							<div>
								{item1.sections.map((item2: any) => (
									<p className={s.Task}> {item2.name}</p>
								))}
							</div>
							<a href={BASE_URL+'magazines/report/' + item1.id}>
								<button type="submit" className={s.button2}>Pobierz raport</button>
							</a>
						</div>
					</div>
				))}
				<h1><br/></h1>
			</div>
		</TemplatePage>
	)
}
