function isTextOnPage(text){ 

  if (typeof text !== "string" || !document.body) {
    return false;
  }
  
const pageText = document.body.innerText || ""; 

const lowercasePageText = pageText.toLowerCase();
const lowercaseText = text.toLowerCase();

return lowercasePageText.includes(lowercaseText);

}
const searchText = "Eco";
const result = isTextOnPage(searchText);
console.log(result);