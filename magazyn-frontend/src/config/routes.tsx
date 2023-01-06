import React from "react";
import {createBrowserRouter} from "react-router-dom";
import {HomePage} from "../pages/HomePage";
import {PATHS} from "./paths";
import {LoginPageP} from "../pages/LoginPageP";
import {LoginPageK} from "../pages/LoginPageK";
import {SignupPage} from "../pages/SignupPage";
import {EmployeePage} from "../pages/EmployeePage";
import {WarehousePage} from "../pages/WarehousePage";

export const routes = createBrowserRouter([
	{
		path: PATHS.home,
		element: <HomePage/>,
	},
	{
		path: PATHS.loginP,
		element: <LoginPageP/>
	},
	{
		path: PATHS.loginK,
		element: <LoginPageK/>
	},
	{
		path: PATHS.signup,
		element: <SignupPage/>
	},
	{
		path: PATHS.employee,
		element: <EmployeePage/>
	},
	{
		path: PATHS.warehouse,
		element: <WarehousePage/>
	}

])
