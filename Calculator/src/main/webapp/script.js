/**
 * 
 */
function buttonClick(val) {
  var input = document.getElementsByName("input")[0];

  if (val == "C") {
    input.value = "";
  } else if (val == "=") {
    try {
      var result = eval(input.value);
      input.value = result;
    } catch (e) {
      input.value = "Error";
    }
  } else {
    input.value += val;
  }
}

