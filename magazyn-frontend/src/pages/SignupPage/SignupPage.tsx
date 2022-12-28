import React from 'react'
import {TemplatePage} from "../../templates/TemplatePage";
import s from "./SignupPage.module.scss"
import {MagButton} from "../../components/MagButton";
import {Link} from "react-router-dom";
import {PATHS} from "../../config/paths";
import {MagInput} from "../../components/MagInput";

export const SignupPage: React.FC = () => {


	return (
		<TemplatePage>
			<div className={s.fullDiv}>
				<div className={s.transDiv}>
					<h1 className={s.headerUp}>REJESTRACJA</h1>
					<div className={s.formContainer}>
						<div className={s.inputContainer}>
							<MagInput placeholder="Email"/>
							<MagInput placeholder="Nazwa użytkownika"/>
							<MagInput type="password" placeholder="Hasło"/>
							<MagInput type="password" placeholder="Powtórz hasło"/>
						</div>
						<MagButton>Zarejestruj się</MagButton>
					</div>
				</div>
			</div>
		</TemplatePage>
	)
}
