package Utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    /**
     * 文件读取类
     * @param filepath        文件路径
     * @param encoding        编码方式
     * @return                文件列表
     * @throws IOException    文件读取异常
     */
    public static List<String> readLines(String filepath, String encoding) throws IOException {
        List<String> lines = new ArrayList<String>();
        File file = new File(filepath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding));
        while (reader.ready()) {
            lines.add(reader.readLine());
        }
        reader.close();
        return lines;
    }

    /**
     * 文件写入
     * @param filepath        文件路径
     * @param encoding        编码方式
     * @param lines           文件列表
     * @throws IOException    文件写入异常
     */
    public static void writeLines(String filepath, String encoding, List<String> lines) throws IOException {
        File file = new File(filepath);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), encoding));
        for (String line : lines) {
            writer.write(line+"\n");
        }
        writer.close();
    }

}
