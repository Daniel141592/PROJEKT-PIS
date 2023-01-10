import React, {useState} from 'react'
import s from "./ManagerPage.module.scss"
import {PATHS} from "../../config/paths";
import {TemplatePage} from "../../templates/TemplatePage";
import {useForm} from 'react-hook-form';
import {sendRequestGET, sendRequestPOST} from 'requests';
import {API_URL} from "../../config/apiUrl";

const BASE_URL = API_URL != "" ? API_URL : 'http://localhost:8080/';


export const ManagerPage: React.FC = () => {
	// Query results
	const [history, setHistory] = useState([]);
	const [elastic, setElastic] = useState([]);
	const [raportLink, setRaportLink] = useState('');
	const [issueResult, setIssueResult] = useState([]);
	const [sectionResult, setSectionResult] = useState([]);
	const [productResult, setProductResult] = useState([]);

	// Inputs
	const [description, setDescription] = useState('');
	const [keyWords, setKeyWords] = useState('');
	const [idMagazine, setIdMagazine] = useState('');

	const [issueEmployeeId, setIssueEmployeeId] = useState('');
	const [issueManagerId, setIssueManagerId] = useState('');
	const [issueName, setIssueName] = useState('');
	const [issueDescription, setIssueDescription] = useState('');
	const [issueStatus, setIssueStatus] = useState('');

	const [sectionMagazineId, setSectionMagazineId] = useState('');
	const [sectionProductId, setSectionProductId] = useState('');
	const [sectionName, setSectionName] = useState('');
	const [sectionAmount, setSectionAmount] = useState('');
	const [sectionLength, setSectionLength] = useState('');
	const [sectionWidth, setSectionWidth] = useState('');
	const [sectionX, setSectionX] = useState('');
	const [sectionY, setSectionY] = useState('');

	const [productName, setProductName] = useState('');
	const [productLength, setProductLength] = useState('');
	const [productWidth, setProductWidth] = useState('');

	// Modal opens
	const [isModalOpen, setIsModalOpen] = useState(false);
	const [isElasticOpen, setIsElasticOpen] = useState(false);
	const [isRaportOpen, setIsRaportOpen] = useState(false);
	const [isIssueOpen, setIsIssueOpen] = useState(false);
	const [isSectionOpen, setIsSectionOpen] = useState(false);
	const [isProductOpen, setIsProductOpen] = useState(false);

	//Others
	const {handleSubmit} = useForm();

	// Modal Popups
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
					return (
						<div>
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
					)})}
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

		if (idMagazine == '') {
			return (
				<div className="modal-overlay">
				  <div className="modal-content">
					  {children}
					  <div>
						  <br/>
						  <p className={s.taskHist}> Błąd! Podaj numer magazynu. </p>
					  </div>
					  <hr className={s.hLine}></hr>
				  </div>
				</div>
			);
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

	function Issue({ isOpen=false, children=null }) {
		if (!isOpen) {
		  	return null;
		}

		if (issueName == '' || issueDescription == '' || issueEmployeeId == '' || issueManagerId == '' || issueStatus == '') {
			return (
				<div className="modal-overlay">
				  <div className="modal-content">
					  {children}
					  <div>
						  <br/>
						  <p className={s.taskHist}> Błąd! Niektóre pola są puste. </p>
					  </div>
					  <hr className={s.hLine}></hr>
				  </div>
				</div>
			);
		}

		return (
		  <div className="modal-overlay">
			<div className="modal-content">
				{children}
				<div>
					<br/>
					<p className={s.taskHist}> Utworzono nowe zadanie. </p>
				</div>
				<hr className={s.hLine}></hr>
			</div>
		  </div>
		);
	}

	function Product({ isOpen=false, children=null }) {
		if (!isOpen) {
		  	return null;
		}

		if (productName == '' ||
			productLength == '' ||
			productWidth == '') {
			return (
				<div className="modal-overlay">
				  <div className="modal-content">
					  {children}
					  <div>
						  <br/>
						  <p className={s.taskHist}> Podczas tworzenia produktu wystąpił błąd! Spróbuj ponownie. </p>
					  </div>
					  <hr className={s.hLine}></hr>
				  </div>
				</div>
			);
		}

		return (
		  <div className="modal-overlay">
			<div className="modal-content">
				{children}
				<div>
					<br/>
					<p className={s.taskHist}> Dodano produkt. </p>
				</div>
				<hr className={s.hLine}></hr>
			</div>
		  </div>
		);
	}

	function Section({ isOpen=false, children=null }) {
		if (!isOpen) {
		  	return null;
		}

		if (sectionName == '' ||
		    sectionAmount == '' ||
			sectionLength == '' ||
			sectionMagazineId == '' ||
			sectionProductId == '' ||
			sectionWidth == '' ||
			sectionX == '' ||
			sectionY == '') {
			return (
				<div className="modal-overlay">
				  <div className="modal-content">
					  {children}
					  <div>
						  <br/>
						  <p className={s.taskHist}> Podczas tworzenia sekcji wystąpił błąd! Spróbuj ponownie. </p>
					  </div>
					  <hr className={s.hLine}></hr>
				  </div>
				</div>
			);
		}

		return <div className="modal-overlay">
					<div className="modal-content">
						{children}
						<div>
							<br/>
							<p className={s.taskHist}> Dodano sekcję. </p>
						</div>
						<hr className={s.hLine}></hr>
					</div>
				</div>
	}

	// Input handlers

	function handleChangeMagazine(event: any) {
		setIdMagazine(event.target.value)
	}

	function handleChangeDescription(event: any) {
		setDescription(event.target.value)
	}

	function handleChangeDescriptionElastic(event: any) {
		setKeyWords(event.target.value)
	}

	function handleChangeIssueEmployeeId(event: any) {
		setIssueEmployeeId(event.target.value)
	}

	function handleChangeIssueManagerId(event: any) {
		setIssueManagerId(event.target.value)
	}

	function handleChangeIssueName(event: any) {
		setIssueName(event.target.value)
	}

	function handleChangeIssueDescription(event: any) {
		setIssueDescription(event.target.value)
	}

	function handleChangeIssueStatus(event: any) {
		setIssueStatus(event.target.value)
	}

	function handleChangeSectionMagazineID(event: any) {
		setSectionMagazineId(event.target.value)
	}

	function handleChangeSectionName(event: any) {
		setSectionName(event.target.value)
	}

	function handleChangeSectionProduktID(event: any) {
		setSectionProductId(event.target.value)
	}

	function handleChangeSectionAmount(event: any) {
		setSectionAmount(event.target.value)
	}

	function handleChangeSectionLength(event: any) {
		setSectionLength(event.target.value)
	}

	function handleChangeSectionWidth(event: any) {
		setSectionWidth(event.target.value)
	}

	function handleChangeSectionX(event: any) {
		setSectionX(event.target.value)
	}

	function handleChangeSectionY(event: any) {
		setSectionY(event.target.value)
	}

	function handleChangeProductName(event: any) {
		setProductName(event.target.value)
	}

	function handleChangeProductLength(event: any) {
		setProductLength(event.target.value)
	}

	function handleChangeProductWidth(event: any) {
		setProductWidth(event.target.value)
	}

	// Send requests
	function sendHistoryRequest(event: any){
		setIsModalOpen(true)

		let response = sendRequestGET(
			'issuehistories/contains?desc=' + description
		).then(async r => {
			let response = await r.json()
			setHistory(response)
			console.log(response)
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
		let tmp_link = BASE_URL+'magazines/report/' + idMagazine
		setRaportLink(tmp_link)
	}

	function sendIssue(event: any){
		setIsIssueOpen(true)

		let response = sendRequestPOST(
			{
				"name": issueName,
				"description": issueDescription,
				"issuingManager": parseInt(issueManagerId),
				"issuedEmployee": parseInt(issueEmployeeId),
				"status": issueStatus
			},
			'issues/add'
		).then(async r => {
			let response = await r.json()
			setIssueResult(response)
		})
	}

	function sendSection(event: any){
		setIsSectionOpen(true)

		let response = sendRequestPOST(
			{
				"magazine": parseInt(sectionMagazineId),
				"product": parseInt(sectionProductId),
				"name": sectionName,
				"amount": parseInt(sectionAmount),
				"length": parseInt(sectionLength),
				"width": parseInt(sectionWidth),
				"bottomLeftPointX": parseInt(sectionX),
				"bottomLeftPointY": parseInt(sectionY)
			},
			'sections/add'
		).then(async r => {
			let response = await r.json()
			setSectionResult(response)
		})
	}

	function sendProduct(event: any){
		setIsProductOpen(true)

		let response = sendRequestPOST(
			{
				"name": productName,
				"length": parseInt(productLength),
				"width": parseInt(productWidth),
				"stackSize": parseInt('64')
			},
			'products/add'
		).then(async r => {
			let response = await r.json()
			setProductResult(response)
			console.log(response)
		})
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
					<form onSubmit={handleSubmit(sendIssue)}>
						<label>
							<input placeholder='Id pracownika' onChange={handleChangeIssueEmployeeId} className={s.input2}/>
							<input placeholder='Id kierownika' onChange={handleChangeIssueManagerId} className={s.input4}/>
							<input placeholder='Nazwa zadania' onChange={handleChangeIssueName} className={s.input2}/>
							<input placeholder='Opis zadania' onChange={handleChangeIssueDescription} className={s.input4}/>
							<input placeholder='Status' onChange={handleChangeIssueStatus} className={s.input}/>
						</label>
						<button type="submit" className={s.button2}>Stwórz</button>
						<Issue isOpen={isIssueOpen}></Issue>
					</form>
				</div>

				<h1 className={s.headerTask}>Nowy produkt:</h1>
				<div className={s.mainDiv}>
					<form onSubmit={handleSubmit(sendProduct)}>
						<label>
							<input placeholder='Nazwa produktu' onChange={handleChangeProductName} className={s.input2}/>
							<input placeholder='Długość produktu' onChange={handleChangeProductLength} className={s.input4}/>
							<input placeholder='Szerokość produktu' onChange={handleChangeProductWidth} className={s.input}/>
						</label>
						<button type="submit" className={s.button2}>Dodaj</button>
						<Product isOpen={isProductOpen}></Product>
					</form>
				</div>

				<h1 className={s.headerTask}>Nowa sekcja:</h1>
				<div className={s.mainDiv}>
					<form onSubmit={handleSubmit(sendSection)}>
						<label>
							<input placeholder='Nazwa sekcji' onChange={handleChangeSectionName} className={s.input2}/>
							<input placeholder='Id magazynu' onChange={handleChangeSectionMagazineID} className={s.input4}/>
							<input placeholder='Id produktu' onChange={handleChangeSectionProduktID} className={s.input2}/>
							<input placeholder='Ilość produktu' onChange={handleChangeSectionAmount} className={s.input4}/>
							<input placeholder='Szerokość sekcji (m)' onChange={handleChangeSectionWidth} className={s.input2}/>
							<input placeholder='Długość sekcji (m)' onChange={handleChangeSectionLength} className={s.input4}/>
							<input placeholder='Współrzędna X' onChange={handleChangeSectionX} className={s.input5}/>
							<input placeholder='Współrzędna Y' onChange={handleChangeSectionY} className={s.input6}/>
						</label>
						<button type="submit" className={s.button2}>Dodaj</button>
						<Section isOpen={isSectionOpen}></Section>
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
