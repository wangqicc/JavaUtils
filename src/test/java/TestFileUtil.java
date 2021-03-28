import Utils.FileUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestFileUtil {

    public static void main(String[] args) throws IOException {
        String filename = "src/test/data/test.txt";
        List<String> lines = new ArrayList<String>();
        lines.add("wq");
        lines.add("xsl");

        // 文件写入
        FileUtil.writeLines(filename, "UTF-8", lines);

        // 文件读取
        List<String> list = FileUtil.readLines(filename, "UTF-8");
        System.out.println(list);
    }

}
