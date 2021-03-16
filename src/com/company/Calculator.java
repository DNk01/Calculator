package com.company;


import java.util.*;

public class Calculator {
    public static void main(String[] args) { //main
        inputStr();//первый метод, запуск программы
    }

    static void inputStr() {
        int i = 0;          //счетчик, нужен для подсчета абсолютно всего, что можно сосчитать и не нужно запоминать
        int flag = 0;       //флаг действия, 1 - арабская система счисления, 2- римская, 0 - выход из программы(ошибка)

        String str = new String();//В переменную str поместим входную строку
        Scanner in = new Scanner(System.in);//производим "сканирование" строки
        str = in.nextLine();//помещаем отсканированную строку в str
        while (str.charAt(i) == ' ')//исключаем ситуацию, когда до первого числа пользователь поместил пробелы
            i++;
        if (str.charAt(i) >= '0' && str.charAt(i) <= '9')//проверяем, в какой системе счисления дана строка
            flag = inputArab(str);//метод, в котором проверяем ,все ли цифры в арабской системе счисления
        else if (str.charAt(i) == 'I' || str.charAt(i) == 'V'||str.charAt(i) == 'X'||str.charAt(i) == 'L'||str.charAt(i) == 'C' ||str.charAt(i) == 'D' ||str.charAt(i) == 'M')
            flag = inputRim(str);////метод, в котором проверяем ,все ли цифры в римской системе счисления
        else { //если символ, идущий после пробелов не цифра, и не буква римской системы счисления, то выдаем ошибку, и
            System.out.println("Ошибка, введены некорректные данные"); //выходим из программы
            return;
        }
        if(flag == 0) {//если в ходе какого-то методы был возвращем flag = 0 (не соответствие заданной системе счисления,
            System.out.println("Ошибка, введены некорректные данные");// или же иная ошибка, связанная с ошибкой вводы, выдаем ошибку
            return;//и выходим из программы
        }
       getResult(str,flag);//переходим в следующий метод, где будем считать первое и второе число, и считает результат
    }

    static int inputArab(String str) {//суть метода - проверка входной строки, если заявлена арабская система счисления
        int i = 0;
        int count = 0;
        while (i < str.length())
        {
            if(str.charAt(i) == '+' || str.charAt(i) == '-' || str.charAt(i) == '*' || str.charAt(i) == '/')
                count++;
            else if(!(str.charAt(i)>='0' && str.charAt(i)<='9') && !(str.charAt(i)==' '))
                return 0;
            i++;
        }
        if(count < 1) //если количество операций не 1, выдаем ошибку
        {
            return 0;
        }
        return 1;
    }
    static int inputRim(String str)//проверка символов строки, если система счисления - римская
    {
        int i = 0;
        int count = 0;
        while (i < str.length())
        {
            if(str.charAt(i) == '+' || str.charAt(i) == '-' || str.charAt(i) == '*' || str.charAt(i) == '/')
                count++;
            else if(!(str.charAt(i) == 'I' || str.charAt(i) == 'V'||str.charAt(i) == 'X'||str.charAt(i) == 'L'||str.charAt(i) == 'C' ||str.charAt(i) == 'D' ||str.charAt(i) == 'M' || str.charAt(i) == ' '))
                return 0;
            i++;
        }
        if(count != 1)//если количество операций не 1, выдаем ошибку
        {
            return 0;
        }
        return 2;
    }
    static void getResult(String str, int flag) {
        int i = 0;          //счетчик
        number firstnum = new number();   //первое число
        number secnum = new number();
        int count = 0;      //текущий размер второго числа(для римской системы)
        operation oper = new operation(); //обьект типа операция
        if (flag == 1) {    //если арабская система
            while (str.charAt(i) == ' ')//пропуск пробелов
                i++;
            while (str.charAt(i) >= '0' && str.charAt(i) <= '9') {//пока элементы - число
                firstnum.value = firstnum.sub(firstnum.add(firstnum.mul(firstnum.value,10),str.charAt(i)),48);//умножаем текущий число на 10, и прибавляем его на текущий символ
                i++;
            }
            while (!(str.charAt(i) >= '0' && str.charAt(i) <= '9')) //пока не второе число
                i++;
            while (str.charAt(i) >= '0' && str.charAt(i) <= '9') { //такая же операция и со вторым числов
                secnum.value = secnum.sub(secnum.add(secnum.mul(secnum.value,10),str.charAt(i)),48);
                i++;
                if (i == str.length())
                    break;
            }
        }
        if (flag == 2) {//если римская система
            firstnum.length = firstnum.findlength(str, i);
            i+=firstnum.length;
            while (!(str.charAt(i) == 'I' || str.charAt(i) == 'V' || str.charAt(i) == 'X' || str.charAt(i) == 'L' || str.charAt(i) == 'C' || str.charAt(i) == 'D' || str.charAt(i) == 'M'))
                i++;
            secnum.length = secnum.findlength(str, i);
            i = 0;//теперь считает первое число
            while (str.charAt(i) == ' ')
                i++;
            while (str.charAt(i) == 'I' || str.charAt(i) == 'V' || str.charAt(i) == 'X' || str.charAt(i) == 'L' || str.charAt(i) == 'C' || str.charAt(i) == 'D' || str.charAt(i) == 'M') {
                if (count == firstnum.length - 2) {//если текущий размер слова == размеру слова - 2
                    double tmp = firstnum.value; //проверяем, изменилось ли слово, с момента запуска следующего метода
                    firstnum.value = afterstr(str, firstnum.value, i);//запуск метода, который определяем число, если последние элементы
                    //поменялись местами (к примеру, IX  = 9)
                    System.out.println(tmp);
                    System.out.println(firstnum.value);
                    if (firstnum.value == tmp)//если число не поменялось, значит в числе нет таких ситуаций, описанных выше,
                        firstnum.value = sum(str, i, firstnum.value);//значит просто запускаем метод sum
                    else
                        i++;
                } else
                    firstnum.value = sum(str, i, firstnum.value);//в обычной ситуации запускаем метод, который прибавляем к числу
                count++;//другое число, в зависимости от символы строки
                i++;
            }
            count = 0;
            while (!(str.charAt(i) == 'I' || str.charAt(i) == 'V' || str.charAt(i) == 'X' || str.charAt(i) == 'L' || str.charAt(i) == 'C' || str.charAt(i) == 'D' || str.charAt(i) == 'M'))
                i++;
            while (str.charAt(i) == 'I' || str.charAt(i) == 'V' || str.charAt(i) == 'X' || str.charAt(i) == 'L' || str.charAt(i) == 'C' || str.charAt(i) == 'D' || str.charAt(i) == 'M') {
                if (count == secnum.length - 2) {//такая же ситуация и с вторым числом
                    double tmp = secnum.value;
                    System.out.println(i);
                    secnum.value = afterstr(str, secnum.value, i);
                    if (secnum.value == tmp)
                        secnum.value = sum(str, i, secnum.value);
                    else
                        i++;
                } else
                    secnum.value = sum(str, i, secnum.value);
                i++;
                count++;
                if (i == str.length())
                    break;
            }
        }
        if(firstnum.checkcorrect(firstnum.value) == 0 || secnum.checkcorrect(secnum.value) == 0) {
            System.out.println("Ошибка, число не входит в диапозон допустимых");
            return ;
        }
        i = 0;//теперь определяем операцию, и считаем результат
        while (str.charAt(i) != '*' && str.charAt(i) != '+' && str.charAt(i) != '-' && str.charAt(i) != '/')
            i++;
        number tmp = new number();
        if (str.charAt(i) == '*')
            tmp.value = oper.mul(firstnum.value, secnum.value);
        if (str.charAt(i) == '+')
            tmp.value = oper.add(firstnum.value, secnum.value);
        if (str.charAt(i) == '-')
            tmp.value = oper.sub(firstnum.value, secnum.value);
        if (str.charAt(i) == '/') {
            if (secnum.value == 0) {
                System.out.println("Ошибка");
                return;
            } else
                tmp.value = oper.div(firstnum.value, secnum.value);
        }
        if (flag == 1)//если арабская система счисления, то сразу выводим результат
            System.out.println(tmp.value);
        if (flag == 2) {//если римская, то
            if (tmp.value < 0) {
                System.out.println("Ошибка, невозможно вывести отрицательное число в римской системе счисления");
                return;
            }
            while (tmp.value > 0) {//пока результат не обнулится, вычитаем из него числа, параллельно выводя их римские
                if (tmp.value >= 1000) { //эквиваленты на экран
                    System.out.print("M");
                    tmp.value -= 1000;
                } else if (tmp.value >= 500) {
                    System.out.print("D");
                    tmp.value -= 500;
                } else if (tmp.value >= 100) {
                    System.out.print("C");
                    tmp.value -= 100;
                } else if (tmp.value >= 50) {
                    System.out.print("L");
                    tmp.value -= 50;
                }else if (tmp.value >= 40) {
                    System.out.print("XL");
                    tmp.value -= 40;
                }else if (tmp.value >= 10) {
                    System.out.print("X");
                    tmp.value -= 10;
                }else if (tmp.value >= 9) {
                    System.out.print("IX");
                    tmp.value -= 9;
                }else if (tmp.value >= 5) {
                    System.out.print("V");
                    tmp.value -= 5;
                }else if (tmp.value >= 4) {
                    System.out.print("IV");
                    tmp.value -= 4;
                }else if (tmp.value >= 1) {
                    System.out.print("I");
                    tmp.value -= 1;
                }
                if(tmp.value > 0 && tmp.value < 1)
                {
                    System.out.println(".");
                    tmp.value*=10;
                    while(tmp.value%10!=0)
                    {
                        tmp.value*=10;
                    }
                }
            }
        }
    }
    static double afterstr(String str, double count, int i)//метод, в котором я расписал все ситуации,
    {//в которых возможна ситуация, когда символ римской цифры стоит не на свое месте, (что означаеи вычитание)
        if(str.charAt(i) == 'I')
        {
            if(str.charAt(i + 1) == 'V')
                count = count + 4;
            if(str.charAt(i + 1) == 'X')
                count = count + 9;
            if(str.charAt(i + 1) == 'L')
                count = count + 49;
            if(str.charAt(i + 1) == 'C')
                count = count + 99;
            if(str.charAt(i + 1) == 'D')
                count = count + 499;
            if(str.charAt(i + 1) == 'M')
                count = count + 999;
        }
        else if(str.charAt(i) == 'V')
        {
            if(str.charAt(i + 1) == 'X')
                count = count + 9;
            if(str.charAt(i + 1) == 'L')
                count = count + 49;
            if(str.charAt(i + 1) == 'C')
                count = count + 99;
            if(str.charAt(i + 1) == 'D')
                count = count + 499;
            if(str.charAt(i + 1) == 'M')
                count = count + 999;
        }
        else if(str.charAt(i) == 'X')
        {
            if(str.charAt(i + 1) == 'L')
                count = count + 49;
            if(str.charAt(i + 1) == 'C')
                count = count + 99;
            if(str.charAt(i + 1) == 'D')
                count = count + 499;
            if(str.charAt(i + 1) == 'M')
                count = count + 999;
        }
        else if(str.charAt(i) == 'L')
        {
            if(str.charAt(i + 1) == 'C')
                count = count + 99;
            if(str.charAt(i + 1) == 'D')
                count = count + 499;
            if(str.charAt(i + 1) == 'M')
                count = count + 999;
        }
        else if(str.charAt(i) == 'C')
        {
            if(str.charAt(i + 1) == 'D')
                count = count + 499;
            if(str.charAt(i + 1) == 'M')
                count = count + 999;
        }
        else if(str.charAt(i) == 'D')
        {
            if(str.charAt(i + 1) == 'M')
                count = count + 999;
        }
        return count;

    }
    static double sum(String str, int i, double count)//метод, в котором подсчитывается сумма числа при нормальном условие
    {//(нормальное условие - надо складываться числа, а не вычитать
        if(str.charAt(i) == 'I')
            count = count + 1;
        if(str.charAt(i) == 'V')
            count = count + 5;
        if(str.charAt(i) == 'X')
            count = count + 10;
        if(str.charAt(i) == 'L')
            count = count + 50;
        if(str.charAt(i) == 'C')
            count = count + 100;
        if(str.charAt(i) == 'D')
            count = count + 500;
        if(str.charAt(i) == 'M')
            count = count + 1000;
        return count;
    }
}
class operation
{
    double div(double firstnum, double secnum)
    {
        if(secnum!=0)
            return firstnum / secnum;
        else
            return 0;
    }
    double mul(double firstnum, double secnum)
    {
        return firstnum * secnum;
    }
    double add(double firstnum, double secnum)
    {
        return firstnum + secnum;
    }
    double sub(double firstnum, double secnum)
    {
        return firstnum - secnum;
    }
}
class number extends operation
{
    double value;
    int length;
    int checkcorrect(double number)
    {
        if(number < 0 || number > 10)
            return 0;
        return 1;
    }
    int findlength(String str, int i)
    {
        int len = 0;
        while (str.charAt(i) == ' ')//пропуск пробелов
            i++;
        while (str.charAt(i) == 'I' || str.charAt(i) == 'V' || str.charAt(i) == 'X' || str.charAt(i) == 'L' || str.charAt(i) == 'C' || str.charAt(i) == 'D' || str.charAt(i) == 'M') {
            i++;//определяем размер первого числа
            len++;
            if (i == str.length())
                break;
        }
        return len;
    }
}


