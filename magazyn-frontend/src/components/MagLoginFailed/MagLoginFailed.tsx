import s from "./MagLoginfailed.module.scss"

export function MagLoginFailed({ isOpen=false, children=null }) {
	if (!isOpen) {
		return null;
	  }

	return (
		<div className="modal-overlay">
			<div className="modal-content">
				{children}
				<div>
					<p> <b> NIEPOPRAWY LOGIN LUB HASŁO </b> </p>
				</div>
			</div>
		</div>
	);
}
