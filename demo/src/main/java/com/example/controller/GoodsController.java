package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.example.domain.Goods;
import com.example.domain.Goodspics;
import com.example.domain.Stockhistory;
import com.example.service.CategoryService;
import com.example.service.GoodsService;
import com.example.service.GoodspicsService;
import com.example.service.StockhistoryService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Resource
    private GoodsService goodsService;
    @Resource
    private GoodspicsService goodspicsService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private StockhistoryService stockhistoryService;

    @RequestMapping("/update_Ginfo")
    public boolean update_Ginfo(@RequestBody Map<String,Object> goodsMap){
        int gid = (int) goodsMap.get("gid");
        String name = (String) goodsMap.get("name");
        String desc = (String) goodsMap.get("desc");
        int stock = (int) goodsMap.get("stock");
        int value = (int) goodsMap.get("value");
        int cid = (int) goodsMap.get("cid");
        Goods g = goodsService.getById(gid);
        LambdaUpdateWrapper<Goods> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Goods::getGid,gid);
        Goods goods = new Goods();
        goods.setGname(name);
        goods.setGdesc(desc);
        goods.setGstock(stock);
        goods.setGvalue(value);
        goods.setCid(cid);
        if(goodsService.update(goods,updateWrapper)){
            if(stock!=g.getGstock()){
                Stockhistory stockhistory = new Stockhistory();
                stockhistory.setGID(gid);
                stockhistory.setSHStockO(g.getGstock());
                stockhistory.setSHStockN(stock);
                stockhistory.setSHTime(new Date());
                stockhistory.setSHReason(1);//0表示该库存记录变更原因为手动修改
                return stockhistoryService.save(stockhistory);
            }else {
                return true;
            }
        }else {
            return false;
        }
    }

    @RequestMapping("/update_Gshelf")
    public boolean update_Gshelf(@RequestBody Map<String,Object> goodsMap){
        int gid = (int) goodsMap.get("gid");
        int shelf = (int) goodsMap.get("shelf");
        if (shelf==1){
            QueryWrapper<Goodspics> wrapper = new QueryWrapper<>();
            wrapper.eq("gid", gid);
            if (goodspicsService.count(wrapper)==0) return false;
        }
        LambdaUpdateWrapper<Goods> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Goods::getGid,gid);
        Goods goods = new Goods();
        goods.setGshelf(shelf);
        return goodsService.update(goods,updateWrapper);
        }
//    商品的冻结状态被优化了
//    @RequestMapping("/update_Gstate")
//    public boolean update_Gstate(@RequestBody Map<String,Object> goodsMap){
//        int gid = (int) goodsMap.get("gid");
//        int state = (int) goodsMap.get("state");
//        LambdaUpdateWrapper<Goods> updateWrapper = new LambdaUpdateWrapper<>();
//        updateWrapper.eq(Goods::getGid,gid);
//        Goods goods = new Goods();
//        goods.setGstate((float) state);
//        return goodsService.update(goods,updateWrapper);
//    }

    @RequestMapping("/get_info")
    public List<Goods> get_info(@RequestBody Map<String,Object> goodsMap){
        int gid = (int) goodsMap.get("gid");
        LambdaQueryWrapper<Goods> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Goods::getGid,gid);
        return goodsService.list(lambdaQueryWrapper);
    }
    @RequestMapping("/list_By_Category")
    public List<Goods> list_By_Category(@RequestBody Map<String,Object> goodsMap) {
        Object cidObject = goodsMap.get("cid");
        if (cidObject == null) {
            throw new IllegalArgumentException("The 'cid' field is required.");
        }
        // 确保 idObject 是数组类型
        if (!(cidObject instanceof List)) {
            throw new IllegalArgumentException("The 'cid' field must be an array of integers.");
        }
        List<Integer> cidList = (List<Integer>) cidObject;
        int[] cid = cidList.stream().mapToInt(i -> i).toArray();
        int[] cids = categoryService.get_all_child(cid);
        List<Goods> goods = new ArrayList<>();
        for (int id : cids) {
            LambdaQueryWrapper<Goods> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(Goods::getCid, id);
            goods.addAll(goodsService.list(lambdaQueryWrapper));
        }
        return goods;
    }
    @RequestMapping("/get_info_by_name")
    public List<Goods> get_info_by_name(@RequestBody Map<String,Object> goodsMap){
        String name = (String) goodsMap.get("name");
        LambdaQueryWrapper<Goods> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Goods::getGname,name);
        return goodsService.list(lambdaQueryWrapper);
    }
    @RequestMapping("/add_goods")
    public boolean add_goods(@RequestBody Goods goods){
        return goodsService.save(goods);
    }
    @RequestMapping("/delete_goods/{id}")
    public boolean delete_goods(@PathVariable int id){
        return goodsService.removeById(id);
    }
}
