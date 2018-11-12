import java.io.File;
import java.util.Random;

public class DivFile {
    public static void main(String[] args) {
        doAction(new File("E:\\copy"));
    }

    public static void doAction(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            int index = 1;
            String newPath = "";
            for (int i = 0; i < files.length; i++, index++) {
                if (index == 1 || index % 4 == 0) {
                    File newFile = new File(file.getAbsolutePath() + File.separator + index);
                    newFile.mkdir();
                    newPath = newFile.getAbsolutePath();
                }
                files[i].renameTo(new File(newPath + File.separator + files[i].getName()));
            }
        }
    }
}
