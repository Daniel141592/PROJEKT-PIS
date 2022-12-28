import React from "react";

export type MagInputProps = React.HTMLProps<HTMLInputElement> & {
	className?: string;
	variant?: "primary" | "chat";
};


