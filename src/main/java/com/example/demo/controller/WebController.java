package com.example.demo.controller;

import com.example.demo.data.Detail;
import com.example.demo.data.LoginInfo;
import com.example.demo.func.AddressCheck;
import com.example.demo.func.LoginCheck;
import com.example.demo.data.Address;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class WebController {
    //login page
    @RequestMapping("/login")
    public String login(LoginInfo user, Model model, HttpServletRequest request){
        if (request.getSession().getAttribute("msg") != null){
            user.setMessage("Wrong user name or password");
        }
        model.addAttribute("user", user);
        return "login";
    }

    //check weather user name and password is right
    @SneakyThrows
    @PostMapping("/loginCheck")
    public String loginCheck(LoginInfo user, Model model, HttpServletRequest request, HttpServletResponse response){
        if(LoginCheck.isLogin(user)){
            user.setMessage("Login successfully!");
            request.getSession().setAttribute("login", "OK");
            request.getSession().removeAttribute("msg");

            return "redirect:/addresslist";
        } else {
            request.getSession().setAttribute("msg", "Wrong user name or password");
            user.setPassword("");
            user.setUsername("");

            return "redirect:/login";
        }
    }

    @SneakyThrows
    @GetMapping("/loginCheck")
    public String getLogin(HttpServletRequest request){
        if(null == request.getSession().getAttribute("login")){
            return "redirect:/login";
        } else {
            return "redirect:/addresslist";
        }
    }

    @SneakyThrows
    @GetMapping("/addresslist")
    public String showList(HttpServletRequest request){
        Object isLogin = request.getSession().getAttribute("login");
        if (null != isLogin){
            if(null == request.getSession().getAttribute("address")){
                Address address = new Address();
                request.getSession().setAttribute("address", address);
            }
            return "addresslist";
        } else {
            return "redirect:/login";
        }
    }

    @SneakyThrows
    @RequestMapping("/add")
    public String showAdd(@ModelAttribute("detail") Detail detail, Model model){
        return "add";
    }

    @SneakyThrows
    @GetMapping("/addCheck")
    public String getAdd(){
        return "add";
    }

    @SneakyThrows
    @PostMapping("/addCheck")
    public String checkAdd(HttpServletRequest request, Model model, Detail detail){
        Address list = (Address)request.getSession().getAttribute("address");
        if (AddressCheck.checkValid(list, detail)){
            list.getDetail().addElement(detail);
            return "redirect:/addresslist";
        } else {
            detail.setMessage("The contact already exists");
            detail.setName("");
            return showAdd(detail, model);
        }
    }

    @SneakyThrows
    @PostMapping("/update")
    public String showUpdate(@ModelAttribute("row")Integer row, HttpServletRequest request, Model model) {
        Address address = (Address) request.getSession().getAttribute("address");
        Detail detail = address.getDetail().elementAt(row);
        model.addAttribute("detail", detail);
        return "update";
    }

    @SneakyThrows
    @GetMapping("/update")
    public String getUpdate() {
        return "redirect:/addresslist";
    }

    @GetMapping("/updateCheck")
    public String getCheckUpdate() {
        return "redirect:/addresslist";
    }

    @PostMapping("/updateCheck")
    public String checkUpdate(HttpServletRequest request, Detail detail) {
        Address address = (Address) request.getSession().getAttribute("address");
        AddressCheck.updateElem(address, detail);
        return "redirect:/addresslist";
    }

    @GetMapping("/del")
    public String getDel() {
        return "redirect:/addresslist";
    }

    @PostMapping("/del")
    public String DeleteContact(@ModelAttribute("row")Integer row, HttpServletRequest request) {
        Address address = (Address) request.getSession().getAttribute("address");
        AddressCheck.deleteElem(address, row);
        return "redirect:/addresslist";
    }
}
