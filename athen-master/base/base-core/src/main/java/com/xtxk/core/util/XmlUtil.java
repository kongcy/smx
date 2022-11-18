package com.xtxk.core.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.map.UnmodifiableMap;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by fansensen on 2020-10-19.
 */
public class XmlUtil {

    private static final Logger logger = LoggerFactory.getLogger(XmlUtil.class);

    private static final Object LOCK = new Object();

    private XmlUtil() {}

    /**
     * 对象转为XML字符串
     * @param val （对象类需要@Xml*相关注解）
     * @return xml字符串，null表示失败
     */
    public static String transBean2Xml(Object val){
        StringWriter sw = new StringWriter();
        try {
            JAXBContext context = JAXBContext.newInstance(val.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(val, sw);
            return sw.toString();
        } catch (JAXBException e) {
            logger.error("对象转为XML字符串", e);
        }
        return null;
    }

    /**
     * 字符串转java对象
     * @param xml xml字符串
     * @param beanClz java对象类型（需要@Xml*相关注解）
     * @param <T>
     * @return java对象，null表示失败
     */
    public static <T> T transXml2Bean(String xml, Class<T> beanClz) {
        StringReader sr = new StringReader(xml);
        try {
            JAXBContext context = JAXBContext.newInstance(beanClz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (T)unmarshaller.unmarshal(sr);
        } catch (JAXBException e) {
            logger.error("对象转为XML字符串", e);
        }
        return null;
    }

    /**
     * 将XML字符串解析为Json对象
     * @param xmlStr
     * @param isLowerCase
     * @return
     */
    public static JSONObject parseXml2Json(String xmlStr, boolean isLowerCase) {
        long startTime = System.currentTimeMillis();
        logger.info("解析xml开始....");
        JSONObject result = new JSONObject();
        if ( StringUtils.isBlank(xmlStr) || !xmlStr.startsWith("<")) {
            logger.info("XML解析，字符串为空，或者不是XML字符串: {}", xmlStr);
            result.put("parseError", "InvalidParameter: " + xmlStr);
            return result;
        }

        try {
            synchronized (LOCK) {
                Document doc = DocumentHelper.parseText(xmlStr);
                Element root = doc.getRootElement();
                parseXMLString(root, result, isLowerCase);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        logger.info("解析xml结束...., 耗时：{}毫秒", (System.currentTimeMillis() - startTime));
        return result;
    }


    /**
     * 递归解析xml结构
     * @param root
     * @param rootResult
     * @param isLowerCase
     */
    private static void parseXMLString(Element root, final JSONObject rootResult, boolean isLowerCase) {

        if ( root.isTextOnly() ) {
            insertElementValue(rootResult, root, isLowerCase);
            return;
        }

        JSONObject curVal = new JSONObject();
        String rootName = isLowerCase ? root.getName().toLowerCase() : root.getName();
        if ( rootResult.containsKey(rootName) ) {
            Object value = rootResult.get(rootName);
            if ( value instanceof JSONObject ) {
                JSONArray array = new JSONArray();
                array.add(value);
                rootResult.put(rootName, array);
            }
            ((JSONArray)rootResult.get(rootName)).add(curVal);

        } else {
            rootResult.put(rootName, curVal);
        }

        List<Element> subEleList = root.elements();
        if ( CollectionUtils.isEmpty(subEleList) ) {
            return;
        }

        for (Element element : subEleList) {
            parseXMLString(element, curVal, isLowerCase);
        }
    }

    private static void insertElementValue(JSONObject rootResult, Element root, boolean isLowerCase) {
        String rootName = isLowerCase ? root.getName().toLowerCase() : root.getName();
        if ( rootResult.containsKey(rootName) ) {
            Object value = rootResult.get(rootName);
            if ( ! (value instanceof JSONArray) ) {
                JSONArray array = new JSONArray();
                array.add(value);
                rootResult.put(rootName, array);
            }
            ((JSONArray)rootResult.get(rootName)).add(root.getTextTrim());
        } else {
            rootResult.put(rootName, root.getTextTrim());
        }
    }

    /**
     * xml解析及获取指定值辅助器
     * 用法(获取a标签下的b标签下的c的值)：
     *  XMLParseHelper helper = new XMLParseHelper(xmlString);
     *  Object val = helper.find("a.b.c");
     *  或
     *  Object val = helper.find("a.b[0].c");
     *  //其中数字为JsonArray数组下标
     *
     */
    public static class XMLParseHelper {
        private JSONObject result;

        private String originXml;

        private boolean isLowerCase = false;

        public XMLParseHelper(String originXml) {
            this(originXml, false);
        }

        public XMLParseHelper(String originXml, boolean isLowerCase) {
            this.originXml = originXml;
            this.result = parseXml2Json(this.originXml, isLowerCase);
            this.isLowerCase = isLowerCase;
        }

        /**
         * 获取指定值
         * @param key 键值，
         *            格式:a.b.c 即获取a标签中b标签中c标签的值；
         *            格式：a.b[0].c 即获取a标签中第一个b标签中c标签的值， 0为数组下标；
         *            每个环节只适用于jsonObject，不可用于array
         * @return
         */
        public Object findValue(String key) {
            if ( this.result.containsKey("parseError") ) {
                return this.result.get("parseError");
            }
            logger.info("查找key[{}]开始", key);
            String[] keys = key.split("\\.");
            JSONObject curVal = this.result;
            Object finalVal = null;
            String subKey;
            Pair<String, Integer> arrayInfoPair = new Pair<>();
            for (int i=0; i < keys.length; i++ ) {
                subKey = keys[i];
                if ( withArrayIndex(subKey, arrayInfoPair) ) {//jsonArray处理
                    finalVal = curVal.get(arrayInfoPair.getFirst());
                    if ( finalVal instanceof JSONArray ) {
                        finalVal = ((JSONArray)finalVal).get(arrayInfoPair.getSecond());
                    }

                    subKey = arrayInfoPair.getFirst();
                } else {
                    finalVal = curVal.get(subKey);
                }
                if ( curVal.containsKey(subKey) ) {

                    if ( i == keys.length - 1 ) {//最后一个环节
                        break;
                    }

                    if ( finalVal instanceof JSONObject ) {
                        curVal = (JSONObject) finalVal;
                    } else {
                        throw new IllegalArgumentException("key中<" + subKey + ">环节不是Json对象，无法解析");
                    }
                    continue;
                } else {
                    throw new IllegalArgumentException("key中<" + subKey + ">环节不存在");
                }
            }
            logger.info("查找key[{}]结束", key);
            return finalVal;
        }

        private boolean withArrayIndex(String subKey, Pair<String, Integer> arrayInfoPair) {
            Pattern p = Pattern.compile("(\\w+)\\[(\\d+)\\]");
            Matcher matcher = p.matcher(subKey);
            if ( matcher.matches() ) {
                arrayInfoPair.setFirst(matcher.group(1));
                arrayInfoPair.setSecond(Integer.parseInt(matcher.group(2)));
                return true;
            }

            return false;
        }

        /**
         * 获取解析XML后的json对象(不可修改)
         * @return
         */
        public JSONObject getResult() {
            return new JSONObject(UnmodifiableMap.unmodifiableMap(this.result));
        }
    }

    public static void main(String[] args) {
//        String xml = "<?xml version=\"1.0\" encoding=\"gb2312\"?>"+
//                "<Result xmlns=\"http://www.fiorano.com/fesb/activity/DBQueryOnInput2/Out\">"+
//                "<row resultcount=\"1\">"+
//                "<users_id>1001 </users_id>"+
//                "<users_name>wangwei </users_name>"+
//                "<users_group>80 </users_group>"+
//                "<users_address>1001号 </users_address>"+
//                "</row>"+
//                "<row resultcount=\"1\">"+
//                "<users_id>1002 </users_id>"+
//                "<users_name>wangwei </users_name>"+
//                "<users_group>80 </users_group>"+
//                "<users_address>1002号 </users_address>"+
//                "</row>"+
//                "</Result>";
//
//        for (int i = 0; i < 5; i++) {
//            new Thread(() -> {
//                Object o = new XMLParseHelper(xml, true).findValue("result.users_name");
//                System.out.println(Thread.currentThread().getName() + "------" + o);
//            }).setName("Thread-" + i);
//        }
        String str="<ROWSET>\n" +
                "   <ROW num=\"1\">\n" +
                "      <DEPTID>50167</DEPTID>\n" +
                "      <PDEPTID>50181</PDEPTID>\n" +
                "      <YHID>liping1.ptr</YHID>\n" +
                "      <YHDM>liping1</YHDM>\n" +
                "      <YHXM>?.钩</YHXM>\n" +
                "      <YHPWSX>150</YHPWSX>\n" +
                "      <DWBM>CNPC.QT.BJ.ZYRF</DWBM>\n" +
                "      <DWMC>?.含涓.补?.?淇℃.?€?..?.矗浠诲.?</DWMC>\n" +
                "      <DWJB>4</DWJB>\n" +
                "      <DWJC>涓.补?.?</DWJC>\n" +
                "      <DWPWSX>1093</DWPWSX>\n" +
                "      <GWBM>CNPC.QT.BJ.ZYRF-YG</GWBM>\n" +
                "      <GWMC>?.伐</GWMC>\n" +
                "      <SJCQQX>CNPC.QT.BJ.ZYRF-YG-liping1</SJCQQX>\n" +
                "      <YHJB>9</YHJB>\n" +
                "      <SFMR>Y</SFMR>\n" +
                "      <YKTDW>151101</YKTDW>\n" +
                "      <YHXB>?</YHXB>\n" +
                "      <YHSFZH>320123198801133016</YHSFZH>\n" +
                "      <HRRSBH>NO</HRRSBH>\n" +
                "      <HHID>34573016043143478365189150324463</HHID>\n" +
                "   </ROW>\n" +
                "</ROWSET>";

        JSONObject userInfo = (JSONObject) new XmlUtil.XMLParseHelper(str, true).findValue("rowset.row");
        System.out.println(userInfo);
    }
}
