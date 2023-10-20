var pathArray = window.location.pathname.split('/');
var id = pathArray[2];
if (id<1) {    
	enableEdit();
}

function previewImage(event) {
    var reader = new FileReader();
    reader.onload = function(){
      var output = document.getElementById('preview');
      output.src = reader.result;
    };
    reader.readAsDataURL(event.target.files[0]);
  }
  
function validateForm() {
  var title = document.getElementsByName("title")[0];
  var author = document.getElementsByName("author")[0];
  var releasedate = document.getElementsByName("releasedate")[0];

  var isValid = true;

  if (title.value === "") {
    title.classList.add("highlight");
    isValid = false;
  } else {
    title.classList.remove("highlight");
  }

  if (author.value === "") {
    author.classList.add("highlight");
    isValid = false;
  } else {
    author.classList.remove("highlight");
  }

  if (releasedate.value === "") {
    releasedate.classList.add("highlight");
    isValid = false;
  } else {
    releasedate.classList.remove("highlight");
  }

  if (!isValid) {
    alert("Please fill in all required fields!");
  }

  return isValid;
}

function enableEdit() {
    // Enable all input fields
    var inputFields = document.getElementsByTagName("input");
    for (var i = 0; i < inputFields.length; i++) {
        inputFields[i].disabled = false;
    }

    // Enable textarea field
    var textareaField = document.getElementsByTagName("textarea")[0];
    textareaField.disabled = false;

    // Enable select field
    var selectField = document.getElementsByTagName("select")[0];
    selectField.disabled = false;

    // Show the file upload button
    var fileUploadButton = document.getElementById("file-upload");
    fileUploadButton.disabled = false;

    // Hide the edit button
    var editButton = document.getElementById("editButton");
    editButton.style.display = "none";

    // Show the save button
    var saveButton = document.getElementById("saveButton");
    saveButton.style.display = "block";
}