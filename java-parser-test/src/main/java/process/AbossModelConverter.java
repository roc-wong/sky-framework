package process;

import com.google.common.collect.Lists;
import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import io.github.lukehutch.fastclasspathscanner.scanner.ClassInfo;
import io.github.lukehutch.fastclasspathscanner.scanner.FieldInfo;
import io.github.lukehutch.fastclasspathscanner.scanner.ScanResult;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author roc
 * @date 2018/01/03
 */
public class AbossModelConverter {

    public static void main(String[] args) {

        /*ClassPool pool = ClassPool.getDefault();
        try {
            CtClass cc = pool.get("hbec.app.transaction.stock.utils.App");
            CtMethod mthd = CtNewMethod.make("public Integer getInteger() { return null; }", cc);
            cc.addMethod(mthd);
            cc.writeFile();
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        List<FieldDesc> fieldDescs = loadFieldMapper();
    }


    public static void read(){
        List<FieldDesc> fieldDescs = loadFieldMapper();
        String[] packageToScan = new String[]{"hbec"};
        Map<String, ClassInfo>  classInfoMap = scanClass(packageToScan);
        for (Map.Entry<String, ClassInfo> entry : classInfoMap.entrySet()) {
            String key = entry.getKey();
            ClassInfo classInfo = entry.getValue();
            List<FieldInfo> fieldInfos = classInfo.getFieldInfo();
            for (FieldInfo fieldInfo : fieldInfos) {
//                fieldInfo
            }
            System.out.println(fieldInfos);
        }
    }

    public static Map<String, ClassInfo> scanClass(String[] packageToScan) {
        ScanResult scanResult = new FastClasspathScanner(packageToScan).enableFieldInfo().scan();
        return scanResult.getClassNameToClassInfo();
    }


    private static List<FieldDesc> loadFieldMapper() {
        String fileName = "field.txt";
        ClassLoader classLoader = AbossModelConverter.class.getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        List<FieldDesc> fieldDescs = Lists.newArrayList();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                FieldDesc fieldDesc = readLine(tempString);
                fieldDescs.add(fieldDesc);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return fieldDescs;
    }

    private static FieldDesc readLine(String line) {
        String[] members = line.replaceAll("\\s{1,}", " ").split(" ");
        return FieldDesc.newBuilder().setUpperCaseName(members[0]).setCode(Integer.valueOf(members[1])).setDesc(members[2]).build();
    }

}