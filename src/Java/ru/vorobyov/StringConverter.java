package ru.vorobyov;

public class StringConverter {

    public String run(String inputWord){
        String outputWord = ""; //выводимая строка после итераций
        Boolean bracketsLeft = true; //булево значение для проверки на остаток скобок
        int previosLength = inputWord.length(); //длина входящей строки для проверки на финальной итерации

        //цикл с проверкой на наличие скобок и основной логикой
        while (bracketsLeft){
            //перевод строки в массив чаров
            char[] inputCharArray = inputWord.toCharArray();
            String partWord = "";
            inputWord = "";
            int firstBracket = 0;
            int lastBracket = 0;

            //проверка на наличие скобок
            for(char inputChar : inputCharArray){
                if(inputChar == '['){
                    bracketsLeft = true;
                    break;
                }
                else
                    bracketsLeft = false;
            }

            //финальная проверка для выхода из цикла
            if(!bracketsLeft && inputCharArray.length >= previosLength){
                break;
            }
            //проверка для пополнение пустого массива чаров для следующих итераций
            if(inputCharArray.length ==0){
                inputWord = outputWord;
                outputWord = "";
                continue;
            }
            //добавление оставшихся символов после скобок к выводимой строке
            if(!bracketsLeft && inputCharArray.length !=0){
                for(char inputChar : inputCharArray)
                    outputWord += inputChar;
                inputCharArray = null;
                continue;
            }

            //поиск первой или внешней и закрывающей её скобок (в зависимости от поступающей строки)
            for(int i = 0; i < inputCharArray.length; i++){
                if(inputCharArray[i] == '['){
                    firstBracket = i;
                    lastBracket = braсketFinder(inputCharArray, i); //вызов метода для поиска закрывающей скобки
                    break;
                }
            }
            //формирование символов внутри скобки в строку
            for(int i = firstBracket+1; i < lastBracket; i++){
                partWord += inputCharArray[i];
            }
            //добавление символов внутри строк в выводимую строку в зависимости от цифры перед ними
            for(int l = 0; l < Integer.parseInt(String.valueOf(inputCharArray[firstBracket - 1])); l++){
                outputWord += partWord;
            }

            //добавление символов в середине слова между скобками в выводимую строку
            for(int l = lastBracket+1; l < inputCharArray.length; l++){
                if(inputCharArray[l] == '['){
                    for (int i = lastBracket+1; i < inputCharArray.length; i++){
                        if(inputCharArray[i] == '[')
                            break;
                        outputWord += inputCharArray[i];
                    }

                    char[] outputCharArray = outputWord.toCharArray();
                    outputWord = "";

                    outputCharArray[outputCharArray.length - 1] = ' ';
                    for ( char outputChar : outputCharArray)
                        outputWord += outputChar;

                    outputWord = outputWord.trim();
                    break;
                }
            }
            //вырезание из изначальной строки уже раскрытых скобок для следующих итераций
            for(int i = lastBracket+1; i < inputCharArray.length; i++){
                inputWord += inputCharArray[i];
            }
        }
        return outputWord;
    }
    //метод для поиска закрывающей скобки
        int braсketFinder(char[] charArray, int numBegin){
        int lastBracket = 0;
        int secondBrackets = 0;
        for(int i = numBegin+1; i < charArray.length; i++){
            if(charArray[i] == '[')
                secondBrackets++;
            if(charArray[i] == ']' && secondBrackets > 0){
                secondBrackets --;
                continue;
            }
            if(charArray[i] == ']' && secondBrackets == 0){
                lastBracket = i;
                break;
            }
        }
        return lastBracket;
    }


}
