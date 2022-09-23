
var string = "my name is ankit.";
var user = 0, temp = 0, final = 0, con_name = "";
size = string.length;

for (var i = 0; i < size; i++) {
    
    if (string[i] == ' ' || string[i] == '.') {
        final = Math.max(temp, final);
        temp = 0;
    }
    else
    temp++;
  
}
console.log("Max size : " + final);
console.log("Max size : " + user);

