import com.alibaba.fastjson.JSONObject;

public class TestFastjson {

    public static void main(String[] args) {

        // 创建 json
        JSONObject object = new JSONObject();
        object.put("key1", 1);
        object.put("key2", "value2");
        System.out.println(object.toJSONString());

        System.out.println(object.get("key1"));
    }

}
