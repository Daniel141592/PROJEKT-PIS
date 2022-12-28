import {PATHS} from "./paths";

export enum HEADER_VARIANT {
	default = 'default',
	defaultWithBack = 'defaultWithBack',
	chat = 'chat',
}

export const headerConfig = (path: string): HEADER_VARIANT => {
	const {home, login, signup, chat} = PATHS;
	switch (path) {
		case home:
			return HEADER_VARIANT.default;
		case login:
			return HEADER_VARIANT.defaultWithBack;
		case signup:
			return HEADER_VARIANT.defaultWithBack;
		case chat:
			return HEADER_VARIANT.chat;
		default:
			return HEADER_VARIANT.default;

	}
}