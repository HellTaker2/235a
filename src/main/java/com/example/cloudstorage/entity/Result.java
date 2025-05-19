package com.example.cloudstorage.entity;

public class Result<T> {
    private int code;    // 状态码（如1表示成功）
    private String msg;  // 消息描述
    private T data;      // 数据（泛型）

    // 成功静态方法（默认code=1）
    public static <T> Result<T> success(T data) {
        return new Result<>(1, "success", data);
    }

    // 失败静态方法（自定义code和msg）
    public static <T> Result<T> fail(int code, String msg) {
        return new Result<>(code, msg, null);
    }

    // 构造方法（私有化，强制使用静态方法）
    private Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    // Getter方法（必须提供，否则JSON序列化失败）
    public int getCode() { return code; }
    public String getMsg() { return msg; }
    public T getData() { return data; }
}
//@RestController
//@RequestMapping("/api")
//public class UserController {
//
//    // 返回对象
//    @GetMapping("/user")
//    public Result<User> getUser() {
//        User user = new User("张三", "zhangsan@example.com");
//        return Result.success(user);
//    }
//
//    // 返回字符串
//    @GetMapping("/hello")
//    public Result<String> hello() {
//        return Result.success("Hello World");
//    }
//
//    // 返回嵌套JSON
//    @GetMapping("/details")
//    public Result<Map<String, Object>> getDetails() {
//        Map<String, Object> data = new HashMap<>();
//        data.put("role", "admin");
//        data.put("permissions", Arrays.asList("read", "write"));
//        return Result.success(data);
//    }
//
//    // 返回失败示例
//    @GetMapping("/error")
//    public Result<String> error() {
//        return Result.fail(500, "Internal Server Error");
//    }
//}