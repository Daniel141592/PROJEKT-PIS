import React, {useState} from 'react'
import s from "./ManagerPage.module.scss"
import {PATHS} from "../../config/paths";
import {TemplatePage} from "../../templates/TemplatePage";
import { FieldValues, useForm } from 'react-hook-form';
import { redirect, useNavigate } from 'react-router-dom';
import { sendRequestGET } from 'requests';


export const ManagerPage: React.FC = () => {
	const [history, setHistory] = useState([]);
	const [elastic, setElastic] = useState([]);
	const [raportLink, setRaportLink] = useState('');
	const [description, setDescription] = useState('');
	const [keyWords, setKeyWords] = useState('');
	const [idMagazine, setIdMagazine] = useState('');
	const [isModalOpen, setIsModalOpen] = useState(false);
	const [isElasticOpen, setIsElasticOpen] = useState(false);
	const [isRaportOpen, setIsRaportOpen] = useState(false);
	const {register, formState, handleSubmit} = useForm();
	const redirect = useNavigate();

	function Modal({ isOpen=false, children=null }) {
		if (!isOpen) {
		  return null;
		}

		if (history.length < 1) {
			return 	<p className={s.taskHist}>
						<br/>
						Brak wyników.
					</p>
		}

		return (
		  <div className="modal-overlay">
			<div className="modal-content">
				{children}
				{history.map((data: any) => {
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

	function Elastic({ isOpen=false, children=null }) {
		if (!isOpen) {
		  return null;
		}

		if (elastic.length < 1) {
			return 	<p className={s.taskHist}>
				 		<br/>
						Brak wyników.
					</p>
		}

		return (
		  <div className="modal-overlay">
			<div className="modal-content">
				{children}
				{elastic.map((data, idx) => {
					return <div>
								<p className={s.taskHist}>
									<br/>
									{data}
								</p>
								<hr className={s.hLine}></hr>
							</div>
				})}
			</div>
		  </div>
		);
	  }

	  function Raport({ isOpen=false, children=null }) {
		if (!isOpen) {
		  return null;
		}

		return (
		  <div className="modal-overlay">
			<div className="modal-content">
				{children}
				<a href={raportLink}>
					<div className={s.pdf}>
						<div className={s.img}></div>
						<p className={s.Link}>Raport_magazyn_{idMagazine}.pdf</p>
					</div>
				</a>
			</div>
		  </div>
		);
	  }

	function handleChangeMagazine(event: any) {
		setIdMagazine(event.target.value)
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
			'magazines/report/search?search=' + keyWords
		).then(async r => {
			let response = await r.json()
			setElastic(response)
		})
		
	}

	function sendReport(event: any){
		setIsRaportOpen(true)
		let tmp_link = 'http://localhost:8080/magazines/report/' + idMagazine
		setRaportLink(tmp_link)
	}

	return (
		<TemplatePage>
			<div className={s.fullDiv}>
				<div className={s.transDiv}>
					<h1 className={s.headerUp}>PANEL KIEROWNIKA</h1>
					<a href={PATHS.warehouse} className={s.link}>
						<button className={s.button}>Magazyny</button>
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
						<Modal isOpen={isModalOpen}></Modal>
					</form>
				</div>
				<h1 className={s.headerTask}>Raport z magazynu:</h1>
				<div className={s.mainDiv}>
					<form onSubmit={handleSubmit(sendReport)}>
						<label>
							<input placeholder='Id magazynu' onChange={handleChangeMagazine} className={s.input}/>
						</label>
						<button type="submit" className={s.button2}>Generuj</button>
						<Raport isOpen={isRaportOpen}></Raport>
					</form>
				</div>
				<h1 className={s.headerTask}>Wyszukiwanie pełnotekstowe:</h1>
				<div className={s.mainDiv}>
					<form onSubmit={handleSubmit(sendElastic)}>
						<label>
							<input placeholder='Słowa kluczowe' onChange={handleChangeDescriptionElastic} className={s.input}/>
						</label>
						<button type="submit" className={s.button2}>Szukaj</button>
						<Elastic isOpen={isElasticOpen}></Elastic>
					</form>
				</div>
				<h1><br></br></h1>
			</div>
		</TemplatePage>
	)
}