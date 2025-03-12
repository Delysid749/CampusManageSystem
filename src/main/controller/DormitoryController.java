// 宿舍管理控制器：处理所有与宿舍相关的请求
@RestController
@RequestMapping("/dormitory")
public class DormitoryController {
    // 添加新宿舍
    @PostMapping("/add")
    public Result add(@RequestBody Dormitory dormitory) {
        // ... existing code ...
    }

    // 删除宿舍信息
    @DeleteMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        // ... existing code ...
    }

    // 更新宿舍信息
    @PutMapping("/update")
    public Result update(@RequestBody Dormitory dormitory) {
        // ... existing code ...
    }

    // 分页查询宿舍信息
    @GetMapping("/list")
    public Result list(@RequestParam Integer pageNum,
                      @RequestParam Integer pageSize,
                      @RequestParam(required = false) String buildingName,
                      @RequestParam(required = false) String dormitoryNo) {
        // ... existing code ...
    }

    // 根据楼宇ID获取宿舍列表
    @GetMapping("/listByBuildingId")
    public Result listByBuildingId(@RequestParam Integer buildingId) {
        // ... existing code ...
    }
} 