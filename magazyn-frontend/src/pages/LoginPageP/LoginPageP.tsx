import React, { useState } from 'react'
import {TemplatePage} from "templates/TemplatePage";
import s from "./LoginPageP.module.scss"
import {MagButton} from "components/MagButton";
import {MagLoginFailed} from "components/MagLoginFailed";
import {PATHS} from "config/paths";
import {MagInput} from "components/MagInput";
import {FieldValues, useForm} from "react-hook-form";
import {Link, useNavigate} from "react-router-dom";
import {sendRequestPOST} from 'requests';


export const LoginPageP: React.FC = () => {
	const [isFailOpen, setIsFailOpen] = useState(false);
	const redirect = useNavigate();
	const {register, handleSubmit} = useForm();

	async function sendLoginRequest(data: FieldValues){
		let response = sendRequestPOST(
			{"username": data.UserName, "password": data.Password},
			'employees/login'
		).then(async r => {
			let response = await r.json()

			if (response.success) {
				redirect(PATHS.employee)
				localStorage.setItem("token", response.token);
			}
			else {
				setIsFailOpen(true)
			}
		})
	}

	return (
		<TemplatePage>
			<div className={s.fullDiv}>
				<div className={s.transDiv}>
					<h1 className={s.headerUp}>LOGOWANIE</h1>
					<h3 className={s.headerDown}>PRACOWNIK</h3>
					<form className={s.formContainer} onSubmit={handleSubmit(sendLoginRequest)}>
						<div className={s.inputContainer}>
							<MagInput placeholder="Nazwa użytkownika" {...register("UserName")}/>
							<MagInput type="password" placeholder="Hasło" {...register("Password")}/>
							<MagLoginFailed isOpen={isFailOpen}></MagLoginFailed>
						</div>
						<MagButton type="submit">Zaloguj się</MagButton>
						<p className={s.signInCaption}>
							Nie masz konta?{" "}
							<Link to={PATHS.signup}>Zarejestruj się</Link>
						</p>
					</form>
				</div>
			</div>
		</TemplatePage>
	)
}

