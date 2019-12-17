package com.superjeevan.googol2019maven;

import java.util.Scanner;

public class CalculatorV2 {

    private int n = 0;

    public CalculatorV2(String cmd, int n) {
        this.n = n;
        Scanner s = new Scanner(System.in);
        error8:
        do {
            System.out.print("Numeric expression: ");
            String equa = "";
            if (this.n == 0) {
                this.n++;
                System.out.println(cmd);
                equa = cmd.replaceAll(" ", "");
            } else {
                equa = s.nextLine();
                equa = equa.replaceAll(" ", "");
                if (equa.equals("")) {
                    continue error8;
                }
            }

            int i = equa.length();
            boolean cond = false;
            boolean cond1 = false;
            boolean cond2 = false;
            boolean cond4 = false;
            boolean[] cond3 = new boolean[i];
            double[] num = new double[i];
            char[] ope = new char[i];
            char[][] brac = new char[i][i];
            boolean[] zero = new boolean[i];
            int[] cnn1 = new int[i];
            double a = 0;
            boolean a1 = false;
            int[] cnn3 = new int[i];
            int[] cnn4 = new int[i];
            int[] cnn5 = new int[i];
            int[] cnn6 = new int[i];

            int open = 0;
            int open1 = 0;
            int open2 = 0;
            int close1 = 0;
            int cnnn = 0;
            double sum = 0;
            int cnn = 0;
            int[] cnn2 = new int[i];
            int u = 0;
            error:
            for (int k = 0; k < i; k++) {
                sum = 0;
                u = 0;
                cnnn = 0;
                zero[k] = false;
                adder:
                for (int j = i - 1 - cnn; j >= 0; j--) {
                    switch (equa.charAt(j)) {
                        case '0':
                            u++;
                            cnn++;
                            cnnn++;
                            zero[k] = true;
                            continue;
                        case '1':
                            sum += 1 * Math.pow(10, u);
                            u++;
                            cnn++;
                            cnnn++;
                            continue;
                        case '2':
                            sum += 2 * Math.pow(10, u);
                            u++;
                            cnn++;
                            cnnn++;
                            continue;
                        case '3':
                            sum += 3 * Math.pow(10, u);
                            u++;
                            cnn++;
                            cnnn++;
                            continue;
                        case '4':
                            sum += 4 * Math.pow(10, u);
                            u++;
                            cnn++;
                            cnnn++;
                            continue;
                        case '5':
                            sum += 5 * Math.pow(10, u);
                            u++;
                            cnn++;
                            cnnn++;
                            continue;
                        case '6':
                            sum += 6 * Math.pow(10, u);
                            u++;
                            cnn++;
                            cnnn++;
                            continue;
                        case '7':
                            sum += 7 * Math.pow(10, u);
                            u++;
                            cnn++;
                            cnnn++;
                            continue;
                        case '8':
                            sum += 8 * Math.pow(10, u);
                            u++;
                            cnn++;
                            cnnn++;
                            continue;
                        case '9':
                            sum += 9 * Math.pow(10, u);
                            u++;
                            cnn++;
                            cnnn++;
                            continue;
                        case '(':
                            if (brac[k][0] == 0) {
                                brac[k][0] = '(';
                            } else if (brac[k][0] == '(') {
                                ++open;
                                brac[k][open] = '(';
                            } else if (brac[k][0] == ')') {
                                System.out.println("Syntax error");
                                System.out.println("");
                                continue error8;
                            }
                            open1++;
                            cnn++;
                            break adder;
                        case ')':
                            if (brac[k][0] == 0) {
                                brac[k][0] = ')';
                            } else if (brac[k][0] == ')') {
                                ++open;
                                brac[k][open] = ')';
                            } else if (brac[k][0] == '(') {
                                System.out.println("Syntax error");
                                System.out.println("");
                                continue error8;
                            }
                            close1++;
                            cnn++;
                            break adder;
                        case '+':
                            ope[k] = '+';
                            cnn++;
                            break adder;
                        case '-':
                            ope[k] = '-';
                            cnn++;
                            break adder;
                        case 'x':
                            ope[k] = '*';
                            cnn++;
                            break adder;
                        case '*':
                            ope[k] = '*';
                            cnn++;
                            break adder;
                        case '/':
                            ope[k] = '/';
                            cnn++;
                            break adder;
                        case '=':
                            System.out.println("Do not enter '=' sign");
                            System.out.println();
                            continue error8;
                        case '.':
                            cnn++;
                            sum = Math.pow(10, (-cnnn)) * sum;
                            u = 0;
                            continue;
                        default:
                            System.out.println("Please check any irrelevant operators.");
                            System.out.println();
                            continue error8;
                    }

                }

                num[k] = sum;

            }
            if (open1 != close1) {
                System.out.println("Syntax error");
                System.out.println("");
                continue error8;
            }
            open1 = 0;
            close1 = 0;
            for (int t = i - 1; t >= 0; t--) {
                cond1 = false;
                skip7:
                for (int x = 0; x < i; x++) {

                    if (brac[t][0] == '(' && x + 1 < i) {
                        if (brac[t][x] == '(') {
                            open2 = x;
                            open1 = t;

                        }
                    } else if (brac[t][0] == ')' && x + 1 < i) {
                        if (brac[t][x + 1] != ')') {
                            brac[t][x] = 0;
                            brac[open1][open2] = 0;
                            close1 = t;
                            cond1 = true;
                            cnn4[t] = t;
                            cnn5[open1] = open1;
                            if (close1 == 0) {
                                cond4 = true;
                            }
                            break skip7;
                        }
                    }
                }
                if (cond1 == true) {
                    for (int j = close1 + 1; j <= open1; j++) {
                        if (ope[j] == '+') {
                            if (j + 1 >= i) {
                                System.out.println("Syntax error");
                                System.out.println("");
                                continue error8;
                            } else if ((num[j + 1] == 0 && zero[j + 1] == false) && (cnn3[j] != j || (cnn3[j] == 0 && cond2 == false))) {
                                num[j + 1] = num[j];
                                cnn1[j] = j;
                                cnn3[j] = j;
                                if (j == 0) {
                                    cond2 = true;
                                }
                                if (j == 0) {
                                    cond = true;
                                }
                            }
                        } else if (ope[j] == '-') {
                            if (j + 1 >= i) {
                                System.out.println("Syntax error");
                                System.out.println("");
                                continue error8;
                            }
                            if ((num[j + 1] == 0 && zero[j + 1] == false) && (cnn3[j] != j || (cnn3[j] == 0 && cond2 == false))) {
                                cnn1[j] = j;
                                cnn3[j] = j;
                                if (j == 0) {
                                    cond2 = true;
                                }

                                if (j == 0) {
                                    cond = true;
                                }
                                num[j + 1] = num[j] * -1;
                                num[j] = num[j + 1];
                                skip9:
                                for (int m = 1; j - m >= 0; m++) {
                                    if (cnn1[j - m] == 0 && cond == true) {
                                        num[0] = num[1];
                                    } else if (cnn1[j - m] == j - m && (j - m) != 0) {
                                        num[j - m] = num[j];
                                    } else {
                                        break skip9;
                                    }

                                }
                            }
                        }
                    }
                    for (int j = open1; j > close1; j--) {
                        if (ope[j] == '*') {
                            if (num[j + 1] == 0 && zero[j + 1] == false && cond3[j] == false) {
                                System.out.println("Syntax error");
                                System.out.println("");
                                continue error8;
                            } else if (num[j] == 0 && zero[j] == false && cond3[j] == false) {
                                System.out.println("Syntax error");
                                System.out.println("");
                                continue error8;
                            } else if ((num[j] != 0 || (num[j] == 0 && zero[j] == true) || (num[j] == 0 && cond3[j] == true) || ((num[j + 1] != 0 || (num[j + 1] == 0 && zero[j + 1] == true)) && (ope[j - 1] != '*' && ope[j - 1] != '/'))) && (cnn3[j] != j || (cnn3[j] == 0 && cond2 == false))) {

                                num[j] = num[j] * num[j + 1];
                                num[j + 1] = num[j];
                                a = num[j];
                                a1 = true;
                                cnn2[j] = j;
                                if (j == 0) {
                                    cond2 = true;
                                }
                                skip1:
                                for (int k = 1; k <= i - 1 - j; k++) {
                                    if (ope[j + k] == '*' || ope[j + k] == '/') {
                                        num[j + k + 1] = num[j];
                                    }
                                    if (j - k > 0 && (ope[j - k] == '+' || ope[j - k] == '-') && cnn1[j - k] == j - k) {
                                        num[j - k] = num[j];
                                    } else if (j - k == 0 && cond == true) {
                                        num[0] = num[1];
                                    } else if (ope[j + k] != '*' || ope[j + k] != '/') {
                                        break skip1;
                                    }
                                }

                                cnn3[j] = j;
                            }

                        } else if (ope[j] == '/') {
                            if (num[j + 1] == 0 && zero[j + 1] == false && cond3[j] == false) {
                                System.out.println("Syntax error");
                                System.out.println("");
                                continue error8;
                            }
                            if (num[j] == 0 && zero[j] == false && cond3[j] == false) {
                                System.out.println("Syntax error");
                                System.out.println("");
                                continue error8;
                            } else if ((num[j] != 0 || (num[j] == 0 && zero[j] == true) || (num[j] == 0 && cond3[j] == true) || ((ope[j - 1] != 0 || (num[j - 1] == 0 && zero[j - 1] == true)) && (ope[j - 1] != '*' && ope[j - 1] != '/'))) && (cnn3[j] != j || (cnn3[j] == 0 && cond2 == false))) {
                                if (num[j] == 0) {
                                    System.out.println("Answer: Infinity");
                                    System.out.println("");
                                    System.out.println("Enter any button to continue / "
                                            + "'no' to exit");
                                    String user = s.nextLine();
                                    System.out.println();
                                    if (user.equals("no")) {
                                        break error8;
                                    } else {
                                        continue error8;
                                    }
                                }
                                num[j] = num[j + 1] / num[j];
                                num[j + 1] = num[j];
                                a = num[j];
                                a1 = true;
                                cnn2[j] = j;
                                if (j == 0) {
                                    cond2 = true;
                                }
                                skip2:
                                for (int k = 1; k <= i - 1 - j; k++) {
                                    if (ope[j + k] == '*' || ope[j + k] == '/') {
                                        num[j + k + 1] = num[j];
                                    } else if (j - k > 0 && (ope[j - k] == '+' || ope[j - k] == '-') && cnn1[j - k] == j - k) {
                                        num[j - k] = num[j];
                                    } else if (j - k == 0 && cond == true) {
                                        num[0] = num[1];
                                    } else if (ope[j + k] != '*' || ope[j + k] != '/') {
                                        break skip2;
                                    }
                                }
                                cnn3[j] = j;
                            }
                        }
                    }
                    for (int j = open1; j > close1; j--) {
                        if (ope[j] == '+') {
                            if (num[j] == 0 && zero[j] == false) {
                                System.out.println("Syntax error");
                                System.out.println("");
                                continue error8;
                            } else if ((cnn1[j] != j || (cnn1[j] == 0 && cond == false)) && (cnn3[j] != j || (cnn3[j] == 0 && cond2 == false))) {
                                num[j] = num[j] + num[j + 1];
                                num[j + 1] = num[j];
                                a = num[j];
                                a1 = true;
                                if (j == 0) {
                                    cond2 = true;
                                }
                                skip8:
                                for (int k = 1; j - k >= 0; k++) {
                                    if (cnn1[j - k] == j - k && (j - k) != 0) {
                                        num[j - k] = num[j];
                                    } else if (cnn1[j - k] == j - k && (j - k) == 0 && cond == true) {
                                        num[0] = num[1];
                                    } else if (cnn1[j - k] != j - k) {
                                        break skip8;
                                    }
                                }
                                skip3:
                                for (int k = 1; k <= i - 1 - j; k++) {
                                    if (j - k < 0) {
                                        break skip3;
                                    } else {
                                        if (((ope[j - k] == '*' || ope[j - k] == '/') && cnn3[j - k] == (j - k) && cnn3[j - k] != 0) || (cnn5[j - k] == j - k && j - k != 0) || (cnn4[j - k] == j - k && (j - k) != 0) || (j - k == 0 && cnn4[j - k] == 0 && cond4 == true)) {
                                            num[j - k] = num[j];
                                        } else if ((ope[j - k] != '*' || ope[j - k] != '/' || ((ope[j - k] == '*' || ope[j - k] == '/') && cnn3[j - k] != j - k)) || (cnn5[j - k] != j - k || j - k == 0) || (cnn4[j - k] != j - k) || ((j - k) == 0 && cond4 == false)) {
                                            break skip3;
                                        }
                                    }
                                }
                                if (num[close1 + 1] != num[open1]) {
                                    num[open1] = num[close1 + 1];
                                }

                                skip1:
                                for (int k = 1; open1 - k >= 0; k++) {
                                    if ((cnn3[open1 - k] == (open1 - k) && open1 - k != 0) || (open1 - k == 0 && (cnn3[open1 - k] == 0 && cond2 == true)) || (cnn5[open1 - k] == open1 - k && cnn5[open1 - k] != 0)) {
                                        num[open1 - k] = num[open1];
                                    } else if ((cnn4[open1 - k] == open1 - k && (open1 - k) != 0) || ((open1 - k) == 0 && cond4 == true)) {
                                        if ((num[open1 - k] == 0 && zero[open1 - k] == false) || (num[open1 - k] != 0 && cnn6[open1 - k] == open1 - k)) {
                                            num[open1 - k] = num[open1];
                                            cnn6[open1 - k] = open1 - k;
                                        } else if ((num[open1 - k] != 0 || (num[open1 - k] == 0 && zero[open1 - k] == true)) && (cnn6[open1 - k] != (open1 - k))) {
                                            cnn6[open1 - k] = open1 - k;
                                        } else {
                                        };

                                    } else if (open1 - k != cnn3[open1 - k] && ope[open1 - k] == 0 && cnn4[open1 - k] != open1 - k && (cnn4[open1 - k] == 0 && cond4 == false)) {
                                        break skip1;
                                    }
                                }
                                cnn3[j] = j;
                            }
                        } else if (ope[j] == '-') {

                            if ((cnn1[j] != j || (cnn1[j] == 0 && cond == false)) && (cnn3[j] != j || (cnn3[j] == 0 && cond2 == false))) {
                                if (num[j] >= 0) {
                                    num[j] = num[j + 1] - num[j];
                                    num[j + 1] = num[j];
                                    a = num[j];
                                    a1 = true;
                                    if (j == 0) {
                                        cond2 = true;
                                    }
                                    skip8:
                                    for (int k = 1; j - k >= 0; k++) {
                                        if (cnn1[j - k] == j - k && (j - k) != 0) {
                                            num[j - k] = num[j];
                                        } else if (cnn1[j - k] == j - k && (j - k) == 0 && cond == true) {
                                            num[0] = num[1];
                                        } else if (cnn1[j - k] != j - k) {
                                            break skip8;
                                        }
                                    }
                                    skip3:
                                    for (int k = 1; k <= i - 1 - j; k++) {
                                        if (j - k < 0) {
                                            break skip3;
                                        } else {
                                            if (((ope[j - k] == '*' || ope[j - k] == '/') && cnn3[j - k] == (j - k) && cnn3[j - k] != 0) || (cnn5[j - k] == j - k && j - k != 0) || (cnn4[j - k] == j - k && (j - k) != 0) || (j - k == 0 && cnn4[j - k] == 0 && cond4 == true)) {
                                                num[j - k] = num[j];
                                            } else if ((ope[j - k] != '*' || ope[j - k] != '/' || ((ope[j - k] == '*' || ope[j - k] == '/') && cnn3[j - k] != j - k)) || (cnn5[j - k] != j - k || j - k == 0) || (cnn4[j - k] != j - k) || ((j - k) == 0 && cond4 == false)) {
                                                break skip3;
                                            }
                                        }
                                    }
                                } else if (num[j] < 0) {
                                    num[j] = num[j + 1] + num[j] * -1;
                                    num[j + 1] = num[j];
                                    a = num[j];
                                    a1 = true;
                                    skip8:
                                    for (int k = 1; j - k >= 0; k++) {
                                        if (cnn1[j - k] == j - k && (j - k) != 0) {
                                            num[j - k] = num[j];
                                        } else if (cnn1[j - k] == j - k && (j - k) == 0 && cond == true) {
                                            num[0] = num[1];
                                        } else if (cnn1[j - k] != j - k) {
                                            break skip8;
                                        }
                                    }
                                    skip3:
                                    for (int k = 1; k <= i - 1 - j; k++) {
                                        if (j - k < 0) {
                                            break skip3;
                                        } else {
                                            if (((ope[j - k] == '*' || ope[j - k] == '/') && cnn3[j - k] == (j - k) && cnn3[j - k] != 0) || (cnn5[j - k] == j - k && j - k != 0) || (cnn4[j - k] == j - k && (j - k) != 0) || (cnn4[j - k] == 0 && cond4 == true)) {
                                                num[j - k] = num[j];
                                            } else if ((ope[j - k] != '*' || ope[j - k] != '/' || ((ope[j - k] == '*' || ope[j - k] == '/') && cnn3[j - k] != j - k)) || (cnn5[j - k] != j - k || j - k == 0) || (cnn4[j - k] != j - k) || ((j - k) == 0 && cond4 == false)) {
                                                break skip3;
                                            }
                                        }
                                    }
                                }
                                if (num[close1 + 1] != num[open1]) {
                                    num[open1] = num[close1 + 1];
                                }
                                skip1:
                                for (int k = 1; open1 - k >= 0; k++) {
                                    if ((cnn3[open1 - k] == (open1 - k) && open1 - k != 0) || (cnn3[open1 - k] == 0 && cond2 == true) || (cnn5[open1 - k] == open1 - k && cnn5[open1 - k] != 0)) {
                                        num[open1 - k] = num[open1];
                                    } else if ((cnn4[open1 - k] == open1 - k && (open1 - k) != 0) || ((open1 - k) == 0 && cond4 == true)) {
                                        if ((num[open1 - k] == 0 && zero[open1 - k] == false) || (num[open1 - k] != 0 && cnn6[open1 - k] == open1 - k)) {
                                            num[open1 - k] = num[open1];
                                            cnn6[open1 - k] = open1 - k;
                                        } else if ((num[open1 - k] != 0 || (num[open1 - k] == 0 && zero[open1 - k] == true)) && (cnn6[open1 - k] != (open1 - k))) {
                                            cnn6[open1 - k] = open1 - k;
                                        } else {
                                        };

                                    } else if (open1 - k != cnn3[open1 - k] && ope[open1 - k] == 0 && cnn4[open1 - k] != open1 - k && (cnn4[open1 - k] == 0 && cond4 == false)) {
                                        break skip1;
                                    }
                                }
                                cnn3[j] = j;
                            }
                        }
                    }
                    for (int j = close1 + 2; j <= open1; j++) {
                        num[j] = num[close1 + 1];
                    }
                    if ((num[open1] != a && a != 0) || (a == 0 && a1 == true)) {
                        num[open1] = a;
                    }
                    if (num[open1 + 1] == 0 & zero[open1 + 1] == false) {
                        num[open1 + 1] = num[open1];
                    } else if (num[open1 + 1] != 0 || (num[open1 + 1] == 0 && zero[open1 + 1] == true)) {
                        num[open1 + 1] = num[open1 + 1] * num[open1];
                        num[open1] = num[open1 + 1];
                    }
                    skip1:
                    for (int k = 1; open1 - k >= 0; k++) {
                        if ((cnn3[open1 - k] == (open1 - k) && open1 - k != 0) || (cnn3[open1 - k] == 0 && cond2 == true) || (cnn5[open1 - k] == open1 - k && cnn5[open1 - k] != 0)) {
                            num[open1 - k] = num[open1];
                        } else if ((cnn4[open1 - k] == open1 - k && (open1 - k) != 0) || ((open1 - k) == 0 && cond4 == true)) {
                            if ((num[open1 - k] == 0 && zero[open1 - k] == false) || (num[open1 - k] != 0 && cnn6[open1 - k] == open1 - k)) {
                                num[open1 - k] = num[open1];
                                cnn6[open1 - k] = open1 - k;
                            } else if ((num[open1 - k] != 0 || (num[open1 - k] == 0 && zero[open1 - k] == true)) && (cnn6[open1 - k] != (open1 - k))) {
                                num[open1 - k + 1] = num[open1 - k] * num[open1 - k + 1];
                                num[open1 - k] = num[open1 - k + 1];
                                cnn6[open1 - k] = open1 - k;
                            } else {
                            };

                        } else if (open1 - k != cnn3[open1 - k] && ope[open1 - k] == 0 && cnn4[open1 - k] != open1 - k && (cnn4[open1 - k] == 0 && cond4 == false)) {
                            break skip1;
                        }
                    }
                    for (int j = close1 + 2; j <= open1; j++) {
                        num[j] = num[close1];
                    }

                    open1 = 0;
                    close1 = 0;
                    t = i - 1;
                }
            }
            for (int j = 0; j < i; j++) {
                if (ope[j] == '+') {
                    if (j + 1 >= i) {
                        System.out.println("Syntax error");
                        System.out.println("");
                        continue error8;
                    } else if ((num[j + 1] == 0 && zero[j + 1] == false) && (cnn3[j] != j || (cnn3[j] == 0 && cond2 == false))) {
                        num[j + 1] = num[j];
                        cnn1[j] = j;
                        if (j == 0) {
                            cond = true;
                        }
                    }
                } else if (ope[j] == '-') {
                    if (j + 1 >= i) {
                        System.out.println("Syntax error");
                        System.out.println("");
                        continue error8;
                    }
                    if ((num[j + 1] == 0 && zero[j + 1] == false) && (cnn3[j] != j || (cnn3[j] == 0 && cond2 == false))) {
                        cnn1[j] = j;
                        if (j == 0) {
                            cond = true;
                        }
                        num[j + 1] = num[j] * -1;
                        num[j] = num[j + 1];
                        skip9:
                        for (int m = 1; j - m >= 0; m++) {
                            if (cnn1[j - m] == 0 && cond == true) {
                                num[0] = num[1];
                            } else if (cnn1[j - m] == j - m && (j - m) != 0) {
                                num[j - m] = num[j];
                            } else {
                                break skip9;
                            }

                        }
                    }
                }
            }

            for (int j = i - 1; j >= 0; j--) {
                if (ope[j] == '*') {
                    if (num[j + 1] == 0 && zero[j + 1] == false && cond3[j + 1] == false) {
                        System.out.println("Syntax error");
                        System.out.println("");
                        continue error8;
                    } else if (num[j] == 0 && zero[j] == false && cond3[j] == false) {
                        System.out.println("Syntax error");
                        System.out.println("");
                        continue error8;
                    } else if ((num[j] != 0 || (num[j] == 0 && zero[j] == true || (num[j] == 0 && cond3[j] == true)) || ((num[j + 1] != 0 || (num[j + 1] == 0 && zero[j + 1] == true)) && (ope[j - 1] != '*' && ope[j - 1] != '/'))) && (cnn3[j] != j || (cnn3[j] == 0 && cond2 == false))) {

                        num[j] = num[j] * num[j + 1];
                        num[j + 1] = num[j];
                        cnn2[j] = j;
                        skip1:
                        for (int k = 1; k <= i - 1 - j; k++) {
                            if (ope[j + k] == '*' || ope[j + k] == '/') {
                                num[j + k + 1] = num[j];
                            } else if (j - k > 0 && (ope[j - k] == '+' || ope[j - k] == '-') && cnn1[j - k] == j - k) {
                                num[j - k] = num[j];
                            } else if (j - k == 0 && cond == true) {
                                num[0] = num[1];
                            } else if (ope[j + k] != '*' || ope[j + k] != '/') {
                                break skip1;
                            }
                        }
                        skip1:
                        for (int k = 1; j - k >= 0; k++) {
                            if ((cnn3[j - k] == (j - k) && j - k != 0) || (cnn3[j - k] == 0 && cond2 == true) || (cnn5[j - k] == j - k && cnn5[j - k] != 0)) {
                                num[j - k] = num[j];
                            } else if ((cnn4[j - k] == j - k && (j - k) != 0) || ((j - k) == 0 && cond4 == true)) {
                                if ((num[j - k] == 0 && zero[j - k] == false) || (num[j - k] != 0 && cnn6[j - k] == j - k)) {
                                    num[j - k] = num[j];
                                    cnn6[j - k] = j - k;
                                } else if ((num[j - k] != 0 || (num[j - k] == 0 && zero[j - k] == true)) && (cnn6[j - k] != (j - k))) {
                                    num[j - k + 1] = num[j - k] * num[j - k + 1];
                                    num[j - k] = num[j - k + 1];
                                    cnn6[j - k] = j - k;
                                } else {
                                };

                            } else if (j - k != cnn3[j - k] && ope[j - k] == 0 && cnn4[j - k] != j - k && (cnn4[j - k] == 0 && cond4 == false)) {
                                break skip1;
                            }
                        }
                    }
                } else if (ope[j] == '/') {
                    if (num[j + 1] == 0 && zero[j + 1] == false && cond3[j + 1] == false) {
                        System.out.println("Syntax error");
                        System.out.println("");
                        continue error8;
                    }
                    if (num[j] == 0 && zero[j] == false && cond3[j] == false) {
                        System.out.println("Syntax error");
                        System.out.println("");
                        continue error8;
                    } else if ((num[j] != 0 || (num[j] == 0 && zero[j] == true) || (num[j] == 0 && cond3[j] == true) || ((num[j + 1] != 0 || (num[j + 1] == 0 && zero[j + 1] == true)) && (ope[j - 1] != '*' && ope[j - 1] != '/'))) && (cnn3[j] != j || (cnn3[j] == 0 && cond2 == false))) {
                        if (num[j] == 0) {
                            System.out.println("Answer: Infinity");
                            System.out.println("");
                            System.out.println("Enter any button to continue / "
                                    + "'no' to exit");
                            String user = s.nextLine();
                            System.out.println();
                            if (user.equals("no")) {
                                break error8;
                            } else {
                                continue error8;
                            }
                        }
                        num[j] = num[j + 1] / num[j];
                        num[j + 1] = num[j];
                        cnn2[j] = j;
                        skip2:
                        for (int k = 1; k <= i - 1 - j; k++) {
                            if (ope[j + k] == '*' || ope[j + k] == '/') {
                                num[j + k + 1] = num[j];
                            } else if (j - k > 0 && (ope[j - k] == '+' || ope[j - k] == '-') && cnn1[j - k] == j - k) {
                                num[j - k] = num[j];
                            } else if (j - k == 0 && cond == true) {
                                num[0] = num[1];
                            } else if (ope[j + k] != '*' || ope[j + k] != '/') {
                                break skip2;
                            }
                        }
                        skip1:
                        for (int k = 1; j - k >= 0; k++) {
                            if ((cnn3[j - k] == (j - k) && j - k != 0) || (cnn3[j - k] == 0 && cond2 == true) || (cnn5[j - k] == j - k && cnn5[j - k] != 0)) {
                                num[j - k] = num[j];
                            } else if ((cnn4[j - k] == j - k && (j - k) != 0) || ((j - k) == 0 && cond4 == true)) {
                                if ((num[j - k] == 0 && zero[j - k] == false) || (num[j - k] != 0 && cnn6[j - k] == j - k)) {
                                    num[j - k] = num[j];
                                    cnn6[j - k] = j - k;
                                } else if ((num[j - k] != 0 || (num[j - k] == 0 && zero[j - k] == true)) && (cnn6[j - k] != (j - k))) {
                                    num[j - k + 1] = num[j - k] * num[j - k + 1];
                                    num[j - k] = num[j - k + 1];
                                    cnn6[j - k] = j - k;
                                } else {
                                };

                            } else if (j - k != cnn3[j - k] && ope[j - k] == 0 && cnn4[j - k] != j - k && (cnn4[j - k] == 0 && cond4 == false)) {
                                break skip1;
                            }
                        }
                    }
                }
            }
            for (int j = i - 1; j >= 0; j--) {
                if (ope[j] == '+') {
                    if (num[j] == 0 && zero[j] == false) {
                        System.out.println("Syntax error");
                        System.out.println("");
                        continue error8;
                    }
                    if ((cnn1[j] != j || (cnn1[j] == 0 && cond == false)) && (cnn3[j] != j || (cnn3[j] == 0 && cond2 == false))) {
                        num[j] = num[j] + num[j + 1];
                        num[j + 1] = num[j];
                        skip8:
                        for (int k = 1; j - k >= 0; k++) {
                            if (cnn1[j - k] == j - k && (j - k) != 0) {
                                num[j - k] = num[j];
                            } else if (cnn1[j - k] == j - k && (j - k) == 0 && cond == true) {
                                num[0] = num[1];
                            } else if (cnn1[j - k] != j - k) {
                                break skip8;
                            }
                        }
                        skip3:
                        for (int k = 1; k <= i - 1 - j; k++) {
                            if (j - k < 0) {
                                break skip3;
                            } else {
                                if (ope[j - k] == '*' || ope[j - k] == '/') {
                                    num[j - k] = num[j];
                                } else if (ope[j - k] != '*' || ope[j - k] != '/') {
                                    break skip3;
                                }
                            }
                        }
                        skip1:
                        for (int k = 1; j - k >= 0; k++) {
                            if ((cnn3[j - k] == (j - k) && j - k != 0) || (cnn3[j - k] == 0 && cond2 == true) || (cnn5[j - k] == j - k && cnn5[j - k] != 0)) {
                                num[j - k] = num[j];
                            } else if ((cnn4[j - k] == j - k && (j - k) != 0) || ((j - k) == 0 && cond4 == true)) {
                                if ((num[j - k] == 0 && zero[j - k] == false) || (num[j - k] != 0 && cnn6[j - k] == j - k)) {
                                    num[j - k] = num[j];
                                    cnn6[j - k] = j - k;
                                } else if ((num[j - k] != 0 || (num[j - k] == 0 && zero[j - k] == true)) && (cnn6[j - k] != (j - k))) {
                                    num[j - k + 1] = num[j - k] * num[j - k + 1];
                                    num[j - k] = num[j - k + 1];
                                    cnn6[j - k] = j - k;
                                } else {
                                };

                            } else if (j - k != cnn3[j - k] && ope[j - k] == 0 && cnn4[j - k] != j - k && (cnn4[j - k] == 0 && cond4 == false)) {
                                break skip1;
                            }
                        }
                    }
                } else if (ope[j] == '-') {

                    if ((cnn1[j] != j || (cnn1[j] == 0 && cond == false)) && (cnn3[j] != j || (cnn3[j] == 0 && cond2 == false))) {
                        if (num[j] >= 0) {
                            num[j] = num[j + 1] - num[j];
                            num[j + 1] = num[j];
                            skip8:
                            for (int k = 1; j - k >= 0; k++) {
                                if (cnn1[j - k] == j - k && (j - k) != 0) {
                                    num[j - k] = num[j];
                                } else if (cnn1[j - k] == j - k && (j - k) == 0 && cond == true) {
                                    num[0] = num[1];
                                } else if (cnn1[j - k] != j - k) {
                                    break skip8;
                                }
                            }
                            skip3:
                            for (int k = 1; k <= i - 1 - j; k++) {
                                if (j - k < 0) {
                                    break skip3;
                                } else {
                                    if (ope[j - k] == '*' || ope[j - k] == '/') {
                                        num[j - k] = num[j];
                                    } else if (ope[j - k] != '*' || ope[j - k] != '/') {
                                        break skip3;
                                    }
                                }
                            }
                        } else if (num[j] < 0) {
                            num[j] = num[j + 1] + num[j] * -1;
                            num[j + 1] = num[j];
                            skip8:
                            for (int k = 1; j - k >= 0; k++) {
                                if (cnn1[j - k] == j - k && (j - k) != 0) {
                                    num[j - k] = num[j];
                                } else if (cnn1[j - k] == j - k && (j - k) == 0 && cond == true) {
                                    num[0] = num[1];
                                } else if (cnn1[j - k] != j - k) {
                                    break skip8;
                                }
                            }

                            skip3:
                            for (int k = 1; k <= i - 1 - j; k++) {
                                if (j - k < 0) {
                                    break skip3;
                                } else {
                                    if (ope[j - k] == '*' || ope[j - k] == '/') {
                                        num[j - k] = num[j];
                                    } else if (ope[j - k] != '*' || ope[j - k] != '/') {
                                        break skip3;
                                    }
                                }
                            }

                        }
                        skip1:
                        for (int k = 1; j - k >= 0; k++) {
                            if ((cnn3[j - k] == (j - k) && j - k != 0) || (cnn3[j - k] == 0 && cond2 == true) || (cnn5[j - k] == j - k && cnn5[j - k] != 0)) {
                                num[j - k] = num[j];
                            } else if ((cnn4[j - k] == j - k && (j - k) != 0) || ((j - k) == 0 && cond4 == true)) {
                                if ((num[j - k] == 0 && zero[j - k] == false) || (num[j - k] != 0 && cnn6[j - k] == j - k)) {
                                    num[j - k] = num[j];
                                    cnn6[j - k] = j - k;
                                } else if ((num[j - k] != 0 || (num[j - k] == 0 && zero[j - k] == true)) && (cnn6[j - k] != (j - k))) {
                                    num[j - k + 1] = num[j - k] * num[j - k + 1];
                                    num[j - k] = num[j - k + 1];
                                    cnn6[j - k] = j - k;
                                } else {
                                };

                            } else if (j - k != cnn3[j - k] && ope[j - k] == 0 && cnn4[j - k] != j - k && (cnn4[j - k] == 0 && cond4 == false)) {
                                break skip1;
                            }
                        }
                    }
                }
            }
            System.out.printf("Answer: %.9f \n", num[0]);

            System.out.println("Enter any button to continue / "
                    + "'no' to exit");
            String user = s.nextLine();
            System.out.println();
            if (user.equals("no")) {
                break error8;
            }
        } while (true);
    }
}
