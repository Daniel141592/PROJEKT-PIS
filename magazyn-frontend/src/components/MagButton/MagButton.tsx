import React from 'react'
import s from "./MagButton.module.scss"
import {MagButtonProps} from "./MagButton.types";
import clsx from "clsx";

export const MagButton: React.FC<MagButtonProps> = ({
		children,
		className,
		variant = "default",
		...props
	}) => {
	return (
		<button className={clsx(
			s.container,
			s["variant-" + variant],
		)}>
			{children}
		</button>
	)
}
