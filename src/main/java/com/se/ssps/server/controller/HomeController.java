package com.se.ssps.server.controller;

//import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.server.ResponseStatusException;

import com.se.ssps.server.dto.StudentRegistrationRequest;
//import com.se.ssps.server.entity.response.LoginResponse;
import com.se.ssps.server.entity.user.Student;
//import com.se.ssps.server.entity.user.Admin;
// import com.se.ssps.server.entity.user.User;
// import com.se.ssps.server.service.user.UserService;

import com.se.ssps.server.service.user.StudentService;




@RestController
@CrossOrigin
public class HomeController {

    // @Autowired
    // UserService userService;





    @GetMapping("/index")
    public String homePage() {
        return "This is homepage";
    }

    @GetMapping("/login_home-page")
    public String loginPage() {
        return "this is loginpage";
    }

    // @PostMapping("/register")
    // public LoginResponse register(@RequestBody User user) {
    //     LoginResponse response = new LoginResponse();

    //     try {
    //         User newUser = userService.registerUser(user);
    //         response.setUser(newUser);
    //         response.setCorrectPass(true);
    //     } catch (Exception e) {
    //         response.setCorrectPass(false);
    //         response.setErrorMessage(e.getMessage());
    //     }

    //     return response;
    // }

    // @PostMapping("/login")
    // public LoginResponse login_proccess(@RequestBody User user) throws LoginException {
    //     LoginResponse loginResponse = new LoginResponse();
    //     User findUser = userService.findUser(user.getUsername());
    //     if (findUser != null) {
    //         if (findUser.getPassword().equals(user.getPassword())) {
    //             loginResponse.setUser(findUser);
    //             System.out.println(loginResponse.getUser().getUsername());
    //             loginResponse.setCorrectPass(true);
    //             return loginResponse;
    //         }
    //         loginResponse.setUser(findUser);
    //         loginResponse.setCorrectPass(false);
    //         return loginResponse;
    //     }
    //     return loginResponse;
    // }
    @Autowired
        private StudentService studentService;

//        @PostMapping("/login")
// public ResponseEntity<?> loginStudent(@RequestBody Student student) {
//     LoginResponse loginResponse = new LoginResponse();
//     try {
//         // Tìm Student dựa trên username
//         Student foundStudent = studentService.findStudentByUsername(student.getUsername());
//         if (foundStudent != null) {
//             // So sánh password
//             if (foundStudent.getPassword().equals(student.getPassword())) {
//                 loginResponse.setUser(foundStudent);
//                 loginResponse.setCorrectPass(true);
//                 return ResponseEntity.ok(loginResponse); // Đăng nhập thành công
//             }
//             // Mật khẩu sai
//             loginResponse.setUser(foundStudent);
//             loginResponse.setCorrectPass(false);
//             return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                                  .body("Sai mật khẩu"); // Trả về thông báo sai mật khẩu
//         }
//         // Nếu không tìm thấy Student
//         return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                              .body("Student không tồn tại"); // Trả về thông báo không tìm thấy student
//     } catch (Exception e) {
//         // Lỗi hệ thống
//         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                              .body("Lỗi hệ thống: " + e.getMessage()); // Trả về thông báo lỗi hệ thống
//     }
// }
        
        @PostMapping("/login")
    public ResponseEntity<String> loginStudent(@RequestBody Student student) {
    try {
        Student foundStudent = studentService.findStudentByUsername(student.getUsername());
        if (foundStudent != null) {
            if (foundStudent.getPassword().equals(student.getPassword())) {
                return ResponseEntity.ok("Đăng nhập thành công");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Sai mật khẩu");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Student không tồn tại");
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Lỗi hệ thống: " + e.getMessage());
    }
}
   

    // @PostMapping("/register")
    // public Student registerStudent(@RequestBody StudentRegistrationRequest request) {
    //     try {
    //         Student student = new Student(
    //                 request.getFirstName(),
    //                 request.getLastName(),
    //                 request.getUsername(),
    //                 request.getPassword(),
    //                 request.getStudentNumber());
    //         return studentService.registerStudent(student);
    //     } catch (Exception e) {
    //         throw new RuntimeException("Failed to register student: " + e.getMessage());
    //     }
    // }
    @PostMapping("/register")
    public ResponseEntity<?> registerStudent(@RequestBody StudentRegistrationRequest request) {
        try {
            // Kiểm tra username hoặc studentNumber có trùng không
            if (studentService.isUsernameTaken(request.getUsername())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Username đã tồn tại. Vui lòng chọn username khác.");
            }
            if (studentService.isStudentNumberTaken(request.getStudentNumber())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Student Number đã tồn tại.");
            }

            // Tạo đối tượng Student mới
            Student student = new Student(
                    request.getFirstName(),
                    request.getLastName(),
                    request.getUsername(),
                    request.getPassword(),
                    request.getStudentNumber());

            // Đăng ký Student
            studentService.registerStudent(student);

            // Trả về thông báo thành công
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Đăng ký thành công!");

        } catch (Exception e) {
            // Xử lý lỗi hệ thống
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi hệ thống: " + e.getMessage());
        }
    }

}
