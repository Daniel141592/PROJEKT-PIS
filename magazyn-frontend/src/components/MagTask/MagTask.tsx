import React, { useState } from 'react';
import s from "./MagTask.module.scss"
import {MagButton} from 'components/MagButton';
import {MagInput} from 'components/MagInput';

export function MagTask(props: any) {
	const [status, setStatus] = useState(props.status);
	const [newStatus, setNewStatus] = useState('');
  
	function handleChange(event: any) {
	  setNewStatus(event.target.value);
	}
  
	function handleSubmit(event: any) {
	  event.preventDefault();
	  setStatus(newStatus);
	}
  
	return (
	  <div className={s.mainDiv}>
		<div className={s.textDiv}> Nazwa zadania: {props.name} </div>
		<div className={s.textDiv}> Opis zadania: {props.description} </div>
		<div className={s.textDiv}> Status: {status} </div>
		<form onSubmit={handleSubmit}>
		  <label>
			<input value={newStatus} onChange={handleChange} className={s.input}/>
		  </label>
		  <button type="submit" className={s.button}>Aktualizuj</button>
		</form>
	  </div>
	);
}
  

