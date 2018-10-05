var element = document.getElementsByName('loginForm');

element[0].addEventListener('submit', validate);


var rules = {
    required: function (element) {
        return element.value !== '';

    }
};

function showErrors(arr) {
    for (var i = 0; i < arr.length; i++) {
        if(arr[i].name === "login" && arr[i].error === "required"){
            document.getElementById("firstN").style.display = "inline";
        }
        if(arr[i].name === "password" && arr[i].error === "required"){
            document.getElementById("lastN").style.display = "inline";
        }
        if(arr[i].name === "department" && arr[i].error === "required"){
            document.getElementById("age").style.display = "inline";
        }
    }
}

function validate(e) {
    cleanErrors();
    var errors = [];
    var inputs = this.elements;

    for (var i = 0; i < inputs.length; i++) {
        if (inputs[i].type !== 'submit') {
            var rulesList = inputs[i].dataset.rule;
            rulesList = rulesList.split(' ');

            for (var j = 0; j < rulesList.length; j++) {
                if (rulesList[j] in rules) {
                    if (!rules[rulesList[j]](inputs[i])) {
                        errors.push({
                            name: inputs[i].name,
                            error: rulesList[j]
                        })
                    }
                }
            }
        }
    }
    if (errors.length > 0) {
        e.preventDefault();
        showErrors(errors);
    }
}

function cleanErrors() {
    var elements = document.getElementsByClassName("reqMsg");
    for (var i = 0; i < elements.length; i++) {
        elements[i].style.display = "none";
    }
}