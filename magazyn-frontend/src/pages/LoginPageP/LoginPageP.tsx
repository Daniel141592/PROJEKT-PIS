import React from 'react'
import {TemplatePage} from "templates/TemplatePage";
import s from "./LoginPageP.module.scss"
import {MagButton} from "components/MagButton";
import {MagLinkButton} from "components/MagLinkButton";
import {PATHS} from "config/paths";
import {MagInput} from "components/MagInput";
import {FieldValues, useForm} from "react-hook-form";
import {LOCAL_STORAGE_CONFIG} from "config/localStorageConfig";
import {useMutation} from "react-query";
import {useUserContext} from "context/UserContext";
import {Link, Navigate, useNavigate} from "react-router-dom";
import {sendRequestPOST} from 'requests';


export const LoginPageP: React.FC = () => {

	const redirect = useNavigate();
	const {register, formState, handleSubmit} = useForm();
	const {setIsLoggedIn} = useUserContext();

	async function sendLoginRequest(data: FieldValues){
		let response = sendRequestPOST(
			{"username": data.UserName, "password": data.Password},
			'employees/login'
		).then(async r => {
			let response = await r.json()

			if (response.success) {
				redirect(PATHS.employee)
				localStorage.setItem("token", JSON.stringify((response.token)));
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
