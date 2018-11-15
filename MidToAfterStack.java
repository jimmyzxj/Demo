import java.util.LinkedList;

public class MidToAfterStack {
    //定义一个存放操作符的栈，用LinkedList实现
    LinkedList<String> stack;

    //将中缀表达式转为后缀表达式
    public String convertAfterExpress(String str) {
        stack = new LinkedList<>();
        //用空格分割字符串
        String[] cotents = str.split("\\s");
        //用于存放后缀表达式
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < cotents.length; i++) {
            String s = cotents[i];
            //字符为运算符时，对栈进行操作
            if (s.equals("*") || s.equals("+") || s.equals("-") || s.equals("/") || s.equals("(") || s.equals(")") || s.equals("%")) {
                //如果栈里没有操作符，存入栈中
                if (stack.isEmpty()) {
                    stack.addFirst(s);
                } else {
                    //栈不为空时，判断字符和栈顶运算符的优先级
                    String first = stack.getFirst();
                    if (getLevel(first) < getLevel(s)) {
                        //字符优先级大，直接压入栈中
                        stack.addFirst(s);
                    } else {
                        //字符优先级小于栈顶运算符
                        //判断运算符是否为 ) ，如果为 ) 需进行特殊处理
                        if (!s.equals(")")) {
                            //弹出大于等于字符优先级的元素
                            while (stack.size() != 0 && getLevel(s) <= getLevel(stack.getFirst())) {
                                //当遇到栈顶元素为( 时 ，停止弹出
                                if (stack.getFirst().equals("(")) {
                                    break;
                                } else {
                                    //将弹出的元素输入到后缀表达式中，元素间用空格隔开
                                    String p = stack.removeFirst();
                                    sb.append(p);
                                    sb.append("\t");
                                }
                            }
                            //弹出元素后，将字符压入栈中,不输出
                            stack.addFirst(s);
                        } else {
                            //当前字符为 ) 时候，进行特殊处理
                            while (!stack.getFirst().equals("(")) {
                                //将不为 （ 的运算符全部弹出，并输出到后缀表达式
                                String c = stack.removeFirst();
                                sb.append(c);
                                sb.append("\t");
                            }
                            //将 (  只弹出 ，不输出
                            stack.removeFirst();
                        }
                    }
                }
            } else {
                //直接将操作数 输出到后缀表达式
                sb.append(s);
                sb.append("\t");
            }
        }
        //遍历完成后，栈中的运算符全部输出
        while (stack.size() > 0) {
            String r = stack.removeFirst();
            sb.append(r);
            sb.append("\t");
        }
        return sb.toString().trim();
    }
    //计算后缀表达式
    public int getResult(String content) {
        LinkedList<String> cacul_list = new LinkedList<>();
        String[] cotents = content.split("\\s");
        for (int i = 0; i < cotents.length; i++) {
            String s = cotents[i];
            //将操作数直接压入栈中，读到操作符时候，从栈中弹出两个操作数，并将计算结果压入栈中
            if (s.equals("*") || s.equals("+") || s.equals("-") || s.equals("/") || s.equals("(") || s.equals(")") || s.equals("%")) {
                String s1 = cacul_list.removeFirst();
                String s2 = cacul_list.removeFirst();
                int tmp = cacul(Integer.valueOf(s2), Integer.valueOf(s1), s);
                cacul_list.addFirst(tmp + "");
            } else {
                cacul_list.addFirst(s);
            }
        }
        return Integer.valueOf(cacul_list.getFirst());
    }

    public int cacul(int x, int y, String p) {
        switch (p) {
            case "+":
                return x + y;
            case "-":
                return x - y;
            case "*":
                return x * y;
            case "/":
                return x / y;
            case "%":
                return x % y;
        }
        return 0;
    }

    public int getLevel(String c) {
        switch (c) {
            case ")":
                return 0;
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            case "%":
                return 3;
            case "^":
                return 4;
            case "(":
                return 5;
            default:
                return -1;
        }
    }

    public static void main(String[] args) {
        String str = "11 + 2 * ( 5 - 2 ) + 7 - 2 + 10 / 3";
        MidToAfterStack m = new MidToAfterStack();
        String s1 = m.convertAfterExpress(str);
        int result = m.getResult(s1);
        System.out.println(result);
    }
}
