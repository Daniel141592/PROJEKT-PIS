import React from 'react'
import {TemplatePage} from "templates/TemplatePage";
import s from "./LoginPageP.module.scss"
import {MagButton} from "components/MagButton";
import {Link, useNavigate} from "react-router-dom";
import {PATHS} from "config/paths";
import {MagInput} from "components/MagInput";
import {FieldValues, useForm} from "react-hook-form";
import {LOCAL_STORAGE_CONFIG} from "config/localStorageConfig";
import {useMutation} from "react-query";
import {useUserContext} from "context/UserContext";

export const LoginPageP: React.FC = () => {

	const redirect = useNavigate();
	const {register, formState, handleSubmit} = useForm();
	const {setIsLoggedIn} = useUserContext();

	function sendLoginRequest(data: FieldValues) {
		let url = "http://localhost:8080/account/login";
		fetch(url, {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
				// 'Authorization': `${cookies.get("JWTTOKEN")}`
			},
			body: JSON.stringify({"username": data.UserName, "password": data.Password})
		}).then(async r => {
			console.log(await r.json());
		});
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
