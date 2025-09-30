// Variant 1: using loop
function filterNumbersGreaterThan(array, number) {
  if (!Array.isArray(array)) {
    console.error("Error: The first parameter must be an array!");
    return [];
  }

  if (typeof number !== "number") {
    console.error("Error: The second parameter must be a number!");
    return [];
  }

  const array1 = [];
  for (let i = 0; i < array.length; i++) {
    if (array[i] > number) {
      array1.push(array[i]);
    }
  }
  return array1;
}

const array1 = [1, 2, 3, 4, 5];
const number1 = 3;
const result1 = filterNumbersGreaterThan(array1, number1);
console.log("Variant 1:", result1);

// Variant 2: using filter()
function filterNumbersGreaterThanFilter(array, number) {
  if (!Array.isArray(array)) {
    console.error("Error: The first parameter must be an array!");
    return [];
  }

  if (typeof number !== "number") {
    console.error("Error: The second parameter must be a number!");
    return [];
  }

  return array.filter(element => element > number);
}

const array2 = [10, 20, 33, 44, 55];
const number2 = 15;
const result2 = filterNumbersGreaterThanFilter(array2, number2);
console.log("Variant 2:", result2);
