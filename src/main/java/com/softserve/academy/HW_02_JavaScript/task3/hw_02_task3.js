function generateSecurePassword(length) {
  if (length < 8) {
    return "Password must be at least 8 characters long";
  }

  const uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  const lowercase = "abcdefghijklmnopqrstuvwxyz";
  const numbers = "0123456789";
  const specials = "!@#$%^&*-+()_=[]{};:<>?";
  const allChars = uppercase + lowercase + numbers + specials;

  const passwordArray = [];

  // Ensure at least one character from each category
  passwordArray.push(uppercase[Math.floor(Math.random() * uppercase.length)]);
  passwordArray.push(lowercase[Math.floor(Math.random() * lowercase.length)]);
  passwordArray.push(numbers[Math.floor(Math.random() * numbers.length)]);
  passwordArray.push(specials[Math.floor(Math.random() * specials.length)]);

  // Fill the rest
  while (passwordArray.length < length) {
    const randomChar = allChars[Math.floor(Math.random() * allChars.length)];
    const lastChar = passwordArray[passwordArray.length - 1];

    // Avoid repeating same char
    if (randomChar === lastChar) continue;

    // Avoid simple sequences
    if (passwordArray.length >= 2) {
      const secondLastChar = passwordArray[passwordArray.length - 2];
      const code1 = secondLastChar.charCodeAt(0);
      const code2 = lastChar.charCodeAt(0);
      const code3 = randomChar.charCodeAt(0);

      if (
        (code3 - code2 === 1 && code2 - code1 === 1) || 
        (code3 - code2 === -1 && code2 - code1 === -1)
      ) {
        continue;
      }
    }

    passwordArray.push(randomChar);
  }

  // Shuffle to randomize positions
  for (let i = passwordArray.length - 1; i > 0; i--) {
    const j = Math.floor(Math.random() * (i + 1));
    [passwordArray[i], passwordArray[j]] = [passwordArray[j], passwordArray[i]];
  }

  return passwordArray.join("");
}

// Examples
console.log(generateSecurePassword(2));   // too short
console.log(generateSecurePassword(8));   // valid password
console.log(generateSecurePassword(15));  // valid password
