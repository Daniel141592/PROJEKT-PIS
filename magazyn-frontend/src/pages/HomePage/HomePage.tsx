import React, {useState} from 'react'
import reactLogo from 'assets/react.svg'
import s from "./HomePage.module.scss"
import clsx from "clsx";
import {MagButton} from "../../components/MagButton";
import {MagLinkButton} from "../../components/MagLinkButton";
import {PATHS} from "../../config/paths";
import {TemplatePage} from "../../templates/TemplatePage";

export const HomePage: React.FC = () => {

	return (
		<TemplatePage>
			<div className={s.pageDiv}>
				<div className={s.transBox}>
					<div className={s.buttonContainer}>
						<h1 className={s.headerUp}>SYSTEM KOMPLEKSOWEGO</h1>
						<h1></h1>
						<h1 className={s.headerUp}>ZARZĄDZANIA MAGAZYNEM</h1>
						<h1></h1>
						<h1></h1>
						<h1></h1>
						<h1></h1>
						<h3 className={s.headerDown}>Razem zmieścimy więcej</h3>
						<h3></h3>
						<MagLinkButton href={PATHS.loginP}>Pracownik</MagLinkButton>
						<MagLinkButton href={PATHS.loginK}>Kierownik magazynu</MagLinkButton>
					</div>
				</div>
			</div>
		</TemplatePage>
	)
}
