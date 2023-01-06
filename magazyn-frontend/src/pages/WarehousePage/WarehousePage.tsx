import React, {useState} from 'react'
import reactLogo from 'assets/react.svg'
import s from "./WarehousePage.module.scss"
import clsx from "clsx";
import {MagTask} from "../../components/MagTask";
import {MagLinkButton} from '../../components/MagLinkButton';
import {PATHS} from "../../config/paths";
import {TemplatePage} from "../../templates/TemplatePage";

export const WarehousePage: React.FC = () => {

	return (
		<TemplatePage>
			<div className={s.fullDiv}>
				<div className={s.transDiv}>
					<h1 className={s.headerUp}>MAGAZYN</h1>
				</div>
			</div>
			<div className={s.Panel}>
			</div>
		</TemplatePage>
	)
}