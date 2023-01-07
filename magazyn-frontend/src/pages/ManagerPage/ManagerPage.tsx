import React, {useState} from 'react'
import reactLogo from 'assets/react.svg'
import s from "./ManagerPage.module.scss"
import clsx from "clsx";
import {MagTask} from "../../components/MagTask";
import {MagLinkButton} from '../../components/MagLinkButton';
import {PATHS} from "../../config/paths";
import {TemplatePage} from "../../templates/TemplatePage";

export const ManagerPage: React.FC = () => {

	return (
		<TemplatePage>
			<div className={s.fullDiv}>
				<div className={s.transDiv}>
					<h1 className={s.headerUp}>PANEL KIEROWNIKA</h1>
					<a href={PATHS.warehouse} className={s.link}>
						<button className={s.button}>Magazyn</button>
					</a>
				</div>
			</div>
			<div className={s.Panel}>
				<h1 className={s.headerTask}>Nowe zadanie:</h1>
				<div className={s.mainDiv}>
					<form>
						<label>
							<input placeholder='Nazwa pracownika' className={s.input2}/>
							<input placeholder='Nazwa zadania' className={s.input4}/>
							<input placeholder='Opis' className={s.input3}/>
							<input placeholder='Status' className={s.input}/>
						</label>
						<button type="submit" className={s.button2}>Stwórz</button>
					</form>
				</div>
				<h1 className={s.headerTask}>Historia zadań:</h1>
				<div className={s.mainDiv}>
					<form>
						<label>
							<input placeholder='Nazwa pracownika' className={s.input}/>
						</label>
						<button type="submit" className={s.button2}>Generuj</button>
					</form>
				</div>
				<h1 className={s.headerTask}>Raport z magazynu:</h1>
				<div className={s.mainDiv}>
					<form>
						<label>
							<input placeholder='Nazwa magazynu' className={s.input}/>
						</label>
						<button type="submit" className={s.button2}>Generuj</button>
					</form>
				</div>
			</div>
		</TemplatePage>
	)
}