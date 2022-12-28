import React from 'react'
import ReactDOM from 'react-dom/client'
import 'styles/app.scss'
import {RouterProvider} from "react-router-dom";
import {routes} from "./config/routes";
import {QueryClient, QueryClientProvider} from "react-query";
import {UserContextProvider} from "./context/UserContext";

const queryClient = new QueryClient({
	defaultOptions: {
		queries: {
			refetchOnWindowFocus: false,
			refetchOnReconnect: false,
			retry: false,
		},
	},
});

ReactDOM.createRoot(document.getElementById('root') as HTMLElement).render(
	<React.StrictMode>
		<UserContextProvider>
			<QueryClientProvider client={queryClient}>
				<RouterProvider router={routes}/>
			</QueryClientProvider>
		</UserContextProvider>
	</React.StrictMode>,
)
