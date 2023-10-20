    function increaseValue() {
        var valueInput = document.getElementById('value-input');
        var value = parseInt(valueInput.value);
        valueInput.value = value + 1;
    }

    function decreaseValue() {
        var valueInput = document.getElementById('value-input');
        var value = parseInt(valueInput.value);
        if (value > 1) {
            valueInput.value = value - 1;
        }
    }

    function checkValue() {
        var valueInput = document.getElementById('value-input');
        var value = parseInt(valueInput.value);
        if (value < 1 || isNaN(value)) {
            valueInput.value = 1;
        }
    }
// Prevent form submission from refreshing the page
	    var form = document.querySelector('form');
	    form.addEventListener('submit', function (event) {
	        event.preventDefault();
	    });