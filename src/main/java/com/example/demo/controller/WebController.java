package com.example.demo.controller;

import com.example.demo.dao.AddressRepository;
import com.example.demo.data.Detail;
import com.example.demo.data.LoginInfo;
import com.example.demo.data.Phone;
import com.example.demo.entity.ListData;
import com.example.demo.func.AddressCheck;
import com.example.demo.func.LoginCheck;
import com.example.demo.data.Address;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.Vector;

@Controller
public class WebController {
    private AddressRepository addressRepository;

    @Autowired
    public void setAddressRepository(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    //login page
    @RequestMapping("/login")
    public String login(LoginInfo user, Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("msg") != null) {
            user.setMessage("Wrong user name or password");
        }
        model.addAttribute("user", user);
        return "login";
    }

    //check weather user name and password is right
    @SneakyThrows
    @PostMapping("/loginCheck")
    public String loginCheck(LoginInfo user, Model model, HttpServletRequest request, HttpServletResponse response) {
        if (LoginCheck.isLogin(user)) {
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
    public String getLogin(HttpServletRequest request) {
        if (null == request.getSession().getAttribute("login")) {
            return "redirect:/login";
        } else {
            return "redirect:/addresslist";
        }
    }

    @SneakyThrows
    @GetMapping("/addresslist")
    public String showList(Model model, @RequestParam(value = "start", defaultValue = "0") Integer start,
                           @RequestParam(value = "limit", defaultValue = "3") Integer limit) {
        start = start < 0 ? 0 : start;
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(start, limit, sort);
        Page<ListData> listData = addressRepository.findAll(pageable);
        model.addAttribute("address", listData);
        return "addresslist";
    }

    @SneakyThrows
    @RequestMapping("/add")
    public String showAdd(@ModelAttribute("address") ListData listData, Model model) {
        return "add";
    }

    @SneakyThrows
    @GetMapping("/addCheck")
    public String getAdd() {
        return "add";
    }

    @SneakyThrows
    @PostMapping("/addCheck")
    public String checkAdd(ListData listData) {
        addressRepository.save(listData);
        return "redirect:/addresslist";
    }

    @SneakyThrows
    @RequestMapping("/update")
    public String showUpdate(@RequestParam Long listId, Model model) {
        Optional<ListData> listDataOptional = addressRepository.findById(listId);
        if(listDataOptional.isPresent()){
            ListData listData = listDataOptional.get();
            model.addAttribute("address", listData);
        }
        return "update";
    }


    @GetMapping("/updateCheck")
    public String getCheckUpdate() {
        return "redirect:/addresslist";
    }

    @PostMapping("/updateCheck")
    public String checkUpdate(ListData listData) {
        addressRepository.save(listData);
        return "redirect:/addresslist";
    }

    @RequestMapping("/del")
    public String DeleteContact(@RequestParam Long listId) {
        addressRepository.deleteById(listId);
        return "redirect:/addresslist";
    }
}
