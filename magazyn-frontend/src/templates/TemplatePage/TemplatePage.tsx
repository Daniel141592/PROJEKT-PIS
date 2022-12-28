import React from 'react'
import s from "./TemplatePage.module.scss"
import {MagHeader} from "../../components/MagHeader";

export const TemplatePage: React.FC<React.PropsWithChildren> = ({children}) => {

	return (
		<>
			{/* <WeHeader/> */}
			<div className={s.container}>
				{children}
			</div>
		</>
	)
}
