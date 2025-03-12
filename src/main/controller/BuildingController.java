// 楼宇管理控制器：处理所有与楼宇相关的请求
@RestController
@RequestMapping("/building")
public class BuildingController {
    // 添加新楼宇信息
    @PostMapping("/add")
    public Result add(@RequestBody Building building) {
        // ... existing code ...
    }

    // 删除楼宇信息
    @DeleteMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        // ... existing code ...
    }

    // 修改楼宇信息
    @PutMapping("/update")
    public Result update(@RequestBody Building building) {
        // ... existing code ...
    }

    // 分页查询楼宇信息
    @GetMapping("/list")
    public Result list(@RequestParam Integer pageNum,
                      @RequestParam Integer pageSize,
                      @RequestParam(required = false) String buildingName) {
        // ... existing code ...
    }
} 