package pers.lance.platform.base.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 代码生成器工具
 *
 * @author: lance
 * @create: 2018-11-23 11:09
 */
@Slf4j
public class CodeGenerateUtils {

    /**
     * properties
     */
    private static Properties props;
    /**
     * properties 文件位置
     */
    private static final String PROPERTIES = "code-generate.properties";

    /**
     * 驼峰转换
     */
    private static final Pattern PATTERN = Pattern.compile("[A-Z]");

    /**
     * 双斜杠
     */
    public static final String TWO_DIAGONAL_BARS = "//";
    /**
     * 单斜杠
     */
    public static final String DIAGONAL_BARS = "/";
    /**
     * 句号
     */
    public static final String PERIOD = ".";
    /**
     * 逗号
     */
    public static final String COMMA = ",";
    /**
     * 双反斜杠
     */
    public static final String TWO_BACKSLASHES = "\\";

    /**
     * freemark文件后缀
     */
    private static final String FREEMARK_FILE_SUFFIX = "ftl";
    /**
     * java文件后缀
     */
    private static final String JAVA_FILE_SUFFIX = "java";
    /**
     * xml文件后缀
     */
    private static final String XML_FILE_SUFFIX = "xml";

    private static String TEMPLATE_PATH = "src.main.resources.template";
    /**
     * 项目文件路径
     */
    private static final String PROJECT_PATH = "src.main.java";
    /**
     * 源base路径
     */
    private static String SOURCE_PATH = "pers.lance.platform";
    /**
     * 输出base路径
     */
    private static String TARGET_PATH = "pers.lance.platform";

    /**
     * 输出文件后缀
     */
    private static String TARGET_FILE_SUFFIXS = JAVA_FILE_SUFFIX + COMMA + XML_FILE_SUFFIX;
    /**
     * 输出文件
     */
    private static String TARGET_FILES = "entity,vo,dto,query,mapper,mapperXml,repository,service,serviceImpl,controller";
    /**
     * 源模块名称
     */
    private static String SOURCE_MODULE = "CodeTemplate";
    /**
     * 输出模块名称
     */
    private static String TARGET_MODULE = "Test";
    /**
     * java文件内容替换map
     */
    private static Map<String, String> javaFileContentReplaceMap = new LinkedHashMap<>();
    /**
     * xml文件内容替换map
     */
    private static Map<String, String> xmlFileContentReplaceMap = new LinkedHashMap<>();
    /**
     * default文件内容替换map
     */
    private static Map<String, String> defaultFileContentReplaceMap = new LinkedHashMap<>();
    /**
     * 实际输出文件map
     */
    private static Map<String, String> replaceFileMap = new HashMap<>();

    private static boolean isTemplate = Boolean.TRUE;

    /**
     * 代码生成
     */
    public static void generate() {
        // 初始化配置
        initConfigParams();

        // 文件夹路径
        String sourcePath = getSourcePath();

        // 循环执行
        loopFile(sourcePath);
    }

    private static String getSourcePath() {
        // 构造文件夹路径
        if (isTemplate) {
            return getProjectPath() + DIAGONAL_BARS + packagePathToFilePath(TEMPLATE_PATH);
        } else {
            return getProjectPath() + DIAGONAL_BARS + packagePathToFilePath(PROJECT_PATH) + DIAGONAL_BARS + packagePathToFilePath(SOURCE_PATH);
        }
    }

    /**
     * 自定义配置
     */
    private static void initConfigParams() {
        // 读取properties
        SOURCE_PATH = getProperty("source.path", SOURCE_PATH);
        TARGET_PATH = getProperty("target.path", TARGET_PATH);
        SOURCE_MODULE = getProperty("source", SOURCE_MODULE);
        TARGET_MODULE = getProperty("target", TARGET_MODULE);
        TARGET_FILE_SUFFIXS = getProperty("target.file.suffixs", TARGET_FILE_SUFFIXS);
        TARGET_FILES = getProperty("target.files", TARGET_FILES);

        // 输出包含的文件
        String[] includeFiles = TARGET_FILES.split(COMMA);
        // 默认包含的文件：原文件名，替换名
        for (String e : includeFiles) {
            if (StringUtils.equals(e.toLowerCase(), "entity".toLowerCase())) {
                String sourceFileName = upperFirst(SOURCE_MODULE) + PERIOD + (isTemplate ? FREEMARK_FILE_SUFFIX : JAVA_FILE_SUFFIX);
                String targetFileName = upperFirst(TARGET_MODULE) + PERIOD + JAVA_FILE_SUFFIX;
                replaceFileMap.put(sourceFileName, targetFileName);
            } else if (StringUtils.equals(e.toLowerCase(), "vo".toLowerCase())) {
                String sourceFileName = upperFirst(SOURCE_MODULE) + "VO" + PERIOD + (isTemplate ? FREEMARK_FILE_SUFFIX : JAVA_FILE_SUFFIX);
                String targetFileName = upperFirst(TARGET_MODULE) + "VO" + PERIOD + JAVA_FILE_SUFFIX;
                replaceFileMap.put(sourceFileName, targetFileName);
            } else if (StringUtils.equals(e.toLowerCase(), "dto".toLowerCase())) {
                String sourceFileName = upperFirst(SOURCE_MODULE) + "DTO" + PERIOD + (isTemplate ? FREEMARK_FILE_SUFFIX : JAVA_FILE_SUFFIX);
                String targetFileName = upperFirst(TARGET_MODULE) + "DTO" + PERIOD + JAVA_FILE_SUFFIX;
                replaceFileMap.put(sourceFileName, targetFileName);
            } else if (StringUtils.equals(e.toLowerCase(), "mapperXml".toLowerCase())) {
                String sourceFileName = upperFirst(SOURCE_MODULE) + (isTemplate ? upperFirst("mapperXml") + PERIOD + FREEMARK_FILE_SUFFIX : upperFirst("mapper") + PERIOD + XML_FILE_SUFFIX);
                String targetFileName = upperFirst(TARGET_MODULE) + upperFirst("mapper") + PERIOD + XML_FILE_SUFFIX;
                replaceFileMap.put(sourceFileName, targetFileName);
            } else {
                String sourceFileName = upperFirst(SOURCE_MODULE) + upperFirst(e) + PERIOD + (isTemplate ? FREEMARK_FILE_SUFFIX : JAVA_FILE_SUFFIX);
                String targetFileName = upperFirst(TARGET_MODULE) + upperFirst(e) + PERIOD + JAVA_FILE_SUFFIX;
                replaceFileMap.put(sourceFileName, targetFileName);
            }
        }

        // java文件内容替换map：path
        javaFileContentReplaceMap.put(SOURCE_PATH, TARGET_PATH);
        // java文件内容替换map：模块
        javaFileContentReplaceMap.put(SOURCE_MODULE, TARGET_MODULE);
        // java文件内容替换map：首字母小写
        javaFileContentReplaceMap.put(lowerFirst(SOURCE_MODULE), lowerFirst(TARGET_MODULE));

        // xml文件内容替换map：path
        xmlFileContentReplaceMap.put(SOURCE_PATH, TARGET_PATH);
        // xml文件内容替换map：驼峰转换
        xmlFileContentReplaceMap.put(underlineHander(SOURCE_MODULE), underlineHander(TARGET_MODULE));
        // xml文件内容替换map：模块
        xmlFileContentReplaceMap.put(SOURCE_MODULE, TARGET_MODULE);

        // default文件内容替换map：path
        defaultFileContentReplaceMap.put(SOURCE_PATH, TARGET_PATH);
        // default文件内容替换map：模块
        defaultFileContentReplaceMap.put(SOURCE_MODULE, TARGET_MODULE);

        // 公共信息构建
        Map<String, String> moduleNoteMap = new LinkedHashMap<>();
        moduleNoteMap.put("${author}", getProperty("author", ""));
        moduleNoteMap.put("${date}", LocalDateTime.now().toString());
        moduleNoteMap.put("${module}", getProperty("module", ""));

        // 公共信息共享
        javaFileContentReplaceMap.putAll(moduleNoteMap);
        xmlFileContentReplaceMap.putAll(moduleNoteMap);
        defaultFileContentReplaceMap.putAll(moduleNoteMap);
    }

    /**
     * 循环处理文件
     *
     * @param sourcePath
     * @throws IOException
     */
    private static void loopFile(String sourcePath) {
        // 读取目录
        File dir = new File(sourcePath);
        // 该文件目录下文件全部放入数组
        File[] files = dir.listFiles();
        // 循环文件
        for (File file : files) {
            // 判断是文件还是文件夹
            if (file.isDirectory()) {
                // 获取文件绝对路径
                loopFile(file.getAbsolutePath());
            } else {
                fileGenerateHandler(file);
            }

        }
    }

    /**
     * 文件生成处理
     *
     * @param file
     * @throws IOException
     */
    private static void fileGenerateHandler(File file) {
        // 判断是否执行
        if (replaceFileMap.containsKey(file.getName())) {
            // 输出文件名
            String targetFileName = replaceFileMap.get(file.getName());
            // 输出路径处理 ：
            String targetPath = getTargetPath(file, targetFileName);
            fileGenerate(file, targetPath);
        }
    }

    private static String getTargetPath(File file, String targetFileName) {
        String path = file.getAbsolutePath();
        if (isTemplate) {
            String path1 = packagePathToFilePath(TEMPLATE_PATH);
            String path2 = packagePathToFilePath(PROJECT_PATH + PERIOD + TARGET_PATH);
            return file.getAbsolutePath()
                    // 1 .package替换为输出package；
                    .replace(packagePathToFilePath(TEMPLATE_PATH), packagePathToFilePath(PROJECT_PATH + PERIOD + TARGET_PATH))
                    // 2.文件名替换为输出文件名
                    .replace(file.getName(), targetFileName);
        }
        return file.getAbsolutePath()
                // 1 .package替换为输出package；
                .replace(packagePathToFilePath(SOURCE_PATH), packagePathToFilePath(TARGET_PATH))
                // 2.文件名替换为输出文件名
                .replace(file.getName(), targetFileName);
    }

    /**
     * 文件生成
     *
     * @param sourceFile
     * @param targetFilePath
     * @throws IOException
     */
    private static void fileGenerate(File sourceFile, String targetFilePath) {
        // 数据流读取文件
        try (
                BufferedReader bufReader = new BufferedReader(new InputStreamReader(new FileInputStream(sourceFile)));
                PrintWriter printWriter = new PrintWriter(targetFilePath);
        ) {
            // 文件后缀
            String suffix = getSuffix(targetFilePath);
            StringBuffer strBuffer = new StringBuffer();
            for (String temp = null; (temp = bufReader.readLine()) != null; temp = null) {
                // 对文件内容做相应更改
                temp = fileContentReplace(temp, suffix);
                strBuffer.append(temp);
                // 行与行之间的分割
                strBuffer.append(newLine());
            }
            // 替换后输出的文件位置
            printWriter.write(strBuffer.toString().toCharArray());
            log.info("{} generate success.", targetFilePath);
        } catch (IOException e) {
            e.printStackTrace();
            log.info("{} data error.", targetFilePath);
        }
    }


    /**
     * 驼峰转换处理
     *
     * @param str
     * @return
     */
    private static String underlineHander(String str) {
        return underline(new StringBuffer(str)).toString().substring(1);
    }

    /**
     * 驼峰转换
     *
     * @param str
     * @return
     */
    private static StringBuffer underline(StringBuffer str) {
        Matcher matcher = PATTERN.matcher(str);
        StringBuffer sb = new StringBuffer(str);
        if (matcher.find()) {
            sb = new StringBuffer();
            //将当前匹配子串替换为指定字符串，并且将替换后的子串以及其之前到上次匹配子串之后的字符串段添加到一个StringBuffer对象里。
            //正则之前的字符和被替换的字符
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
            //把之后的也添加到StringBuffer对象里
            matcher.appendTail(sb);
        } else {
            return sb;
        }
        return underline(sb);
    }

    /**
     * 首字符小写
     *
     * @param s
     * @return
     */
    private static String lowerFirst(String s) {
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    /**
     * 首字符大写
     *
     * @param s
     * @return
     */
    private static String upperFirst(String s) {
        if (Character.isUpperCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    /**
     * 文件内容替换
     *
     * @param text
     * @param suffix
     * @return
     */
    private static String fileContentReplace(String text, String suffix) {
        // 根据文件suffix获取相应的文件内容替换map
        Map<String, String> contentReplaceMap = getFileContentReplaceMap(suffix);
        for (Map.Entry<String, String> entry : contentReplaceMap.entrySet()) {
            text = StringUtils.replace(text, entry.getKey(), entry.getValue());
        }
        return text;
    }

    /**
     * 根据文件suffix获取相应的文件内容替换map
     *
     * @param suffix
     * @return
     */
    private static Map<String, String> getFileContentReplaceMap(String suffix) {
        // 不同文件使用不同的内容替换map
        switch (suffix) {
            case XML_FILE_SUFFIX:
                return xmlFileContentReplaceMap;
            case JAVA_FILE_SUFFIX:
                return javaFileContentReplaceMap;
            default:
                return defaultFileContentReplaceMap;
        }
    }

    /**
     * 获取项目路径
     *
     * @return
     */
    private static String getProjectPath() {
        String path = System.getProperty("user.dir");
        return path.replace(TWO_BACKSLASHES, TWO_DIAGONAL_BARS);
    }

    /**
     * 换行
     *
     * @return
     */
    private static String newLine() {
        return System.getProperty("line.separator");
    }

    /**
     * 加载 properties
     */
    private static void loadProps() {
        props = new Properties();
        try (
                InputStreamReader in = new InputStreamReader(CodeGenerateUtils.class.getClassLoader().getResourceAsStream(PROPERTIES), "UTF-8");
        ) {
            props.load(in);
        } catch (FileNotFoundException e) {
            log.error("Property file not found: {}", PROPERTIES);
        } catch (IOException e) {
            log.error("Property file IO error: {}", PROPERTIES);
        }
    }

    /**
     * 获取 Property value
     *
     * @param key
     * @return
     */
    private static String getProperty(String key) {
        if (Objects.isNull(props)) {
            synchronized (CodeGenerateUtils.class) {
                if (Objects.isNull(props)) {
                    loadProps();
                }
            }
        }
        return props.getProperty(key);
    }

    /**
     * 待默认值的获取 Property value（若获取不到则返回默认值）
     *
     * @param key
     * @param defaultValue
     * @return
     */
    private static String getProperty(String key, String defaultValue) {
        if (Objects.isNull(props)) {
            synchronized (CodeGenerateUtils.class) {
                if (Objects.isNull(props)) {
                    loadProps();
                }
            }
        }
        return props.getProperty(key, defaultValue);
    }


    /**
     * package Path To File Path
     *
     * @param packagePath
     * @return
     */
    private static String packagePathToFilePath(String packagePath) {
        return packagePath.replace(PERIOD, TWO_BACKSLASHES);
    }

    /**
     * 获取name后缀
     *
     * @param fileName
     * @return
     */
    private static String getSuffix(String fileName) {
        return fileName.substring(fileName.lastIndexOf(PERIOD) + 1);
    }

    /**
     * 获取file name 不包含拓展名
     *
     * @param name
     * @return
     */
    private static String getFileName(String name) {
        if (name.indexOf(PERIOD) > -1) {
            return name.substring(0, name.lastIndexOf(PERIOD));
        }
        return name;
    }

}
