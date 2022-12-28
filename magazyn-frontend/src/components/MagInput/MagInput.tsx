import React from 'react'
import s from "./MagInput.module.scss"
import {MagInputProps} from "./MagInput.types";
import clsx from "clsx";

export const MagInput = React.forwardRef<any, MagInputProps>((
	{
		className,
		variant = "primary",
		...props
	}, ref
) => {
	return (
		<label className={s.container}>
			<input
				className={clsx(s.input, s["variant-" + variant])}
				ref={ref}
				{...props}
			/>
		</label>
	)
})
