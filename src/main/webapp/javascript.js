/**
 * 
 */
let buttons = document.getElementsByClassName("editBtn");
for(const elmt of buttons) {
	elmt.addEventListener("click", async function(event) {
    let id = event.target.getAttribute('data-id');
    let name = event.target.getAttribute('data-name');
    let first = event.target.getAttribute('data-first');
    let sex = event.target.getAttribute('data-sex');
    
    let request = await fetch("/tennis/listJoueur?id="+id+"&name="+name+"&first="+first+"&sex="+sex + "&mode=edit", {
		method: "post"
		}).then((response) =>{
			window.location.href = "http://localhost:8080/tennis/ajouterjoueur";
		})    
    });
}

let removeBtns = document.getElementsByClassName("remBtn");
for(const elmt of removeBtns) {
	elmt.addEventListener("click", async function(event) {
		let id = event.target.getAttribute('data-id');
		let name = event.target.getAttribute('data-name');
    	let first = event.target.getAttribute('data-first');
		if(confirm("Etes vous sûr de vouloir supprimer "+first +" "+name +" ?")) {
			let request = await fetch("/tennis/listJoueur?id="+id+"&mode=remove", {
			method: "post"
			}).then((response) =>{
				window.location.href = "http://localhost:8080/tennis/listJoueur";
			})
		}
		
	});
}

let buttonsTournoi = document.getElementsByClassName("editTournoiBtn");
for(const elmt of buttonsTournoi) {
	elmt.addEventListener("click", async function(event) {
    let id = event.target.getAttribute('data-id');
    let name = event.target.getAttribute('data-name');
    let annee = event.target.getAttribute('data-annee');
    let type = event.target.getAttribute('data-type');
    
    let request = await fetch("/tennis/listTournoi?id="+id+"&name="+name+"&annee="+annee+"&type="+type + "&mode=edit", {
		method: "post"
		}).then((response) =>{
			window.location.href = "http://localhost:8080/tennis/ajoutertournoi";
		})    
    });
}

let removeTournoiBtns = document.getElementsByClassName("remTournoiBtn");
for(const elmt of removeTournoiBtns) {
	elmt.addEventListener("click", async function(event) {
		let id = event.target.getAttribute('data-id');
		let annee = event.target.getAttribute('data-annee');
    	let name = event.target.getAttribute('data-name');
		if(confirm("Etes vous sûr de vouloir supprimer "+name +" "+annee +" ?")) {
			let request = await fetch("/tennis/listTournoi?id="+id+"&mode=remove", {
			method: "post"
			}).then((response) =>{
				window.location.href = "http://localhost:8080/tennis/listTournoi";
			})
		}
		
	});
}

let rows = document.getElementsByClassName("rowTournoi");
for(const elmt of rows) {
	elmt.addEventListener("click", async function(event) {
		let id = event.target.getAttribute('data-id');
	    let name = event.target.getAttribute('data-name');
	    let annee = event.target.getAttribute('data-annee');
	    let type = event.target.getAttribute('data-type');
	    let request = await fetch("/tennis/final?id="+id+"&name="+name+"&annee="+annee+"&type="+type, {
			method: "post"
			}).then((response) =>{
				window.location.href = "http://localhost:8080/tennis/final";
			})
	})
}

    