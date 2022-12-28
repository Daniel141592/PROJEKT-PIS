import React from "react";

export type MagIconButtonProps = React.ButtonHTMLAttributes<HTMLButtonElement> & {
	className?: string;
	theme?: "primary" | "transparent";
	icon: React.ReactNode;
};


