import java.util.LinkedList;
import java.util.List;

public class StackCheck {

    public static void main(String[] args) throws Exception {
        List<String> list = new LinkedList<>();
        String[] ss = "{ [ [ ] ] }".split("\\s");
        for (int i = 0; i < ss.length; i++) {
            if (ss[i].equals("{")) {
                ((LinkedList<String>) list).addFirst(ss[i]);
            } else if (ss[i].equals("[")) {
                ((LinkedList<String>) list).addFirst(ss[i]);
            } else if (ss[i].equals("(")) {
                ((LinkedList<String>) list).addFirst(ss[i]);
            }
            if (ss[i].equals("}") && !((LinkedList<String>) list).removeFirst().equals("{")) {
              throw new Exception("not match!");
            } else if (ss[i].equals("]") && !((LinkedList<String>) list).removeFirst().equals("[")) {
                throw new Exception("not match!");
            } else if (ss[i].equals(")")&& !((LinkedList<String>) list).removeFirst().equals("(")) {
                throw new Exception("not match!");
            }
        }
        if (!list.isEmpty()) throw new Exception("the list is not total match");
    }

}



