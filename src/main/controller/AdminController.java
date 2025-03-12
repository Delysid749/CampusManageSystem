// 管理员控制器：处理所有与管理员相关的请求
@RestController
@RequestMapping("/admin")
public class AdminController {
    // ... existing code ...
    
    // 管理员登录接口
    @PostMapping("/login")
    public Result login(@RequestBody Admin admin) {
        // ... existing code ...
    }

    // 获取管理员信息接口
    @GetMapping("/info")
    public Result info(@RequestParam("token") String token) {
        // ... existing code ...
    }

    // 管理员登出接口
    @PostMapping("/logout")
    public Result logout() {
        // ... existing code ...
    }

    // 修改管理员密码接口
    @PostMapping("/updatePassword")
    public Result updatePassword(@RequestBody UpdatePasswordDTO updatePasswordDTO) {
        // ... existing code ...
    }
} 