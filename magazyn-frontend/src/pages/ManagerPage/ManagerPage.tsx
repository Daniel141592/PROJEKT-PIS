import React, {useState} from 'react'
import s from "./ManagerPage.module.scss"
import {PATHS} from "../../config/paths";
import {TemplatePage} from "../../templates/TemplatePage";
import { FieldValues, useForm } from 'react-hook-form';
import { redirect } from 'react-router-dom';
import { sendRequestGET } from 'requests';


export const ManagerPage: React.FC = () => {
	const [history, setHistory] = useState([]);
	const [description, setDescription] = useState('');
	const [keyWords, setKeyWords] = useState('');
	const {register, formState, handleSubmit} = useForm();
	const [isModalOpen, setIsModalOpen] = useState(false);
	const [isElasticOpen, setIsElasticOpen] = useState(false);

	function Modal({ isOpen, onClose, children }) {
		if (!isOpen) {
		  return null;
		}

		return (
		  <div className="modal-overlay">
			<div className="modal-content">
				{children}
				{history.map((data, idx) => {
					return <div>
								<p className={s.taskHist}>
									<br/>
									Id: {data.id} <br/>
									Nazwa:  {data.name} <br/>
									Opis: {data.description} <br/>
									Data modyfikacji: {data.modifyDate} <br/>
									Kierownik zadania: {data.issuingManager.name} {data.issuingManager.surname}
								</p>
								<hr className={s.hLine}></hr>
							</div>
				})}
			</div>
		  </div>
		);
	  }

	  function Elastic({ isOpen, onClose, children }) {
		if (!isOpen) {
		  return null;
		}

		return (
		  <div className="modal-overlay">
			<div className="modal-content">
				{children}
			</div>
		  </div>
		);
	  }


	function handleChangeDescription(event: any) {
		setDescription(event.target.value)
	}

	function handleChangeDescriptionElastic(event: any) {
		setKeyWords(event.target.value)
	}

	function sendHistoryRequest(event: any){
		setIsModalOpen(true)

		let response = sendRequestGET(
			'issueHistories/contains?desc=' + description
		).then(async r => {
			let response = await r.json()
			setHistory(response)
		})
	}

	function sendElastic(event: any){
		setIsElasticOpen(true)

		let response = sendRequestGET(
			'issueHistories/contains?desc=' + keyWords
		).then(async r => {
			let response = await r.json()
			setKeyWords(response)
		})
	}

	return (
		<TemplatePage>
			<div className={s.fullDiv}>
				<div className={s.transDiv}>
					<h1 className={s.headerUp}>PANEL KIEROWNIKA</h1>
					<a href={PATHS.warehouse} className={s.link}>
						<button className={s.button}>Magazyn</button>
					</a>
				</div>
			</div>
			<div className={s.Panel}>
				<h1 className={s.headerTask}>Nowe zadanie:</h1>
				<div className={s.mainDiv}>
					<form>
						<label>
							<input placeholder='Nazwa pracownika' className={s.input2}/>
							<input placeholder='Nazwa zadania' className={s.input4}/>
							<input placeholder='Opis' className={s.input3}/>
							<input placeholder='Status' className={s.input}/>
						</label>
						<button type="submit" className={s.button2}>Stwórz</button>
					</form>
				</div>
				<h1 className={s.headerTask}>Historia zadań:</h1>
				<div className={s.mainDiv}>
					<form onSubmit={handleSubmit(sendHistoryRequest)}>
						<label>
							<input placeholder='Opis' onChange={handleChangeDescription} className={s.input}/>
						</label>
						<button type="submit" className={s.button2}>Generuj</button>
						<Modal isOpen={isModalOpen} onRequestClose={() => setIsModalOpen(false)}>
							{/* <p className={s.headerTaskSmall}> Zadania: </p> */}
						</Modal>
					</form>
				</div>
				<h1 className={s.headerTask}>Raport z magazynu:</h1>
				<div className={s.mainDiv}>
					<form>
						<label>
							<input placeholder='Nazwa magazynu' className={s.input}/>
						</label>
						<button type="submit" className={s.button2}>Generuj</button>
					</form>
				</div>
				<h1 className={s.headerTask}>Wyszukiwanie pełnotekstowe:</h1>
				<div className={s.mainDiv}>
					<form onSubmit={handleSubmit(sendElastic)}>
						<label>
							<input placeholder='Słowa kluczowe' onChange={handleChangeDescriptionElastic} className={s.input}/>
						</label>
						<button type="submit" className={s.button2}>Szukaj</button>
						<Elastic isOpen={isElasticOpen} onRequestClose={() => setIsElasticOpen(false)}>
							<p className={s.headerTaskSmall}> CHUJ JEBANY W DUPE ELASTIC </p>
						</Elastic>
					</form>
				</div>
				<h1><br></br></h1>
			</div>
		</TemplatePage>
	)
}