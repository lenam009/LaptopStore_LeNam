package com.lenam.laptopshop.controller.admin;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.lenam.laptopshop.domain.Role;
import com.lenam.laptopshop.domain.User;
import com.lenam.laptopshop.service.RoleService;
import com.lenam.laptopshop.service.UploadService;
import com.lenam.laptopshop.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    private final UserService userService;
    private final RoleService roleService;
    private final UploadService uploadService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, RoleService roleService, UploadService uploadService,
            PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.uploadService = uploadService;
        this.passwordEncoder = passwordEncoder;
    }

    // @RequestMapping("/")
    // public String getHomePage(Model model) {
    // List<User> arrUser = this.userService.getAllUsersByEmail("test@gmail.com");
    // return "hello";
    // }

    @RequestMapping("/admin/user")
    public String getUsersPage(Model model) {
        List<User> users = this.userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/user/show";
    }

    @RequestMapping("/admin/user/{id}")
    public String getUserDetailPage(Model model, @PathVariable long id) {
        User user = this.userService.getOneUserById(id);
        model.addAttribute("user", user);
        return "admin/user/user-detail";
    }

    // create
    @GetMapping("/admin/user/create")
    public String createUserPage(Model model) {
        model.addAttribute("newUser", new User());
        List<Role> roles = this.roleService.getAllRole();
        model.addAttribute("roles", roles);
        System.out.println("roles + " + roles);
        return "admin/user/create";
    }

    @PostMapping(value = "/admin/user/create")
    public String postCreateUserPage(Model model,
            @ModelAttribute("newUser") @Valid User newUser,
            BindingResult newUserBindingResult,
            @RequestParam("lenamFile") MultipartFile avatarFile) {

        // System.out.println("New user: " + newUser);

        List<Role> roles = this.roleService.getAllRole();
        model.addAttribute("roles", roles);

        // List<FieldError> errors = newUserBindingResult.getFieldErrors();
        // for (FieldError error : errors) {
        // System.out.println("newUserValidate: " + error.getField() + " - " +
        // error.getDefaultMessage());
        // }

        // validate
        if (newUserBindingResult.hasErrors()) {
            return "admin/user/create";
        }

        // Upload File
        String avatarName = this.uploadService.handleSaveUploadFile(avatarFile, "avatar");
        // Hash Password
        String passwordEncode = this.passwordEncoder.encode(newUser.getPassword());

        newUser.setAvatar(avatarName);
        newUser.setPassword(passwordEncode);

        this.userService.handleSaveUser(newUser);
        return "redirect:/admin/user";
    }

    // update
    @RequestMapping("/admin/user/update/{id}")
    public String updateUserPage(Model model, @PathVariable long id) {
        User currentUser = this.userService.getOneUserById(id);
        model.addAttribute("currentUser", currentUser);

        List<Role> roles = this.roleService.getAllRole();
        model.addAttribute("roles", roles);

        return "admin/user/update";
    }

    // Giống RequestMapping method post nhưng ngắn hơn
    @PostMapping("/admin/user/update")
    public String postUpdateUserPage(Model model, @ModelAttribute("newUser") User newUser) {
        User currentUser = this.userService.getOneUserById(newUser.getId());
        if (currentUser != null) {
            currentUser.setFullName(newUser.getFullName());
            currentUser.setAddress(newUser.getAddress());
            currentUser.setPhone(newUser.getPhone());
            currentUser.setRole(newUser.getRole());
            this.userService.handleSaveUser(currentUser);
        }

        // Edit ảnh làm y phần create user ở phía trên + delete file ở dòng kế dưới
        // this.uploadService.deleteFile("1710400467165-Screenshot (2).png") ;

        return "redirect:/admin/user";
    }

    @GetMapping("/admin/user/delete/{id}")
    public String deleteUserPage(Model model, @PathVariable long id) {
        model.addAttribute("id", id);
        model.addAttribute("user", new User());
        return "admin/user/delete";
    }

    @PostMapping("/admin/user/delete")
    public String postDeleteUserPage(Model model, @ModelAttribute("user") User user) {
        this.userService.handleDeleteUser(user.getId());
        return "redirect:/admin/user";
    }
}
