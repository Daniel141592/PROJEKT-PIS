import React, {useState} from 'react'
import reactLogo from 'assets/react.svg'
import s from "./EmployerPage.module.scss"
import clsx from "clsx";
import {MagButton} from "../../components/MagButton";
import {MagLinkButton} from "../../components/MagLinkButton";
import {PATHS} from "../../config/paths";
import {TemplatePage} from "../../templates/TemplatePage";

export const EmployerPage: React.FC = () => {

	return (
		<TemplatePage>
			<div className={s.fullDiv}>
				<div className={s.transDiv}>
					<h1 className={s.headerUp}>PANEL PRACOWNIKA</h1>
				</div>
			</div>
			<div className={s.Panel}>
				<h3 className={s.headerTask}>Dzisiejsze zadania: </h3>
			</div>
		</TemplatePage>
	)
}
