import javax.sound.midi.Soundbank;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MidToAfterStack {
    public static void main(String[] args) {
        List<Character> stack = new LinkedList<>();
        List<Character> result = new LinkedList<>();
        String s = "1+2*(5-2)+9/3";

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch > '0' && ch <= '9') {
                ((LinkedList<Character>) result).addFirst(ch);
                //System.out.println(ch);
            } else {
                if (stack.isEmpty()) {
                    ((LinkedList<Character>) stack).addFirst(ch);
                } else {
                    Character first = ((LinkedList<Character>) stack).getFirst();
                    if (getLevel(first) < getLevel(ch)) {
                        ((LinkedList<Character>) stack).addFirst(ch);
                    } else {
                        if (ch == ')'){
                            while (((LinkedList<Character>) stack).getFirst() != '('){
                                Character c = ((LinkedList<Character>) stack).removeFirst();
                                ((LinkedList<Character>) result).addFirst(c);
                                //System.out.println(c);
                            }
                            ((LinkedList<Character>) stack).removeFirst();
                        }else {
                            while (stack.size() != 0 && getLevel(ch) <= getLevel(((LinkedList<Character>) stack).getFirst())) {
                                if (((LinkedList<Character>) stack).getFirst() == '(') {
                                    break;
                                } else {
                                    Character p = ((LinkedList<Character>) stack).removeFirst();
                                    ((LinkedList<Character>) result).addFirst(p);
                                    //System.out.println(p);
                                }
                            }
                            ((LinkedList<Character>) stack).addFirst(ch);
                        }
                    }
                }
            }
        }
        while (stack.size() > 0) {
            Character r = ((LinkedList<Character>) stack).removeFirst();
            ((LinkedList<Character>) result).addFirst(r);
            //System.out.println(r);
        }
        Iterator<Character> iterator = ((LinkedList<Character>) result).descendingIterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    public static int getLevel(char c) {
        switch (c) {
            case ')':
                return 0;
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '%':
                return 3;
            case '^':
                return 4;
            case '(':
                return 5;
            default:
                return -1;
        }
    }
}
