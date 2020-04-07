let userId = getUrlParameter('userId');
if (userId == null || userId == '') {
	userId = localStorage.getItem('userId');
	if (userId != null && userId != '') {
		window.location.href = '/?userId='+userId;
		// for debugging purposes
		// fetch('/savedPreferences?userId='+userId)
		//	.then(response => response.json())
		//	.then(json => console.log(json));
	}
}

if (userId != null && userId != '') {
	localStorage.setItem('userId', userId);
	document.getElementById('userId').value = userId;
}


let marsApiButtons = document.querySelectorAll("button[id*='marsApi']");

marsApiButtons.forEach(button => button.addEventListener('click', function() {
    	const buttonId = this.id;
    	const roverId = buttonId.split('marsApi')[1];
    	let apiData = document.getElementById('marsApiRoverData');
    	apiData.value = roverId;
    	document.getElementById('marsApiForm').submit();
    	}));

let marsRoverType = document.getElementById('marsApiRoverData').value;
highlightRover(marsRoverType);

function getUrlParameter(sParam) {
    var sPageURL = window.location.search.substring(1),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : decodeURIComponent(sParameterName[1]);
        }
    }
};

function highlightRover(rover) {
	if (rover == 'undefined' || rover == '') {
		rover = 'Curiosity';
	}
	document.getElementById('marsApi' + rover).classList.remove('btn-secondary')
	document.getElementById('marsApi' + rover).classList.add('btn-primary')
}