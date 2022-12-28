import React from "react";

export type MagButtonProps = React.ButtonHTMLAttributes<HTMLButtonElement> & {
	className?: string;
	variant?: "default" | "small";
};
