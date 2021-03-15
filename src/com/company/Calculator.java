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
        int firstnum = 0;   //первое число
        int secnum = 0;     //второе число
        int length1 = 0;    //размер первого числа (для римской системы)
        int length2 = 0;    //размер второго числа
        int count = 0;      //текущий размер второго числа(для римской системы)
        if (flag == 1) {    //если арабская система
            while (str.charAt(i) == ' ')//пропуск пробелов
                i++;
            while (str.charAt(i) >= '0' && str.charAt(i) <= '9') {//пока элементы - число
                firstnum = firstnum * 10 + str.charAt(i) - 48;//умножаем текущий число на 10, и прибавляем его на текущий символ
                i++;
            }
            while (!(str.charAt(i) >= '0' && str.charAt(i) <= '9')) //пока не второе число
                i++;
            while (str.charAt(i) >= '0' && str.charAt(i) <= '9') { //такая же операция и со вторым числов
                secnum = secnum * 10 + str.charAt(i) - 48;
                i++;
                if (i == str.length())
                    break;
            }
        }
        if (flag == 2) {//если римская система
            while (str.charAt(i) == ' ')//пропуск пробелов
                i++;
            while (str.charAt(i) == 'I' || str.charAt(i) == 'V' || str.charAt(i) == 'X' || str.charAt(i) == 'L' || str.charAt(i) == 'C' || str.charAt(i) == 'D' || str.charAt(i) == 'M') {
                i++;//определяем размер первого числа
                length1++;
            }
            while (!(str.charAt(i) == 'I' || str.charAt(i) == 'V' || str.charAt(i) == 'X' || str.charAt(i) == 'L' || str.charAt(i) == 'C' || str.charAt(i) == 'D' || str.charAt(i) == 'M'))
                i++;
            while (str.charAt(i) == 'I' || str.charAt(i) == 'V' || str.charAt(i) == 'X' || str.charAt(i) == 'L' || str.charAt(i) == 'C' || str.charAt(i) == 'D' || str.charAt(i) == 'M') {
                i++;//определяем размер второго числа
                length2++;
                if (i == str.length())
                    break;
            }
            i = 0;//теперь считает первое число
            while (str.charAt(i) == ' ')
                i++;
            while (str.charAt(i) == 'I' || str.charAt(i) == 'V' || str.charAt(i) == 'X' || str.charAt(i) == 'L' || str.charAt(i) == 'C' || str.charAt(i) == 'D' || str.charAt(i) == 'M') {
                if (count == length1 - 2) {//если текущий размер слова == размеру слова - 2
                    int tmp = firstnum; //проверяем, изменилось ли слово, с момента запуска следующего метода
                    firstnum = afterstr(str, firstnum, i);//запуск метода, который определяем число, если последние элементы
                    //поменялись местами (к примеру, IX  = 9)
                    if (firstnum == tmp)//если число не поменялось, значит в числе нет таких ситуаций, описанных выше,
                        firstnum = sum(str, i, firstnum);//значит просто запускаем метод sum
                    else
                        i++;

                } else
                    firstnum = sum(str, i, firstnum);//в обычной ситуации запускаем метод, который прибавляем к числу
                count++;//другое число, в зависимости от символы строки
                i++;
            }
            count = 0;
            while (!(str.charAt(i) == 'I' || str.charAt(i) == 'V' || str.charAt(i) == 'X' || str.charAt(i) == 'L' || str.charAt(i) == 'C' || str.charAt(i) == 'D' || str.charAt(i) == 'M'))
                i++;
            while (str.charAt(i) == 'I' || str.charAt(i) == 'V' || str.charAt(i) == 'X' || str.charAt(i) == 'L' || str.charAt(i) == 'C' || str.charAt(i) == 'D' || str.charAt(i) == 'M') {
                if (count == length2 - 2) {//такая же ситуация и с вторым числом
                    int tmp = secnum;
                    secnum = afterstr(str, secnum, i);
                    if (secnum == tmp)
                        secnum = sum(str, i, secnum);
                    else
                        i++;
                } else
                    secnum = sum(str, i, secnum);
                i++;
                count++;
                if (i == str.length())
                    break;
            }
        }
        i = 0;//теперь определяем операцию, и считаем результат
        while (str.charAt(i) != '*' && str.charAt(i) != '+' && str.charAt(i) != '-' && str.charAt(i) != '/')
            i++;
        int tmp = 0;
        if (str.charAt(i) == '*')
            tmp = firstnum * secnum;
        if (str.charAt(i) == '+')
            tmp = firstnum + secnum;
        if (str.charAt(i) == '-')
            tmp = firstnum - secnum;
        if (str.charAt(i) == '/') {
            if (secnum == 0) {
                System.out.println("Ошибка");
                return;
            } else
                tmp = firstnum / secnum;
        }
        if (flag == 1)//если арабская система счисления, то сразу выводим результат
            System.out.println(tmp);
        if (flag == 2) {//если римская, то
            if (tmp < 0) {
                System.out.println("Ошибка, невозможно вывести отрицательное число в римской системе счисления");
                return;
            }
            while (tmp != 0) {//пока результат не обнулится, вычитаем из него числа, параллельно выводя их римские
                if (tmp >= 1000) { //эквиваленты на экран
                    System.out.print("M");
                    tmp -= 1000;
                } else if (tmp >= 500) {
                    System.out.print("D");
                    tmp -= 500;
                } else if (tmp >= 100) {
                    System.out.print("C");
                    tmp -= 100;
                } else if (tmp >= 50) {
                    System.out.print("L");
                    tmp -= 50;
                } else if (tmp >= 10) {
                    System.out.print("X");
                    tmp -= 10;
                } else if (tmp >= 5) {
                    System.out.print("V");
                    tmp -= 5;
                } else if (tmp >= 1) {
                    System.out.print("I");
                    tmp -= 1;
                }
            }
        }
    }
    static int afterstr(String str, int count, int i)//метод, в котором я расписал все ситуации,
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
    static int sum(String str, int i, int count)//метод, в котором подсчитывается сумма числа при нормальном условие
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



