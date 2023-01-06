import React from 'react'
import {TemplatePage} from "templates/TemplatePage";
import s from "./LoginPageP.module.scss"
import {MagButton} from "components/MagButton";
import {MagLinkButton} from "components/MagLinkButton";
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
	const {setIsLoggedIn} = useUserContext()

	return (
		<TemplatePage>
			<div className={s.fullDiv}>
				<div className={s.transDiv}>
					<h1 className={s.headerUp}>LOGOWANIE</h1>
					<h3 className={s.headerDown}>PRACOWNIK</h3>
					<form className={s.formContainer}>
						<div className={s.inputContainer}>
							<MagInput placeholder="Nazwa użytkownika" {...register("UserName")}/>
							<MagInput type="password" placeholder="Hasło" {...register("Password")}/>
						</div>
						{/* <MagButton type="submit">Zaloguj się</MagButton> */}
						<MagLinkButton href={PATHS.employee}>Zaloguj się</MagLinkButton>
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
