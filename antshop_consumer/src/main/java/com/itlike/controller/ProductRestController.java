package com.itlike.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itlike.pojo.AjaxRes;
import com.itlike.pojo.PageListRes;
import com.itlike.pojo.Product;
import com.itlike.pojo.QueryVo;
import com.itlike.service.IProductService;
import com.itlike.utils.FastDFSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author : 迟彪
 * @date : 2019/8/5
 */
@RestController
@RequestMapping("/product")
public class ProductRestController {
    @Value("${fastdfs-ip}")
    private String fastdfsIp;

    @Reference
    private IProductService productService;

    @RequestMapping("/productList")
    public PageListRes productList(QueryVo vo) {
        return productService.getProductList(vo);
    }

    @PostMapping("/addProduct")
    public AjaxRes addProduct(@RequestParam(value = "file", name = "file") MultipartFile file, Product product) {
        product.setImage(this.uploadImage(file));
        product.setOnlinetime(new Date());
        product.setState(true);
        return productService.addProduct(product);
    }

    @PutMapping("/updateProduct")
    public AjaxRes updateProduct(@RequestParam(value = "file", name = "file") MultipartFile file, Product product) {
        product.setImage(this.uploadImage(file));
        product.setOnlinetime(new Date());
        product.setState(true);
        return productService.updateProduct(product);
    }

    @PutMapping("/updateProductState/{id}")
    public AjaxRes updateProductState(@PathVariable("id") long id) {
        return productService.upadateProductState(id);
    }

    @RequestMapping("/getHotProduct")
    public List<Product> getHotProduct() {
        return productService.getHotProduct();
    }

    private String uploadImage(MultipartFile file) {
        if (file != null && file.isEmpty()) {
            System.out.println("文件为空空");
            return null;
        }
        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        fileName = UUID.randomUUID() + suffixName; // 新文件名
        FastDFSClient client = new FastDFSClient("classpath:client.conf");
        try {
            String path = client.uploadFile(file.getBytes(), fileName);
            return fastdfsIp + path;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
