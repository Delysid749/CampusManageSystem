// 学生管理控制器：处理所有与学生相关的请求
@RestController
@RequestMapping("/student")
public class StudentController {
    // 添加新学生信息
    @PostMapping("/add")
    public Result add(@RequestBody Student student) {
        // ... existing code ...
    }

    // 删除学生信息
    @DeleteMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        // ... existing code ...
    }

    // 更新学生信息
    @PutMapping("/update")
    public Result update(@RequestBody Student student) {
        // ... existing code ...
    }

    // 分页查询学生信息
    @GetMapping("/list")
    public Result list(@RequestParam Integer pageNum,
                      @RequestParam Integer pageSize,
                      @RequestParam(required = false) String name,
                      @RequestParam(required = false) String studentNo) {
        // ... existing code ...
    }

    // 学生登录接口
    @PostMapping("/login")
    public Result login(@RequestBody Student student) {
        // ... existing code ...
    }

    // 获取学生个人信息
    @GetMapping("/info")
    public Result info(@RequestParam("token") String token) {
        // ... existing code ...
    }

    // 学生登出接口
    @PostMapping("/logout")
    public Result logout() {
        // ... existing code ...
    }

    // 修改学生密码
    @PostMapping("/updatePassword")
    public Result updatePassword(@RequestBody UpdatePasswordDTO updatePasswordDTO) {
        // ... existing code ...
    }
} 