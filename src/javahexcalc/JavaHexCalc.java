package javahexcalc;

import java.util.Scanner;

public class JavaHexCalc {
    private static Scanner hex = new Scanner(System.in); //Для ввода Hex
    private static String hexArr[] = new String[2]; // массив куда помещаться будут два шестнадцатеричных числа

    public static void main(String[] args) {
        System.out.println("Программа совершает арифметические операции над двумя числами, \n заданными в шестнадцатеричной системе");
        userInput(); //ввод значений
        mathOper(); // арефмитические операции
        bitOper(); //побитовые операции
    }


    /**
     * Получения шестнадцетиричных чисел от пользователя,
     * Помещаются в массив для дальнейшей обработки
     */
    private static void userInput(){
        String tx[] = {"перевое", "второе"}; //для подстакновки при вводе

        for (int i = 0; i<2; i++){
            System.out.print("Введите " + tx[i] + " шестнадцетиричное число: ");

            String str = hex.next().toUpperCase(); //приводим все к верзнему регистру
            if(str.length() < 3){
                hexArr[i] = str; //помещаем введеное значение в массив
                continue;
            }
            char c1 = str.charAt(0);
            char c2 = str.charAt(1);
            if(c1 == '0' && c2 == 'X'){
                /*
                 * Проверка на начальные 0х,
                 * если они есть вырезаем из строки первые 2 символа
                 * помещаем введеное значение в массив
                */
                hexArr[i] = str.substring(2, str.length());
            }else{
                hexArr[i] = str; //помещаем введеное значение в массив
            }
        }
    }

    /**
     * Служебная функция
     * Просмотр, что есть в массиве
     * @param hexArr
     */
    private static void viewArr(String hexArr[]){
        for (int i=0; i<hexArr.length; i++){
            System.out.println("[" + i +"] => " + hexArr[i]);
        }
    }

    private static void mathOper(){
        /*Преобразуем в десятичную*/
        int aDec = Integer.parseInt(hexArr[0], 16);
        int bDec = Integer.parseInt(hexArr[1], 16);

        System.out.println("\n Математические операции:");
        printMathOper("a+b = "+ hexArr[0] + "+" + hexArr[1],aDec+bDec);
        printMathOper("a-b = "+ hexArr[0] + "-" + hexArr[1], aDec-bDec);
        printMathOper("a*b = "+ hexArr[0] + "+" + hexArr[1], aDec*bDec);
        printMathOper("a/b = "+ hexArr[0] + "+" + hexArr[1], aDec/bDec);
        printMathOper("a%b = "+ hexArr[0] + "+" + hexArr[1], aDec%bDec);
        printMathOper("++a = "+ hexArr[0] + "+" + 1, ++aDec);
        printMathOper("++b = "+ hexArr[1] + "+" + 1, bDec++);

        printMathOper("b++ = "+ hexArr[1] + "+" + 1, bDec++);
        printMathOper("a++ = "+ hexArr[0] + "+" + 1, aDec++);
    }

    private static void bitOper(){
        /*Преобразуем в десятичную*/
        int aDec = Integer.parseInt(hexArr[0], 16);
        int bDec = Integer.parseInt(hexArr[1], 16);

        String aBit, bBit, rezBit; //Числа в двоичной системе

        System.out.println("\nПобитовые операции:");
        aBit = Integer.toBinaryString(aDec);
        bBit = Integer.toBinaryString(bDec);

        rezBit = Integer.toBinaryString(~aDec);
        printMathOper("~a = ~0x" + hexArr[0] + " = " + aBit, (int)Long.parseLong(rezBit, 2));

        rezBit = Integer.toBinaryString(~bDec);
        printMathOper("~b = ~0x" + hexArr[1] + " = " + bBit, (int)Long.parseLong(rezBit, 2));

        rezBit = Integer.toBinaryString(aDec&bDec);
        printMathOper("a & b = 0x"+hexArr[0]+" & 0x"+hexArr[1]+" = "+aBit+" & "+bBit+" = "+rezBit, (int)Long.parseLong(rezBit, 2));

        rezBit = Integer.toBinaryString(aDec|bDec);
        printMathOper("a | b = 0x"+hexArr[0]+" | 0x"+hexArr[1]+" = "+aBit+" & "+bBit+" = "+rezBit, (int)Long.parseLong(rezBit, 2));


        rezBit = Integer.toBinaryString(aDec^bDec);
        printMathOper("a ^ b = 0x"+hexArr[0]+" ^ 0x"+hexArr[1]+" = "+aBit+" ^ "+bBit+" = "+rezBit, (int)Long.parseLong(rezBit, 2));

        rezBit = Integer.toBinaryString(aDec<<bDec);
        printMathOper("a << b = 0x"+hexArr[0]+" << 0x"+hexArr[1]+" = "+aBit+" << "+bBit+" = "+rezBit, (int)Long.parseLong(rezBit, 2));

        rezBit = Integer.toBinaryString(aDec>>bDec);
        printMathOper( "a >> b = 0x"+hexArr[0]+" >> 0x"+hexArr[1]+" = "+aBit+" >> "+bBit+" = "+rezBit, (int)Long.parseLong(rezBit, 2));
    }

    /**
     * Вывод значения на экран
     * @param oper - Последовательность операций над числами
     * @param rez - Десятичное число
     */
    private static void printMathOper(String oper, int rez){
        if(rez < 0){
            /*
             * Если приходит отрицательное число,
             * то для правильного отображения
             * в шестнацатеричной системе его надо инвертировать
             */
            String inv = Integer.toBinaryString(~Math.abs(rez)+1);
            rez = (int)Long.parseLong(inv, 2);
        }
        System.out.println(oper+" = "+"0х"+Integer.toHexString(rez));
    }
}
