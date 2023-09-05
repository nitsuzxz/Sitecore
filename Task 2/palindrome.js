function palindromeChecker(inputString, trashSymbolsString) {
  let leftPointer = 0;
  let rightPointer = inputString.length - 1;

  while (leftPointer < rightPointer) {
    const leftChar = inputString[leftPointer].toLowerCase();
    const rightChar = inputString[rightPointer].toLowerCase();

    if (trashSymbolsString.includes(leftChar)) {
      leftPointer++;
      continue;
    }

    if (trashSymbolsString.includes(rightChar)) {
      rightPointer--;
      continue;
    }

    if (leftChar !== rightChar) {
      return false;
    }

    leftPointer++;
    rightPointer--;
  }

  return true;
}

console.log(palindromeChecker("$a@b!!b$a", "!@$"));
console.log(palindromeChecker("?Aa#c", "#?"));
