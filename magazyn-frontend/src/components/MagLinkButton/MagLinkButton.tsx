import React from 'react'
import {MagLinkButtonProps} from "./MagLinkButton.types";
import {Link} from "react-router-dom";
import {MagButton} from "../MagButton";
import clsx from "clsx";
import s from "./MagLinkButton.module.scss";

export const MagLinkButton: React.FC<MagLinkButtonProps> = (
	{
		children,
		className,
		buttonClassName,
		href,
		variant = 'default',
		...props
	}) => {

	return (
		<Link className={clsx(className, s["variant-" + variant])} to={href}>
			<MagButton
				className={buttonClassName}
				variant={variant}
				{...props}
			>
				{children}
			</MagButton>
		</Link>
	)
}
