package com.example.demothylaf.controller;

import com.example.demothylaf.entity.Product;
import com.example.demothylaf.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("list")
    public String getAll(Model model){
        List<Product> products = productRepository.findAll();
        model.addAttribute("listProduct",products);
        return "product-list";
    }

    @GetMapping("add")
    public String add(Model model){
        return "product-add";
    }

    @GetMapping("save")
    public String save(@ModelAttribute Product product){
        System.out.println(product);
        productRepository.save(product);
        return "redirect:/product/list";
    }

    @GetMapping("delete/{id}")
    public String deleteProduct(@PathVariable Integer id){
        Product product = productRepository.findById(id).get();
        productRepository.delete(product);
        return "redirect:/product/list";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable Integer id,Model model){
        Product product = productRepository.findById(id).get();
        model.addAttribute("product",product);
        return "product-edit";
    }

    @PostMapping("update")
    public String update(@ModelAttribute Product product){
        productRepository.save(product);
        System.out.println(product);
        return "redirect:/product/list";
    }
}
